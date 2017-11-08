package br.com.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
<<<<<<< develop
 * Created by FelipeWendt on 01/11/17.
=======
 * Created by FelipeWendt on 08/11/17.
>>>>>>> terceira-autorizacao
 */
public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("devdojo"));
    }
}
<<<<<<< develop

=======
>>>>>>> terceira-autorizacao
