package balancefy.api.application.dto.response;

import java.time.LocalDateTime;

public class TaskResponseDto {
   private  Integer id;
   private String ordem;
   private String descricao;
   private Integer done;
   private Double pontuacao;
   private LocalDateTime createdAt;
}
