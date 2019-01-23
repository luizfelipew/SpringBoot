package br.com.dev.javaclient;

import br.com.dev.model.Student;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        Student studentPost = new Student();
        studentPost.setName("John Wick");
        studentPost.setEmail("john@pencil.com");
        JavaClientDAO javaClientDAO = new JavaClientDAO();
        System.out.println(javaClientDAO.findbyId(1));
        System.out.println(javaClientDAO.listAll());
        System.out.println(javaClientDAO.save(studentPost));

    }


}
