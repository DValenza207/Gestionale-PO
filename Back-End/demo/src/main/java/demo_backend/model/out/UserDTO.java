package demo_backend.model.out;

import com.sun.istack.NotNull;

import java.util.Objects;

public class UserDTO {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return name.equals(userDTO.name) && password.equals(userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
