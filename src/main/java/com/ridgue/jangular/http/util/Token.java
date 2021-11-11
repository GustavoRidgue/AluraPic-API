package com.ridgue.jangular.http.util;

import com.ridgue.jangular.database.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class Token {
    private String token;
    private final LocalDateTime created = LocalDateTime.now();
    private LocalDateTime expiration = created.plusMinutes(30);
    private User user;

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Token(String token) {
        this.token = token;
    }
}
