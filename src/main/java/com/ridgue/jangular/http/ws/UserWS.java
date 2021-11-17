package com.ridgue.jangular.http.ws;
import com.ridgue.jangular.database.entity.User;
import com.ridgue.jangular.database.repository.UserRepository;
import com.ridgue.jangular.exception.*;
import com.ridgue.jangular.http.util.SignInForm;
import com.ridgue.jangular.http.util.SignUpForm;
import com.ridgue.jangular.http.util.Token;
import com.ridgue.jangular.usecase.AuthenticateUseCase;
import com.ridgue.jangular.usecase.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class UserWS {
    @Autowired
    private final UserRepository userRepository;

    private final UserUseCase userUseCase;
    private final AuthenticateUseCase authenticateUseCase;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userUseCase.listAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(userUseCase.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(name = "username") String username) {
        try {
            return ResponseEntity.ok(userUseCase.findByUsername(username));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("user/signIn")
    public ResponseEntity<Token> signIn(@RequestBody SignInForm signInForm) {
        try {
            return ResponseEntity.ok(authenticateUseCase.signIn(signInForm));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (FailedSignInException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("user/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpForm signUp, UriComponentsBuilder uriComponentsBuilder) {
        try {
            User user = userUseCase.signUp(signUp);
            URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(user);
        } catch (EmailAlreadyTakenException | UsernameAlreadyTakenException | InvalidFieldDataException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
