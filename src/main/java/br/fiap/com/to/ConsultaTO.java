package br.fiap.com.to;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public class ConsultaTO {

    private Long idConsulta;

    @NotBlank(message = "Especialidade é obrigatória")
    private String especialidade;

    @NotNull(message = "Data da consulta é obrigatória")
    @FutureOrPresent(message = "Data da consulta não pode ser no passado")
    private LocalDate dataConsulta;

    private String detalhes;

    @NotNull(message = "ID do Paciente é obrigatório")
    private Long idPaciente;

    // Construtores
    public ConsultaTO() {
    }

    public ConsultaTO(Long idConsulta, String especialidade, LocalDate dataConsulta, String detalhes, Long idPaciente) {
        this.idConsulta = idConsulta;
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.detalhes = detalhes;
        this.idPaciente = idPaciente;
    }

    // Getters e Setters
    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
