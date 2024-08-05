package com.projebank.projebanka.service;

import com.projebank.projebanka.dto.CreateCustomerRequest;
import com.projebank.projebanka.dto.CustomerDto;
import com.projebank.projebanka.dto.CustomerDtoConventer;
import com.projebank.projebanka.dto.UpdateCustomerRequest;
import com.projebank.projebanka.model.City;
import com.projebank.projebanka.model.Customer;
import com.projebank.projebanka.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConventer customerDtoConventer;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConventer customerDtoConventer) {
        this.customerRepository = customerRepository;
        this.customerDtoConventer = customerDtoConventer;
    }

    public CustomerDto createCustomer(CreateCustomerRequest  customerRequest){
        Customer customer = new Customer();
        customer.setAddress(customerRequest.getAddress());
        customer.setName(customerRequest.getName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setCity(City.valueOf(customerRequest.getCity().name()));

        customerRepository.save(customer);


        return customerDtoConventer.convert(customer);

    }

    public List<CustomerDto> getAllCustomers() {
    List<Customer> customerList = customerRepository.findAll();

    List<CustomerDto> customerDtoList = new ArrayList<>();

    for (Customer customer: customerList){
        customerDtoList.add(customerDtoConventer.convert(customer));
    }

    return customerDtoList;

    }

    public CustomerDto getCustomerDtoById(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customerDtoConventer::convert).orElse(new CustomerDto());
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomer(String id, UpdateCustomerRequest customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.ifPresent(customer -> {
                customer.setName(customerRequest.getName());
                customer.setCity(City.valueOf(customerRequest.getCity().name()));
                customer.setDateOfBirth(customerRequest.getDateOfBirth());
                customer.setAddress(customer.getAddress());
                customerRepository.save(customer);
        });

        return customerOptional.map(customerDtoConventer::convert).orElse(new CustomerDto());
    }

    protected Customer getCustomerById(String id){

        return customerRepository.findById(id).orElse(new Customer());
    }

    protected Customer getCustomerById2(String id){

        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}
