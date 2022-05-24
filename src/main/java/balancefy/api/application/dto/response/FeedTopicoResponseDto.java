package balancefy.api.application.dto.response;

public class FeedTopicoResponseDto {
    private TopicoResponseDto topico;
    private boolean liked;

    public FeedTopicoResponseDto(TopicoResponseDto topico, boolean liked) {
        this.topico = topico;
        this.liked = liked;
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
}
