package com.ucsal.cucoreminders.dto;

import com.ucsal.cucoreminders.entities.Lembrete;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LembreteDTO {


    private Long id;
    @NotBlank(message = "Campo requerido.")
    @Size(min = 5,max = 20,message = "O Título precisa estar entre 5 e 20 letras")
    private String titulo;
    @NotBlank(message = "Campo requerido.")
    @Size(min = 5,max = 100,message = "O corpo da mensagem precisa estar entre 5 e 100 letras")
    private String mensagem;


    public LembreteDTO() {
    }

    public LembreteDTO(Lembrete entity) {
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
}
