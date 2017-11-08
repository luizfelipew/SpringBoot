package br.com.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by FelipeWendt on 08/11/17.
 */
public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("devdojo"));
    }
}
