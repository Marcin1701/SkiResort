package pl.skiresort.Model.Projection;

import pl.skiresort.Model.User;

public class UserReadModel {

    private String name;

    private String surname;

    private int age;

    private String email;

    private String password;

    public UserReadModel(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    void setSurname(final String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    void setAge(final int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    void setEmail(final String email) {
        this.email = email;
    }
}
