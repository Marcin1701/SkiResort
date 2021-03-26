package pl.skiresort.Model.Projection;

import pl.skiresort.Model.CardPass;

public class UserWriteModel {

    private String name;

    private String surname;

    private int age;

    private String email;

    private CardPass cardPass;

    public UserWriteModel() {

    }

    UserWriteModel(final String name, final String surname, final int age, final String email) {
        this(name, surname, age, email, null);
    }

    UserWriteModel(final String name, final String surname, final int age, final String email, final CardPass cardPass) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.cardPass = cardPass;
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

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public CardPass getCardPass() {
        return cardPass;
    }

    public void setCardPass(final CardPass cardPass) {
        this.cardPass = cardPass;
    }
}
