package model;

import java.util.Objects;

public class User {

    private String userName;
    private String password;
    private AccountType type;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.type = AccountType.USER;
    }
    public User(String userName, String password,AccountType type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        User user = (User) o;
        return Objects.equals (userName, user.userName) && Objects.equals (password, user.password) && type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash (userName, password, type);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", type=" + type +
                '}';
    }
}
