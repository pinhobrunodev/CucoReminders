package com.ucsal.cucoreminders.dto.lembrete;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ucsal.cucoreminders.entities.Lembrete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrazerLembretesDto {

    private Long id;
    private String titulo;
    private String mensagem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataVencimento;

    public TrazerLembretesDto(Lembrete lembreteEntity) {
        id = lembreteEntity.getId();
        titulo = lembreteEntity.getTitulo();
        mensagem = lembreteEntity.getMensagem();
        dataVencimento = lembreteEntity.getTimeSchedule().getDueDate();
    }
}
