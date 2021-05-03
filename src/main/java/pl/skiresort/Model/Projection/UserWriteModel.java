package pl.skiresort.Model.Projection;

import org.hibernate.validator.constraints.Range;
import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

// DTO
public class UserWriteModel {

    @NotBlank(message="Your name must not be empty!")
    private String name;

    @NotBlank(message = "Your surname must not be empty!")
    private String surname;

    @Range(min = 1, message="Your age can not be equal 0!")
    private int age;

    @NotBlank(message = "Your email must not be empty!")
    private String email;

    @NotBlank(message = "Your password must not be empty!")
    private String password;

    private CardPass cardPass;

    private UUID generatedCode;

    public UserWriteModel() {

    }

    public UserWriteModel(final String name, final String surname, final int age, final String email, final String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
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

    public UUID getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(final UUID generatedCode) {
        this.generatedCode = generatedCode;
    }

    public User toUser() {
        return new User(name, surname, age, email, password);
    }


}
