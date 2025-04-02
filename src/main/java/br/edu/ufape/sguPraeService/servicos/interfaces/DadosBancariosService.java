package br.edu.ufape.sguPraeService.servicos.interfaces;

import br.edu.ufape.sguPraeService.models.DadosBancarios;

import java.util.List;

public interface DadosBancariosService {

    DadosBancarios buscarDadosBancarios(Long id);

    List<DadosBancarios> listarDadosBancarios();

    DadosBancarios salvarDadosBancarios(DadosBancarios dadosBancarios);

    DadosBancarios atualizarDadosBancarios(Long id, DadosBancarios novosDadosBancarios);

    void deletarDadosBancarios(Long id);
}
