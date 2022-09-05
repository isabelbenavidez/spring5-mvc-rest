package guru.springfamework.api.mapper;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO()throws Exception {

        //given
        Category category = new Category();
        category.setName("Joe");
        category.setId(1L);
        category.setId(1L);

        //When
        CategoryDTO categoroyDTO = categoryMapper.categoryToCategoryDTO(category);

        //Then
        assertEquals(Long.valueOf(1L), categoroyDTO.getId());
        assertEquals("Joe", categoroyDTO.getName());

    }
}
