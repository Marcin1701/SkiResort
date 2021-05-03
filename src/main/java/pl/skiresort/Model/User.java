package pl.skiresort.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


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

    @NotNull(message = "Your password must not be empty!")
    private String password;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_pass_id", referencedColumnName = "id")
    private CardPass cardPass;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private UUID generatedcode;

    public User() {

    }

    public User(String name, String surname, int age, String email, String password) {
        this(name, surname, age, email, password, null, UUID.randomUUID());
    }

    public User(String name, String surname, int age, String email, String password, CardPass cardPass, UUID generatedcode) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.cardPass = cardPass;
        this.password = password;
        this.generatedcode = generatedcode;
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
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

    public void setAge(final int age) {
        this.age = age;
    }

    public CardPass getCardPass() {
        return cardPass;
    }

    public void setCardPass(final CardPass cardPass) {
        this.cardPass = cardPass;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(final Provider provider) {
        this.provider = provider;
    }

    public UUID getGeneratedcode() {
        return generatedcode;
    }

    public void setGeneratedcode(final UUID generatedcode) {
        this.generatedcode = generatedcode;
    }
}
