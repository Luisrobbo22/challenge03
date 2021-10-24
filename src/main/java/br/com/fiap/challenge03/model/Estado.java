package br.com.fiap.challenge03.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer id;

    @Column(name = "nr_atualizacao", nullable = false)
    @NotNull(message = "Campo nr_atualização obrigatório")
    private Integer numeroAtualizacao;

    @ManyToOne()
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "dt_atualizacao", nullable = false)
    @NotNull(message = "Campo dt_atualizacao é obrigatório")
    private Calendar dataAtualizacao;

    @Column(name = "md_pressao_arterial", length = 10)
    @NotBlank(message = "O campo md_pressao_arterial é obrigatório")
    private String pressaoArterial;

    @Column(name = "md_monitoramento_cardiaco", length = 10)
    @NotBlank(message = "O campo md_monitoramento_cardiaco é obrigatório")
    private String monitoramentoCardiaco;

    @Column(name = "md_temperatura", length = 10)
    private String temperatura;

    public Estado(Integer id, Calendar dataAtualizacao, String temperatura,
                  String monitoramentoCardiaco, String pressaoArterial) {
        this.id = id;
        this.dataAtualizacao = dataAtualizacao;
        this.temperatura = temperatura;
        this.monitoramentoCardiaco = monitoramentoCardiaco;
        this.pressaoArterial = pressaoArterial;
    }

    public Estado(String temperatura) {
        this.temperatura = temperatura;
    }
}
