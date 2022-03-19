package balancefy.api.entities;

public abstract class Meta {

    private Integer id;
    private String nome;
    private String descricao;
    private boolean done;

    public abstract void concluir();

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Boolean getDone() {return done;}

    public void setDone(Boolean done) {this.done = done;}

}
