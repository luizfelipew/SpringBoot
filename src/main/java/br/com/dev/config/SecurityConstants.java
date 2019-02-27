package br.com.dev.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    // Authorization Bearer 7498d987vs97
    static final String SECRET = "DevDojoFoda";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000;


//    public static void main(String[] args) {
//        //Desconbrindo um dia em millisegundos
//        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//    }
}
