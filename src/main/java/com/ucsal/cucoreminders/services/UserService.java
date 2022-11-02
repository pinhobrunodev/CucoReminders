package com.ucsal.cucoreminders.services;

import com.ucsal.cucoreminders.dto.user.UserDTO;
import com.ucsal.cucoreminders.entities.Role;
import com.ucsal.cucoreminders.entities.User;
import com.ucsal.cucoreminders.repositories.RoleRepository;
import com.ucsal.cucoreminders.repositories.UserRepository;
import com.ucsal.cucoreminders.services.exceptions.NameAlreadyExistsException;
import com.ucsal.cucoreminders.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public void registrarUsuario(UserDTO userDTO) {
        if (userRepository.findByFullName(userDTO.getFullName()) != null){
            throw  new NameAlreadyExistsException("O nome "+userDTO.getFullName()+", já está cadastrado...tente novamente!");
        }
        var userEntity = new User();
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.getRoles().add(getRole("ROLE_USER"));
        userRepository.save(userEntity);
    }


    public Role getRole(String authority){
        return roleRepository.findByAuthority(authority).orElseThrow(()-> new ResourceNotFoundException("Role not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String fullName) throws UsernameNotFoundException {
        User user = userRepository.findByFullName(fullName);
        if (user == null) {
            log.error("User Not Found -> " + fullName);
            throw new UsernameNotFoundException("User Not Found");

        }
        log.info("User Found -> " + fullName);
        return user;
    }
}
