package com.ridgue.jangular.http.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {
    private String email;
    private String username;
    private String password;
}
