package br.com.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
<<<<<<< develop
 * Created by FelipeWendt on 01/11/17.
=======
 * Created by FelipeWendt on 08/11/17.
>>>>>>> terceira-autorizacao
=======
 * Created by FelipeWendt on 08/11/17.
>>>>>>> autorizacao-terceira-parte
 */
public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("devdojo"));
    }
}
<<<<<<< HEAD
<<<<<<< develop

=======
>>>>>>> terceira-autorizacao
=======
>>>>>>> autorizacao-terceira-parte
