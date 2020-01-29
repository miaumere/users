package com.users.services;
import com.users.requests.EditUserRequest;
import com.users.requests.NewUserRequest;
import com.users.dto.UserDTO;
import com.users.entities.User;
import com.users.requests.SearchUserArgsRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity createUser(NewUserRequest request) {
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

    public ResponseEntity searchUser(Optional<String> name,
                                     Optional<String> surname,
                                     Optional<Integer> grade,
                                     Optional<Integer> salary) {
        List<User> foundUsers = userRepository.getMatchingUsers(name, surname, grade, salary);

        return new ResponseEntity(foundUsers, HttpStatus.OK);
    }

    public ResponseEntity editUser(EditUserRequest request) {
        User userToEdit = userRepository.findById(request.getId()).orElse(null);
        if(userToEdit != null && !request.areFieldsEmpty()) {
            userToEdit.setName(request.getName());
            userToEdit.setSurname(request.getSurname());
            userToEdit.setGrade(request.getGrade());
            userToEdit.setSalary(request.getSalary());

            userRepository.saveAndFlush(userToEdit);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } if(request.areFieldsEmpty()) {
            return new ResponseEntity("Nie wszystkie pola zostały uzupełnione.",
                    HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(String.format("Brak użytkownika o id: %s.", request.getId()),
                    HttpStatus.NOT_FOUND);
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
