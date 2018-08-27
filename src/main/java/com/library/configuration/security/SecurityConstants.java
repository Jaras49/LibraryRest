package com.library.configuration.security;

public interface SecurityConstants {

    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    long EXPIIRATION_TIME = 864_000_000;
    String SECRET = "SECRETxyz";
}
