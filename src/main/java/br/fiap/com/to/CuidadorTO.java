package br.fiap.com.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CuidadorTO {

    private Long idCuidador;

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    private String telefone;

    @Email(message = "Formato de e-mail inválido")
    private String email;

    // Construtor vazio
    public CuidadorTO() {
    }

    // Construtor com parâmetros
    public CuidadorTO(Long idCuidador, String nome, String telefone, String email) {
        this.idCuidador = idCuidador;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Métodos Getters e Setters
    public Long getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Long idCuidador) {
        this.idCuidador = idCuidador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

