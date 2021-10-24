package br.com.fiap.challenge03.dto;

import br.com.fiap.challenge03.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {

    private String nome;

    private String cpf;

    private Date dataNascimento;

    private Calendar dataAtualizacaoEstado;

    private String pressaoArterial;

    private String monitoramentoCardiaco;

    private String temperatura;

    private double altura;

    private double peso;

    private double imc;

    public ConsultaDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.dataNascimento = usuario.getDataNascimento();
        this.dataAtualizacaoEstado = usuario.getEstados().get(0).getDataAtualizacao();
        this.pressaoArterial = usuario.getEstados().get(0).getPressaoArterial();
        this.monitoramentoCardiaco = usuario.getEstados().get(0).getMonitoramentoCardiaco();
        this.temperatura = usuario.getEstados().get(0).getTemperatura();
        this.peso = usuario.getInfoUsers().get(0).getPeso();
        this.altura = usuario.getInfoUsers().get(0).getAltura();
        this.imc = usuario.getInfoUsers().get(0).getImc();
    }
}
