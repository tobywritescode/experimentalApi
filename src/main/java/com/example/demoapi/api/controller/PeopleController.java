package com.example.demoapi.api.controller;

import com.example.demoapi.exception.AgeCanNotBeLowerThanZero;
import com.example.demoapi.exception.IdsCanNotBeEmptyException;
import com.example.demoapi.exception.UsersCanNotBeNullException;
import com.example.demoapi.model.people.RickAndMortyCharacter;
import com.example.demoapi.model.people.RickAndMortyCharacters;
import com.example.demoapi.model.people.UserAgeGroups;
import com.example.demoapi.model.people.Users;
import com.example.demoapi.service.RickAndMortyService;
import com.example.demoapi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    private final UserService userService;

    private final RickAndMortyService rickAndMortyService;

    @PostMapping("/getUsersOver/{age}")
    public ResponseEntity<Users> getUsersOver(@RequestBody Users users, @PathVariable Integer age) throws AgeCanNotBeLowerThanZero, UsersCanNotBeNullException {
        if(users == null || users.userList == null || users.userList.isEmpty()) {
            throw new UsersCanNotBeNullException("Users can not be null");
        }else if(age <= 0){
            throw new AgeCanNotBeLowerThanZero("Age can not be lower than zero.");
        }
        return ResponseEntity.ok(userService.getUsersOverAge(users, age));

    }

    @PostMapping("/getUsersInAgeGroups")
    public ResponseEntity<UserAgeGroups> getUsersInAgeGroups(@RequestBody Users users) throws UsersCanNotBeNullException {
        if(users == null || users.userList == null || users.userList.isEmpty()) {
            throw new UsersCanNotBeNullException("Users can not be null");
        }
        return ResponseEntity.ok(userService.putUsersIntoAgeGroups(users));
    }

    @GetMapping("/getRickAndMortyCharacters/{ids}")
    public ResponseEntity<RickAndMortyCharacter[]> getRickAndMortyCharacters(@PathVariable Integer[] ids) throws IdsCanNotBeEmptyException, JsonProcessingException {
        if(ids == null || ids.length == 0) {
            throw new IdsCanNotBeEmptyException("You must pass atleast one character Id");
        }
        return ResponseEntity.ok(rickAndMortyService.getCharacters(ids));
    }

}
