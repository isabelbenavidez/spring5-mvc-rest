package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest extends TestCase {


    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }


    @Test
    public void getAllCustomers() throws Exception{
        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Ingrid");
        customer1.setLastName("Benavides");

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Jorge");
        customer2.setLastName("Benavides");


        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDTOS.size());

    }


    public void testGetCustomerById() throws  Exception{
        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Ingrid");
        customer1.setLastName("Benavides");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);
        assertEquals("Ingrid", customerDTO.getFirstName());

    }
}