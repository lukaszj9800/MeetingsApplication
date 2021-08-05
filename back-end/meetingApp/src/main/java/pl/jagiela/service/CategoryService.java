package pl.jagiela.service;
//Serwis zawierający wszystkie metody które mogą zostać wykonane w kontrolerze i przy użyciu serwisu oraz repozytorium pobrane, zapisane, usunięte lub zaktualizowane w bazie danych
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jagiela.dto.CategoryDto;
import pl.jagiela.mapper.CategoryDtoMapper;
import pl.jagiela.model.Category;
import pl.jagiela.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;

//Serwis ten obsługuje operacje związane z kategoriami
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;


    //wstrzyknięcie poprzez konstruktor repozytorium obsługującego połączenie z bazą danych i operującego na obiektach typu Category
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //pobranie listy wszystkich kategorii przekształconych z obiektu typu Category na CategoryDto w celu pobrania tylko potrzebnych danych
    public List<CategoryDto> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    //dodawanie nowych kategorii i zależnie od nazwy kategorii przypisanie jej danego url obrazka.
    public void addCategory(Category category){
        if(category.getName().equals("Sport")){
            category.setImgUrl("https://www.praca.pl/pressroom/poradniki/960x678/p960_300.jpeg");
        }else if(category.getName().equals("Strajk")){
            category.setImgUrl("https://zycieczestochowy.pl/wp-content/uploads/2017/10/bullhorn-2026013_1280_767x470_acf_cropped.png");
        }else if(category.getName().equals("Przyjęcia")){
            category.setImgUrl("https://www.weranda.pl/data/articles/jak%20zorganizowac%20przyjecie%20w%20ogrodzie.jpg");
        }else if(category.getName().equals("Wycieczki")){
            category.setImgUrl("https://cdn.intercars.eu/files/4/3/0/7/1/43071.jpg");
        }
        categoryRepository.save(category);
    }

    //usuwanie kategorii z podanym id
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    //szukanie kategorii po nazwie
    public CategoryDto findCategory(String name){
        return CategoryDtoMapper.mapToDto(categoryRepository.findByNameContainingIgnoreCase(name));
    }

    //edytowanie kategorii
    public Category editCategory(Category category){
        Category categoryEdited = categoryRepository.getOne(category.getId());
        categoryEdited.setName(category.getName());
        categoryEdited.setDescription(category.getDescription());
        return categoryRepository.save(categoryEdited);
    }
}
