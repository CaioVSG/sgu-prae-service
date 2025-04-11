package br.edu.ufape.sguPraeService.comunicacao.dto.cancelamentoAgendamento;

import br.edu.ufape.sguPraeService.comunicacao.dto.agendamento.AgendamentoResponse;
import br.edu.ufape.sguPraeService.models.CancelamentoAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CancelamentoResponse {
    private Long id;
    private String motivo;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCancelamento;
    private AgendamentoResponse agendamento;

    public CancelamentoResponse(CancelamentoAgendamento cancelamento, ModelMapper modelMapper) {
        if (cancelamento == null) throw new IllegalArgumentException("CancelamentoAgendamento não pode ser nulo");
        modelMapper.map(cancelamento,this);
    }
}
