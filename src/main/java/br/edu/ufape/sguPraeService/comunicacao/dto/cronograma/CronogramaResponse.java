package br.edu.ufape.sguPraeService.comunicacao.dto.cronograma;

import br.edu.ufape.sguPraeService.comunicacao.dto.vaga.VagaResponse;
import br.edu.ufape.sguPraeService.models.Cronograma;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter
public class CronogramaResponse {

    Long id;
    LocalDate data;
    List<VagaResponse> vagas;

    public CronogramaResponse(Cronograma cronograma, ModelMapper modelMapper){
        if (cronograma == null) throw new IllegalArgumentException("Cronograma não pode ser nulo");
        else modelMapper.map(cronograma, this);
    }
}
