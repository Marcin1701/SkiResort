package pl.skiresort.Model.Projection;

import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.User;

public class UserWriteModel {

    private String name;

    private String surname;

    private int age;

    private String email;

    private String password;

    private CardPass cardPass;


    public UserWriteModel() {

    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public CardPass getCardPass() {
        return cardPass;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setCardPass(final CardPass cardPass) {
        this.cardPass = cardPass;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public User toUser() {
        return new User(name, surname, age, email, password);
    }
}
