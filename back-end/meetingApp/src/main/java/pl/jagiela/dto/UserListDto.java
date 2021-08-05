package pl.jagiela.dto;
// obiekt dto zawierający tylko wybrane informacje na temat użytkowników które zostały pobrane z bazy danych(wykorzystywany przy wyświetlaniu listy użytkowników zapisanych do danego eventu
import java.util.Objects;


public class UserListDto {

    private Long id;
    private String name;
    private String imgUrl;
    private String email;
    private String phone;

    //gettery i settery dla powyższych pól
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserListDto)) return false;
        UserListDto that = (UserListDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getImgUrl(), that.getImgUrl()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getImgUrl(), getEmail(), getPhone());
    }
}
