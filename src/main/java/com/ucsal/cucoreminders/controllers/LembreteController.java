package com.ucsal.cucoreminders.controllers;

import com.ucsal.cucoreminders.dto.lembrete.AtualizarLembreteDto;
import com.ucsal.cucoreminders.dto.lembrete.InserirLembreteDto;
import com.ucsal.cucoreminders.dto.lembrete.TrazerLembretesDto;
import com.ucsal.cucoreminders.services.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cucoreminder/lembretes")
public class LembreteController {

    @Autowired
    private LembreteService service;


    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(value = "/salvar")
    public ResponseEntity<?> salvarLembrete(@Valid @RequestBody InserirLembreteDto dto){
        service.adicionarLembrete(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lembrete criado com sucesso");
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping(value = "/atualizar/{lembreteId}")
    public ResponseEntity<?> atualizarLembrete(@PathVariable Long lembreteId, @Valid @RequestBody AtualizarLembreteDto dto){
        service.atualizarLembrete(lembreteId,dto);
        return ResponseEntity.ok().body("Lembrete com id "+lembreteId+",foi atualizado com sucesso!");
    }
    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping(value = "/deletar/{lembreteId}")
    public ResponseEntity<?> deletarLembrete(@PathVariable Long lembreteId){
        service.deletarLembrete(lembreteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Lembrete com id "+lembreteId+",foi deletado com sucesso!");
    }
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping
    public ResponseEntity<List<TrazerLembretesDto>> listarLembretes(){
        return ResponseEntity.ok().body(service.listarLembretes());
    }
}
