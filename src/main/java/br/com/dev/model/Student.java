package br.com.dev.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

/**
 * Created by FelipeWendt on 06/09/17.
 */
@Entity
public class Student extends AbstractEntity{
    @NotEmpty
    private String name;

    @NotEmpty
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
}
