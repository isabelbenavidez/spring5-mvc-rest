package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest extends TestCase {

    public static final String NAME = "Julio";

    @Mock//@Mock crea un simulacro
    CategoryService categoryService;

    @InjectMocks//InjectMocks crea una instancia de la clase e inyecta las simulaciones creadas con anotaciones @Mock, @Spy
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListCategories() throws Exception{
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1l);
        category1.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category1.setId(2l);
        category1.setName("César");

        List<CategoryDTO> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));

    }

    @Test
    public void testGetCategoryByName() throws Exception{
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1l);
        category1.setName(NAME);

        when (categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get("/api/v1/categories/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }



}