package br.edu.ufape.sguPraeService.comunicacao.controllers;

import java.util.List;

import br.edu.ufape.sguPraeService.comunicacao.dto.estudante.*;
import br.edu.ufape.sguPraeService.comunicacao.dto.usuario.AlunoResponse;
import br.edu.ufape.sguPraeService.exceptions.notFoundExceptions.EstudanteNotFoundException;
import br.edu.ufape.sguPraeService.exceptions.notFoundExceptions.TipoEtniaNotFoundException;
import br.edu.ufape.sguPraeService.fachada.Fachada;
import br.edu.ufape.sguPraeService.models.Estudante;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudantes")
@RequiredArgsConstructor
public class EstudanteController {
    private final Fachada fachada;
    private final ModelMapper modelMapper;

    @GetMapping("/list")
    public List<EstudanteResponse> listarEstudantes() {
        return fachada.listarEstudantes();
    }

    @GetMapping
    public Page<EstudanteResponse> listarEstudantes(@PageableDefault(sort = "id") Pageable pageable) {
        return fachada.listarEstudantes(pageable);
    }

    @GetMapping("/curso/{id}")
    public Page<EstudanteResponse> listarEstudantesPorCurso(
            @PathVariable Long id,
            @PageableDefault(sort = "id") Pageable pageable
    ) {
        return fachada.listarEstudantesPorCurso(id, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudanteResponse> buscarEstudante(@PathVariable Long id) throws EstudanteNotFoundException {
        return ResponseEntity.ok(fachada.buscarEstudante(id));
    }

    @PreAuthorize("hasRole('ALUNO')")
    @PostMapping
    public ResponseEntity<EstudanteResponse> criarEstudante(@Valid @RequestBody EstudanteRequest estudanteRequest) throws TipoEtniaNotFoundException {
        Estudante estudante = estudanteRequest.convertToEntity(estudanteRequest, modelMapper);
        EstudanteResponse novoEstudante = fachada.salvarEstudante(estudante, estudanteRequest.getTipoEtniaId());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstudante);
    }

    @PreAuthorize("hasRole('ESTUDANTE')")
    @PatchMapping
    public ResponseEntity<EstudanteResponse> atualizarEstudante(@Valid @RequestBody EstudanteRequest estudanteRequest) throws EstudanteNotFoundException, TipoEtniaNotFoundException {
        Estudante estudante = estudanteRequest.convertToEntity(estudanteRequest, modelMapper);
        EstudanteResponse estudanteAtualizado = fachada.atualizarEstudante(estudante,estudanteRequest.getTipoEtniaId());
        return ResponseEntity.ok(estudanteAtualizado);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deletarEstudante(@PathVariable Long id) throws EstudanteNotFoundException{
        fachada.deletarEstudante(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/credores")
    public ResponseEntity<Page<CredorResponse>> listarCredoresComAuxiliosAtivos(@PageableDefault(sort = "id") Pageable pageable) {
        Page<CredorResponse> credores = fachada.listarCredoresComAuxiliosAtivos(pageable);
        return ResponseEntity.ok(credores);
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/credores/list")
    public ResponseEntity<List<CredorResponse>> listarCredoresComAuxiliosAtivos() {
        List<CredorResponse> credores = fachada.listarCredoresComAuxiliosAtivos();
        return ResponseEntity.ok(credores);
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/auxlio/{auxilioId}")
    public ResponseEntity<Page<EstudanteResponse>> buscarEstudantesPorAuxiliosId(@PathVariable Long auxilioId, @PageableDefault(sort = "id") Pageable pageable) {
        Page<Estudante> estudantes = fachada.listarEstudantesPorAuxilio(auxilioId, pageable);
        Page<EstudanteResponse> response = estudantes.map(estudante -> new EstudanteResponse(estudante, modelMapper));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/credores/auxilio/{id}")
    public ResponseEntity<Page<CredorResponse>> listarCredoresPorAuxilio(@PathVariable Long id, @PageableDefault(sort = "id") Pageable pageable) {
        Page<CredorResponse> credores = fachada.listarCredoresPorAuxilio(id, pageable);
        return ResponseEntity.ok(credores);
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/credores/auxilio/{id}/list")
    public ResponseEntity<List<CredorResponse>> listarCredoresPorAuxilio(@PathVariable Long id) {
        List<CredorResponse> credores = fachada.listarCredoresPorAuxilio(id);
        return ResponseEntity.ok(credores);
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/credores/publicacao")
    public ResponseEntity<Page<PublicacaoResponse>> listarCredoresParaPublicacao( @PageableDefault(sort = "id") Pageable pageable) {
        Page<AlunoResponse> pageAlunos = fachada.listarCredoresParaPublicacao(pageable);
        Page<PublicacaoResponse> pagePublicacoes = pageAlunos.map(alunoResponse ->
                new PublicacaoResponse(alunoResponse, modelMapper)
        );
        return ResponseEntity.ok(pagePublicacoes);
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/credores/curso/{id}")
    ResponseEntity<Page<CredorResponse>> listarCredoresPorCurso(@PathVariable Long id, @PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(fachada.listarCredoresPorCurso(id, pageable));
    }

    @PreAuthorize("hasRole('GESTOR') and hasRole('PRAE_ACCESS')")
    @GetMapping("/{id}/relatorio")
    public ResponseEntity<RelatorioEstudanteAssistidoResponse> gerarRelatorioAssistido(
            @PathVariable Long id
    ) throws EstudanteNotFoundException {
        RelatorioEstudanteAssistidoResponse relatorio = fachada.gerarRelatorioEstudanteAssistido(id);
        return ResponseEntity.ok(relatorio);
    }

}