package pl.jagiela.dto;
// obiekt dto zawierający tylko wybrane informacje na temat eventu pobrane z bazy danych (wykorzystywany przy wyświetlaniu dokładnych informacji na temat eventu.
import java.util.List;
import java.util.Objects;

public class EventInfoDto {

    private Long id;
    private String owner;
    private Long idOwner;
    private String name;
    private String category;
    private int places;
    private int availablePlaces;
    private List<UserListDto> users;
    private int day;
    private String month;
    private int year;
    private String description;
    private String categoryImgUrl;
    private double totalCost;

    //gettery i settery do powyższych pól
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public List<UserListDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserListDto> users) {
        this.users = users;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }

    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventInfoDto)) return false;
        EventInfoDto that = (EventInfoDto) o;
        return getPlaces() == that.getPlaces() && getAvailablePlaces() == that.getAvailablePlaces() && getDay() == that.getDay() && getYear() == that.getYear() && Double.compare(that.getTotalCost(), getTotalCost()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getIdOwner(), that.getIdOwner()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getUsers(), that.getUsers()) && Objects.equals(getMonth(), that.getMonth()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCategoryImgUrl(), that.getCategoryImgUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwner(), getIdOwner(), getName(), getCategory(), getPlaces(), getAvailablePlaces(), getUsers(), getDay(), getMonth(), getYear(), getDescription(), getCategoryImgUrl(), getTotalCost());
    }
}
