package ru.lynant.firstproject.models;

import javax.validation.constraints.*;

public class Person {
    private int client_id;
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Za-z]+ [A-Za-z]+ [A-Za-z]+",
            message = "Name must be entered in the following format: Firstname Surname Patronymic")
    private String name;

    @Min(value = 1890, message = "year birth should be > 1890")
    @Max(value = 2022, message = "year birth should be < 2022")
    private int yearBirth;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Incorrect mail. Should be ***@***.***")
    private String email;

    public Person(int id, String name, int yearBirth, String email) {
        this.client_id = id;
        this.name = name;
        this.yearBirth = yearBirth;
        this.email = email;
    }

    public Person() {

    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
