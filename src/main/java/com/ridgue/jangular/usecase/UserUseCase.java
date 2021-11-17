package com.ridgue.jangular.usecase;

import com.ridgue.jangular.database.entity.User;
import com.ridgue.jangular.database.repository.UserRepository;
import com.ridgue.jangular.exception.EmailAlreadyTakenException;
import com.ridgue.jangular.exception.ResourceNotFoundException;
import com.ridgue.jangular.exception.UsernameAlreadyTakenException;
import com.ridgue.jangular.http.util.SignUpForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@RequiredArgsConstructor
@AllArgsConstructor
public class UserUseCase {
    private final UserRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public User findById(long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) throw new ResourceNotFoundException();

        return user;
    }

    public User findByUsername(String username) {
        if (repo.findByUsername(username) == null) throw new ResourceNotFoundException();
        return repo.findByUsername(username);
    }

    public User signUp(SignUpForm signUpForm) {
        validUser(signUpForm.getUsername(), signUpForm.getEmail());

        User user = new User(signUpForm.getUsername(), signUpForm.getPassword(), signUpForm.getEmail());
        repo.save(user);

        return user;
    }

    public boolean validUser(String username, String email) {
        if (repo.findByEmail(email) != null) throw new EmailAlreadyTakenException();
        if (repo.findByUsername(username) != null) throw new UsernameAlreadyTakenException();

        return true;
    }
}
