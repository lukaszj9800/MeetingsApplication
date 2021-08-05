package pl.jagiela.mapper;
//serwis konwertujący obiekty typu Category na CategoryDto
import org.springframework.stereotype.Service;
import pl.jagiela.dto.CategoryDto;
import pl.jagiela.model.Category;

@Service
public class CategoryDtoMapper {

    //metoda konwertująca obiekty typu Category które zostaną do niej przekazane na CategoryDto
    public static CategoryDto mapToDto(Category category){

        CategoryDto categoryDto = new CategoryDto();//stworzenie nowego obiektu który zostanie zwrócony

        //przypisanie wartości pól(z wykorzystaniem setterów i getterów) do nowo utworzonego obiektu który ma zostać zwrócony
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());

        return categoryDto;
    }
}
