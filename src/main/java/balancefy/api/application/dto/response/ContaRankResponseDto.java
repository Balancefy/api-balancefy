package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;

public class ContaRankResponseDto {
    private Integer id;
    private String nome;
    private Double progresso;
    private Integer objetivo;

    public ContaRankResponseDto(Conta conta, int objetivosConcluido) {
        this.id = conta.getId();
        this.nome = conta.getFkUsuario().getNome();
        this.progresso = conta.getProgresso();
        this.objetivo = objetivosConcluido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getProgresso() {
        return progresso;
    }

    public void setProgresso(Double progresso) {
        this.progresso = progresso;
    }

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

}
