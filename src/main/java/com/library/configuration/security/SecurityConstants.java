package com.library.configuration.security;

public interface SecurityConstants {

    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    long EXPIRATION_TIME = 60_000;
    String SECRET = "SECRETxyz";
}
