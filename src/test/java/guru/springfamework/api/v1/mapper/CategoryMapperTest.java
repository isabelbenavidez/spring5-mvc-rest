package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

import javax.print.attribute.standard.MediaSize;

import static junit.framework.TestCase.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO()throws Exception {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);


        //When
        CategoryDTO categoroyDTO = categoryMapper.categoryToCategoryDTO(category);

        //Then
        assertEquals(Long.valueOf(ID), categoroyDTO.getId());
        assertEquals(NAME, categoroyDTO.getName());

    }
}
