package balancefy.api.dto.response;

import balancefy.api.entities.Conta;

public class ContaResponseDto extends ResponseDto {

    private Integer id;
    private Double renda;
    private Double progresso;
    private UsuarioResponseDto usuario;

    public ContaResponseDto(Conta conta) {
        super("Sucesso");
        this.id = conta.getId();
        this.renda = conta.getRenda();
        this.progresso = conta.getProgresso();
        this.usuario = new UsuarioResponseDto(conta.getFkUsuario());
    }

    public ContaResponseDto(Exception ex) {
        super(ex.getMessage());
    }

    public Integer getId() {
        return id;
    }

    public Double getRenda() {
        return renda;
    }

    public Double getProgresso() {
        return progresso;
    }

    public UsuarioResponseDto getUsuario() {
        return usuario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public void setProgresso(Double progresso) {
        this.progresso = progresso;
    }

    public void setUsuario(UsuarioResponseDto usuario) {
        this.usuario = usuario;
    }
}
