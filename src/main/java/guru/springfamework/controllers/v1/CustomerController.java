package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Api("Este es mi CustomerController (Condtrolador de la entidad Customer(Cliente)")//Esto es para que se visualice en swagger
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value="Obtendrá la lista de Customers", notes = "Estas son algunas notas del API")//Esto es para que se visualice en swagger
    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers(){

        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK);
    }

}