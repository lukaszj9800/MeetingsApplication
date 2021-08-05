package pl.jagiela.dto;
//obiekt dto zawierający tylko wybrane informacje na temat kategorii które zostały pobrane z bazy danych
import pl.jagiela.model.Event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private Long id;
    private String name;
    private String description;



    //gettery i settery do podanych wyżej pól
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
