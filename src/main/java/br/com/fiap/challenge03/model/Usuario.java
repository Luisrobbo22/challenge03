package br.com.fiap.challenge03.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nm_usuario")
    @NotBlank(message = "Campo nome obrigatório")
    private String nome;

    @Email(message = "campo email deve conter um email válido")
    @NotBlank(message = "Campo email é obrigatório")
    @Column(name = "ds_email", nullable = false)
    private String email;

    @Size(min = 8, message = "Campo senha deve conter pelo menos 8 Caracteres")
    @NotBlank(message = "Campo senha é obrigatório")
    @Column(name = "ds_senha", nullable = false)
    @JsonIgnore
    private String senha;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_nascimento")
    @NotNull(message = "Campo dataNascimento obrigatório")
    private Calendar dataNascimento;

    @Column(name = "nr_rg")
    private String rg;

    @Column(name = "nr_cpf", unique = true)
    @NotBlank(message = "Campo CPF obrigatório")
    @Size(min = 11, max = 13)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    private Genero genero;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Estado> estados;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InformacaoUsuario> infoUsers;

    public Usuario(Integer id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
