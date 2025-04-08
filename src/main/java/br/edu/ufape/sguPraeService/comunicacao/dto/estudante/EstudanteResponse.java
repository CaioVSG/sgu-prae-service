package br.edu.ufape.sguPraeService.comunicacao.dto.estudante;

import br.edu.ufape.sguPraeService.comunicacao.dto.endereco.EnderecoResponse;
import br.edu.ufape.sguPraeService.comunicacao.dto.tipoEtnia.TipoEtniaResponse;
import br.edu.ufape.sguPraeService.comunicacao.dto.usuario.AlunoResponse;
import br.edu.ufape.sguPraeService.models.Estudante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EstudanteResponse {

    private Long id;
    private AlunoResponse aluno;
    private BigDecimal rendaPercapta;
    private String contatoFamilia;
    private boolean deficiente;
    private String tipoDeficiencia;
    private TipoEtniaResponse tipoEtnia;
    private EnderecoResponse endereco;

    public EstudanteResponse(Estudante estudante,  ModelMapper modelMapper) {
        if (estudante == null) throw new IllegalArgumentException("Estudante não pode ser nulo");
        else modelMapper.map(estudante, this);
    }
}
