package com.gonie.shoppingmall.Controller;

import com.gonie.shoppingmall.customExceptions.UsernameAlreadyExistsException;
import com.gonie.shoppingmall.customExceptions.UsernameNoExistsException;
import com.gonie.shoppingmall.model.UserLoginRequest;
import com.gonie.shoppingmall.model.UserRegisterRequest;
import com.gonie.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request)
    {
        try {
            userService.registerUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok("Registration successful!");

        }catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        try {
            userService.loginUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok("Login successful!!");

        } catch (UsernameNoExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }
}
