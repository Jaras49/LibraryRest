package com.library.configuration.security.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {

    @JsonProperty("login")
    private String usernameOrEmail;

    @JsonProperty("password")
    private String password;
}
