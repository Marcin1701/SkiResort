package pl.skiresort.Model.Projection;

import pl.skiresort.Model.User;

import java.util.UUID;

public class UserReadModel {

    private final int id;

    private final String name;

    private final String surname;

    private final int age;

    private final String email;

    private final String password;

    private final UUID generatedcode;

    public UserReadModel(final int id, final String name, final String surname, final int age, final String email, final String password, final UUID generatedcode) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.generatedcode = generatedcode;
    }

    public UserReadModel(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.generatedcode = user.getGeneratedcode();
    }

    public int getId() {
        return id;
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

    public UUID getGeneratedcode() {
        return generatedcode;
    }
}
