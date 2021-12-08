package com.ucsal.cucoreminders.services;

import com.ucsal.cucoreminders.dto.LembreteDTO;
import com.ucsal.cucoreminders.entities.Lembrete;
import com.ucsal.cucoreminders.repositories.LembreteRepository;
import com.ucsal.cucoreminders.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository repository;

    @Transactional
    public void adicionarLembrete(LembreteDTO lembreteDTO){
        Lembrete lembrete = new Lembrete();
        repository.save(copiarDtoParaEntidade(lembrete,lembreteDTO));
    }


    @Transactional
    public void atualizarLembrete(Long lembreteId,LembreteDTO dto){
        try {
            Lembrete lembrete = new Lembrete();
            lembrete = atualizarAuxiliar(lembreteId,dto);
            repository.save(lembrete);
        }catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Id não encontrado : "+lembreteId);
        }
    }

    public void deletarLembrete(Long lembreteId){
        try {
            repository.deleteById(lembreteId);
        }catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException("Id não encontrado : "+lembreteId);
        }
    }

    @Transactional(readOnly = true)
    public List<LembreteDTO> listarLembretes(){
        return repository.findAll(Sort.by("criadoEm")).stream().map(LembreteDTO::new).collect(Collectors.toList());
    }



    // Metodos auxiliares


    private Lembrete copiarDtoParaEntidade(Lembrete lembrete,LembreteDTO dto){
        lembrete.setTitulo(dto.getTitulo());
        lembrete.setMensagem(dto.getMensagem());
        return lembrete;
    }

    private Lembrete atualizarAuxiliar(Long lembreteId, LembreteDTO dto){
        Lembrete lembrete = repository.getById(lembreteId);
        lembrete.setTitulo(dto.getTitulo());
        lembrete.setMensagem(dto.getMensagem());
        return lembrete;
    }

}
