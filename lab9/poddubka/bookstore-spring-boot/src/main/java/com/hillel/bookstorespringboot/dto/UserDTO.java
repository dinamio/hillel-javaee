package com.hillel.bookstorespringboot.dto;

public class UserDTO {

    private String id;
    private String userName;
    private String userPassword;
    private Boolean enable;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
