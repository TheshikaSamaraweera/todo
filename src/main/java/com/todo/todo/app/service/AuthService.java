package com.todo.todo.app.service;

import com.todo.todo.app.dto.LoginDTO;
import com.todo.todo.app.dto.RegisterDTO;
import com.todo.todo.app.entity.User;
import com.todo.todo.app.exception.TodoAPIException;
import com.todo.todo.app.reopsitory.RoleRepository;
import com.todo.todo.app.reopsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public  String register(RegisterDTO registerDTO){

        //check username already registered
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST , "Username already exists");
        }

        //check email already exists
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST , "Email already exists");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));

        userRepository.save(user);

        return "User created successfully";

    }

    public String login (LoginDTO loginDTO){

         Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login successful";

    }


}
