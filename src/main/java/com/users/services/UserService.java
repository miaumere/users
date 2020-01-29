package com.users.services;
import com.users.dto.NewUserDTO;
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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity getUsersById(Long id){
        User foundUser = userRepository.findById(id).orElse(null);
        if(foundUser != null) {
            ModelMapper modelMapper = new ModelMapper();
            UserDTO result = modelMapper.map(foundUser, UserDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
        }
        return new ResponseEntity(String.format("Brak użytkownika o id: %s.", id),  HttpStatus.NOT_FOUND);
    }

    public ResponseEntity createUser(NewUserDTO request) {
        User userToSave = new User();
        userToSave.setName(request.getName());
        userToSave.setSurname(request.getSurname());
        userToSave.setGrade(request.getGrade());
        userToSave.setSalary(request.getSalary());

        if(!request.areFieldsEmpty()){
        userRepository.saveAndFlush(userToSave);
            return new ResponseEntity(HttpStatus.CREATED);
        }else {
            return new ResponseEntity("Aby dodać użytkownika, uzupełnij wszystkie pola.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deleteUser(Long id) {
        User foundUser = userRepository.findById(id).orElse(null);
        if(foundUser != null) {
            userRepository.delete(foundUser);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(String.format("Brak użytkownika o id: %s. Użytkownik mógł zostać wcześniej usunięty", id),
                    HttpStatus.NOT_FOUND);
        }
    }
}
