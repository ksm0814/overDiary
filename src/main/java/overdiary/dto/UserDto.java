package overdiary.dto;


import org.hibernate.validator.constraints.Length;
import overdiary.domain.User;

import java.util.Objects;

public class UserDto {

    @Length(min = 4, max= 15)
    private String userId;

    @Length(min = 1)
    private String name;

    @Length(min = 4, max = 20)
    private String password;

    private String email;

    public UserDto(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;


    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toUser() {
        return new User(userId, name, password, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, email);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
