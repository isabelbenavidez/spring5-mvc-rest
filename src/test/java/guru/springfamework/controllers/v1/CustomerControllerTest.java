package guru.springfamework.controllers.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static guru.springfamework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void testListCustomers() throws Exception{
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Ingrid");
        customer1.setLastName("Benavides");
        customer1.setCustomerUrl("/api/v1/customer/1");


        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Carolina");
        customer2.setLastName("Benavides");
        customer2.setCustomerUrl("/api/v1/customer/2");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void testGetCustomerById() throws Exception{

        //given

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Ingrid");
        customer1.setLastName("Benavides");
        customer1.setCustomerUrl("/api/v1/customer/1");


        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1));

        //when
        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

                //.andExpect(jsonPath("$.firstName", equalTo("Ingrid")));

    }

    @Test
    public void createNewCustomer() throws Exception{
        //given
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setFirstName("Jorge");
        customerDto.setLastName("Benavides");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(customerDto.getFirstName());
        returnDto.setLastName(customerDto.getLastName());
        returnDto.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(customerDto)).thenReturn(returnDto);

        //when/then
        mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Jorge")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));



    }

    @Test
    public void testUpdateCustomer() throws Exception{
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Panchis");
        customerDTO.setLastName("Tenganan");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                        .andExpect(status().isOk())
                        //.andExpect(jsonPath("$.firstname", equalTo("Panchis")))
                        //.andExpect(jsonPath("$.lastname", equalTo("Tenganan")))
                        .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));




    }


}
