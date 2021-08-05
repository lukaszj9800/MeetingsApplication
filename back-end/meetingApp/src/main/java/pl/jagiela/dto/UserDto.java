package pl.jagiela.dto;
// obiekt dto zawierający tylko wybrane informacje na temat użytkownika które zostały pobrane z bazy danych
public class UserDto {


    private Long id;
    private String firstname;
    private String lastname;
    private String gender;
    private String imgUrl;



    //gettery i settery dla powyższych pól
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
