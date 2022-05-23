package balancefy.api.application.dto.response;


import java.time.LocalDateTime;

public class MovimentacaoResponseDto {
    private Integer id;
    private Double valor;
    private String topico;
    private String descricao;
    private String tipo;
    private LocalDateTime createdAt;
}
