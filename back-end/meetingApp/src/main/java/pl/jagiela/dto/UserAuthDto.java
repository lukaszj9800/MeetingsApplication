package pl.jagiela.dto;

// obiekt dto zawierający tylko wybrane informacje na temat danych uwierzytelniających które zostały pobrane z bazy danych
import java.util.Objects;

public class UserAuthDto {

    private Long id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;
    private Long currentUserId;

    //gettery i settery do powyższych pól
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthDto)) return false;
        UserAuthDto that = (UserAuthDto) o;
        return isActive() == that.isActive() && Objects.equals(getId(), that.getId()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getRoles(), that.getRoles()) && Objects.equals(getCurrentUserId(), that.getCurrentUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), isActive(), getRoles(), getCurrentUserId());
    }
}
