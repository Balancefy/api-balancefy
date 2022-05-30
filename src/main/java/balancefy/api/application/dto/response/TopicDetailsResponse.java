package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;

public class TopicDetailsResponse extends ResponseDto {
    private TopicoResponseDto topico;
    private boolean liked;
    private Conta autor;

    public TopicDetailsResponse(TopicoResponseDto topico, boolean liked, Conta autor) {
        super("Sucesso");
        this.topico = topico;
        this.liked = liked;
        this.autor = autor;
    }

    public TopicDetailsResponse(Exception ex) {
        super(ex.getMessage());
    }

    public TopicoResponseDto getTopico() {
        return topico;
    }

    public void setTopico(TopicoResponseDto topico) {
        this.topico = topico;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Conta getAutor() {
        return autor;
    }

    public void setAutor(Conta autor) {
        this.autor = autor;
    }
}
