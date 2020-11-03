package springcrudapp.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcrudapp.model.User;
import springcrudapp.service.UserDetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestAdminController {
    private UserDetailsServiceImpl userService;

    @Autowired
    public RestAdminController(UserDetailsServiceImpl userService) {this.userService = userService;}

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
         if (id == null) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
         User user = this.userService.findUserById(id);

         if (user == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

         return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.saveUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUser(@RequestBody User user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.editUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = this.userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.allUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
