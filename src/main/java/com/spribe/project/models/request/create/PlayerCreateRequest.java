package com.spribe.project.models.request.create;

import com.spribe.project.enums.Gender;
import com.spribe.project.enums.Role;

import java.util.Objects;

public class PlayerCreateRequest {
    private String age;
    private Gender gender;
    private String login;
    private String password;
    private Role role;
    private String screenName;

    public PlayerCreateRequest() {
    }

    public PlayerCreateRequest(String age, Gender gender, String login, String password, Role role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerCreateRequest that = (PlayerCreateRequest) o;
        return Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(screenName, that.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "PlayerCreateRequest{" +
                "age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
