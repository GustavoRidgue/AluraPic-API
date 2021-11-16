package com.ridgue.jangular.usecase;

import com.ridgue.jangular.database.entity.User;
import com.ridgue.jangular.database.repository.UserRepository;
import com.ridgue.jangular.exception.FailedSignInException;
import com.ridgue.jangular.exception.ResourceNotFoundException;
import com.ridgue.jangular.http.util.SignInForm;
import com.ridgue.jangular.http.util.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class AuthenticateUseCase {
    private final UserRepository userRepository;

    private Token generateToken(User user) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 50;
        Random random = new Random();

        String stringToken = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return new Token(stringToken, user);
    }

    public Token signIn(SignInForm signInForm) {
        if (userRepository.findByUsername(signInForm.getUsername()) == null) throw new ResourceNotFoundException();

        User byUsername = userRepository.findByUsername(signInForm.getUsername());

        if (!byUsername.getPassword().equals(signInForm.getPassword())) throw new FailedSignInException();

        return generateToken(byUsername);
    }
}
