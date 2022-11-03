package com.ucsal.cucoreminders.dto.lembrete;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ucsal.cucoreminders.entities.Lembrete;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class InserirLembreteDto {


    private Long id;
    @NotBlank(message = "Campo requerido.")
    @Size(min = 5,max = 20,message = "O Título precisa estar entre 5 e 20 letras")
    private String titulo;
    @NotBlank(message = "Campo requerido.")
    @Size(min = 5,max = 100,message = "O corpo da mensagem precisa estar entre 5 e 100 letras")
    private String mensagem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull(message =  "Campo requerido")
    @FutureOrPresent(message = "A data so pode ser presente ou futuro")
    private LocalDateTime dataVencimento;


    public InserirLembreteDto() {
    }

    public InserirLembreteDto(Lembrete entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        mensagem = entity.getMensagem();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
