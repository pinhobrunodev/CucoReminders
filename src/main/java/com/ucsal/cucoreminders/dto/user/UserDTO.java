package com.ucsal.cucoreminders.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String fullName;
    @NotBlank(message = "Campo requerido.")
    @Size(min = 5,max = 20,message = "A senha precisa estar entre 5 e 20 caracteres")
    private String password;
}
