package balancefy.api.application.dto.request;

import balancefy.api.resources.entities.Objetivo;

import java.time.Instant;

public class ObjetivoRequestDto {

    private Objetivo objetivo;

    private String descricao;

    private Double valorTotal;

    private Double valorInicial;

    private Instant tempoEstimado;

}
