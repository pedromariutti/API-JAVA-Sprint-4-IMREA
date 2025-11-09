package br.fiap.com.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class PacienteTO {

    private Long idPaciente;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "CPF não pode ser nulo ou vazio")
    @Size(min = 11, max = 14, message = "CPF inválido")
    private String cpf;

    private String email;

    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    private String tipoDeficiencia;

    private Long idCuidador;

    // Construtores
    public PacienteTO() {
    }

    public PacienteTO(Long idPaciente, String nome, String cpf, String email, LocalDate dataNascimento, String tipoDeficiencia, Long idCuidador) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.tipoDeficiencia = tipoDeficiencia;
        this.idCuidador = idCuidador;
    }

    // Getters e Setters
    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public void setTipoDeficiencia(String tipoDeficiencia) {
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public Long getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Long idCuidador) {
        this.idCuidador = idCuidador;
    }
}
