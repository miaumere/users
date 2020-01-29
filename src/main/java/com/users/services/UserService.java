package com.users.services;
import com.users.dto.UserDTO;
import com.users.entities.User;
import org.aspectj.weaver.ast.Test;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity getUsersById(Long id) throws DataAccessException{
        User foundUser = userRepository.findById(id).orElse(null);
        if(foundUser != null) {
            ModelMapper modelMapper = new ModelMapper();
            UserDTO result = modelMapper.map(foundUser, UserDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
        }
        return new ResponseEntity("Brak użytkownika o podanym id.",  HttpStatus.NOT_FOUND);
    }
}
