package br.com.dev.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

/**
 * Created by FelipeWendt on 06/09/17.
 */
@Entity
public class Student extends AbstractEntity{
    @NotEmpty(message = "O campo nome do estudante é obrigatório")
    private String name;

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Student(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @NotEmpty
    @Email(message = "Digite um email válido")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
