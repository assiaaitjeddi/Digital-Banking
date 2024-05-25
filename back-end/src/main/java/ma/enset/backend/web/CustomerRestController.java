package ma.enset.backend.web;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.CustomerDTO;
import ma.enset.backend.entities.Customer;
import ma.enset.backend.exceptions.CustomerNotFoundException;
import ma.enset.backend.services.BankService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class CustomerRestController {
    private BankService bankService;

    @GetMapping(path = "/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> getCustomersDTO() {
        return bankService.getCustomersDTO();
    }

    @GetMapping(path = "/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CustomerDTO getCustomersDTO(@PathVariable Long id) throws CustomerNotFoundException {
        return bankService.getCustomerDTO(id);
    }

    @PostMapping(path = "/add-customer")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO saveCustomerDTO(@RequestBody CustomerDTO customerDTO) {
        return bankService.saveCustomerDTO(customerDTO);
    }

    @PutMapping(path = "/update-customer/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        return bankService.updateCustomerDTO(id, customerDTO);
    }

    @DeleteMapping(path = "/delete-customer/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable Long id) {
        bankService.deleteCustomer(id);
    }
}
