package pl.skiresort.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name= "clients")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Your name must not be empty!")
    private String name;

    @NotBlank(message = "Your surname must not be empty!")
    private String surname;

    @NotNull(message = "Your age must not be empty!")
    private int age;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_pass_id", referencedColumnName = "id")
    private CardPass cardPass;

    public User() {

    }

    public User(String name, String surname, int age, String email) {
        this(name, surname, age, email, null);
    }

    public User(String name, String surname, int age, String email, CardPass cardPass) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.cardPass = cardPass;
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
