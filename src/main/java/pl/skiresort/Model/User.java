package pl.skiresort.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name= "clients")
public class User {

    @Column(name= "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "name")
    @NotBlank(message = "Your name must not be empty!")
    private String name;

    @Column(name= "surname")
    @NotBlank(message = "Your surname must not be empty!")
    private String surname;

    @Column(name= "age")
    @NotNull(message = "Your age must not be empty!")
    private int age;

    @Column(name = "email")
    private String email;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    String getEmail() {
        return email;
    }

    void setEmail(final String email) {
        this.email = email;
    }
}
