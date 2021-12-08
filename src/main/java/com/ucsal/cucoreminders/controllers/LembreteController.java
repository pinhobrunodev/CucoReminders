package com.ucsal.cucoreminders.controllers;

import com.ucsal.cucoreminders.dto.LembreteDTO;
import com.ucsal.cucoreminders.entities.Lembrete;
import com.ucsal.cucoreminders.services.LembreteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cucoreminders")
public class LembreteController {

    @Autowired
    private LembreteService service;


    @PostMapping(value = "/salvar")
    public ResponseEntity<Void> salvarLembrete(@Valid @RequestBody LembreteDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        service.adicionarLembrete(dto);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/atualizar/{lembreteId}")
    public ResponseEntity<Void> atualizarLembrete(@PathVariable Long lembreteId, @Valid @RequestBody LembreteDTO dto){
        service.atualizarLembrete(lembreteId,dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deletar/{lembreteId}")
    public ResponseEntity<Void> deletarLembrete(@PathVariable Long lembreteId){
        service.deletarLembrete(lembreteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/lembretes")
    public ResponseEntity<List<LembreteDTO>> listarLembretes(){
        return ResponseEntity.ok().body(service.listarLembretes());
    }
}
