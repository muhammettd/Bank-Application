package com.projebank.projebanka.controller;

import com.projebank.projebanka.dto.CreateCustomerRequest;
import com.projebank.projebanka.dto.CustomerDto;
import com.projebank.projebanka.dto.UpdateCustomerRequest;
import com.projebank.projebanka.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerDtoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id,
                                                      @RequestBody UpdateCustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));

    }

    
}
