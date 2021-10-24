package br.com.fiap.challenge03.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @Column(name = "id_genero", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ds_genero")
    @NotBlank(message = "Campo descrição do Genero obrigatório")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

}
