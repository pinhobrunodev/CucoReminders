package com.ucsal.cucoreminders.controllers;

import com.ucsal.cucoreminders.dto.user.UserDTO;
import com.ucsal.cucoreminders.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cucoreminder/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(value = "/salvar")
    public ResponseEntity<?> salvarUsuario(@Valid @RequestBody UserDTO userDTO){
        userService.registrarUsuario(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
    }

}
