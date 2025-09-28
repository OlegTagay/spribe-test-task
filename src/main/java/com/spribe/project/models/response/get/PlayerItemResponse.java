package com.spribe.project.models.response.get;

import java.util.Objects;

public class PlayerItemResponse {
    private Integer age;
    private String gender;
    private Long id;
    private String role;
    private String screenName;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
        PlayerItemResponse that = (PlayerItemResponse) o;
        return Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(id, that.id) && Objects.equals(role, that.role) && Objects.equals(screenName, that.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, role, screenName);
    }

    @Override
    public String toString() {
        return "PlayerItemResponse{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
