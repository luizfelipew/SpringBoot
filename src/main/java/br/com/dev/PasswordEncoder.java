package br.com.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
<<<<<<< develop
 * Created by FelipeWendt on 01/11/17.
=======
 * Created by FelipeWendt on 08/11/17.
>>>>>>> terceira-autorizacao
<<<<<<< HEAD
=======
 * Created by FelipeWendt on 08/11/17.
>>>>>>> autorizacao-terceira-parte
=======
>>>>>>> develop
 */
public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("devdojo"));
    }
}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> develop
<<<<<<< develop

=======
>>>>>>> terceira-autorizacao
<<<<<<< HEAD
=======
>>>>>>> autorizacao-terceira-parte
=======
>>>>>>> develop
