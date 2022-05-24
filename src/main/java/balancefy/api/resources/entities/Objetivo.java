package balancefy.api.resources.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "objetivo")
public class Objetivo {
    @Id
    @Column(name = "id_objetivo", nullable = false)
    private Integer id;

    @Column(name = "categoria", length = 100)
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}