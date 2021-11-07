package br.com.springboot.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.DTOs.UpdateUserDTO;
import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/subscribe")
    public ResponseEntity<String> postUser(@RequestBody User user) {
        try {
            this.userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/login/{id}")
    public User getUser(@PathVariable("id") Long id) {

        Optional<User> userFind = this.userRepository.findById(id);

        if (userFind.isPresent()) {
            return userFind.get();
        }
        return null;
    }

    @PutMapping("/login/{id}")
    public User putAgent(@RequestBody UpdateUserDTO updateUserDTO, @PathVariable("id") Long id) {

        Optional<User> userFind = this.userRepository.findById(id);

        if (userFind.isPresent()) {
            User user = userFind.get();
            if (!updateUserDTO.getLogin().isEmpty()) {
                user.setLogin(updateUserDTO.getLogin());
            }
            if (!updateUserDTO.getPassword().isEmpty()) {
                user.setPassword(updateUserDTO.getPassword());
            }

            return this.userRepository.save(user);

        }
        return null;
    }

}
