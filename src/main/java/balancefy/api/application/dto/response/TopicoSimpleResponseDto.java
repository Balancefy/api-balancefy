package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Topico;


public class TopicoSimpleResponseDto extends ResponseDto {

    private Integer id;

    public TopicoSimpleResponseDto(int id) {
        super("Sucesso");
        this.id = id;
    }

    public TopicoSimpleResponseDto(Exception ex) {
        super(ex.getMessage());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
