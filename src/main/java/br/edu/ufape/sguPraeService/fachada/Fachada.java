package br.edu.ufape.sguPraeService.fachada;


import br.edu.ufape.sguPraeService.exceptions.EnderecoNotFoundException;
import br.edu.ufape.sguPraeService.exceptions.EstudanteNotFoundException;
import br.edu.ufape.sguPraeService.exceptions.ProfissionalNotFoundException;
import br.edu.ufape.sguPraeService.exceptions.TipoEtniaNotFoundException;
import br.edu.ufape.sguPraeService.models.*;
import br.edu.ufape.sguPraeService.servicos.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor
public class Fachada {
    private final ProfissionalService profissionalService;
    private final EnderecoService enderecoService;
    private final DadosBancariosService dadosBancariosService;
    private final TipoEtniaService tipoEtniaService;
    private final EstudanteService estudanteService;

    // ------------------- Profissional ------------------- //
    public List<Profissional> listarProfissionais() {
        return profissionalService.listar();
    }

    public Profissional buscarProfissional(Long id) throws ProfissionalNotFoundException {
        return profissionalService.buscar(id);
    }

    public Profissional salvarProfissional(Profissional profissional) {
        return profissionalService.salvar(profissional);
    }

    public Profissional editarProfissional(Long id, Profissional profissional) throws ProfissionalNotFoundException {
        return profissionalService.editar(id, profissional);
    }

    public void deletarProfissional(Long id) throws ProfissionalNotFoundException {
        profissionalService.deletar(id);
    }

    // ================== Estudante  ================== //

    public Estudante salvarEstudante(Estudante estudante) {
        return estudanteService.salvarEstudante(estudante);
    }

    public Estudante buscarEstudante(Long id) throws EstudanteNotFoundException {
        return estudanteService.buscarEstudante(id);
    }

    public List<Estudante> listarEstudantes() {
        return estudanteService.listarEstudantes();
    }

    public Estudante atualizarEstudante(Long id, Estudante estudante) throws EstudanteNotFoundException{
        return estudanteService.atualizarEstudante(id, estudante);
    }

    public void deletarEstudante(Long id) throws EstudanteNotFoundException {
        estudanteService.deletarEstudante(id);
    }


    // ================== TipoEtnia  ================== //


    public TipoEtnia salvarTipoEtnia(TipoEtnia tipoEtnia) {
        return tipoEtniaService.salvarTipoEtnia(tipoEtnia);
    }

    public TipoEtnia buscarTipoEtnia(Long id) throws TipoEtniaNotFoundException {
        return tipoEtniaService.buscarTipoEtnia(id);
    }

    public List<TipoEtnia> listarTiposEtnia() {
        return tipoEtniaService.listarTiposEtnia();
    }

    public TipoEtnia atualizarTipoEtnia(Long id, TipoEtnia tipoEtnia) throws TipoEtniaNotFoundException {
        return tipoEtniaService.atualizarTipoEtnia(id, tipoEtnia);
    }

    public void deletarTipoEtnia(Long id) throws TipoEtniaNotFoundException {
        tipoEtniaService.deletarTipoEtnia(id);
    }


    // ================== Endereco  ================== //

    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    public Endereco buscarEndereco(Long id) {
        try {
            return enderecoService.buscarEndereco(id);
        } catch (EnderecoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Endereco criarEndereco(Endereco endereco) {
        return enderecoService.criarEndereco(endereco);
    }

    public void excluirEndereco(Long id) {
        enderecoService.excluirEndereco(id);
    }

    public Endereco editarEndereco(Long id, Endereco enderecoAtualizado) {
        return enderecoService.editarEndereco(id, enderecoAtualizado);
    }

    // ================== Dados Bancarios  ================== //

    public DadosBancarios salvarDadosBancarios(DadosBancarios dadosBancarios) {
        return dadosBancariosService.salvarDadosBancarios(dadosBancarios);
    }

    public List<DadosBancarios> listarDadosBancarios() {
        return dadosBancariosService.listarDadosBancarios();
    }

    public DadosBancarios buscarDadosBancarios(Long id) {
        return dadosBancariosService.buscarDadosBancarios(id);
    }

    public void deletarDadosBancarios(Long id) {
        dadosBancariosService.deletarDadosBancarios(id);
    }

    public DadosBancarios atualizarDadosBancarios(Long id, DadosBancarios novosDadosBancarios) {
        return dadosBancariosService.atualizarDadosBancarios(id, novosDadosBancarios);
    }


}
