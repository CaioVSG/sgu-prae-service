package br.edu.ufape.sguPraeService.comunicacao.dto.tipoEtnia;

import br.edu.ufape.sguPraeService.models.TipoEtnia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TipoEtniaRequest {
    private Long id;
    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    public  TipoEtnia convertToEntity(TipoEtniaRequest tipoEtniaRequest, ModelMapper modelMapper) {
        return modelMapper.map(tipoEtniaRequest, TipoEtnia.class);
    }
}
