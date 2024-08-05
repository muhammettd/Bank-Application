package com.projebank.projebanka.dto;

import com.projebank.projebanka.model.Customer;
import org.springframework.stereotype.Component;

@Component

public class CustomerDtoConventer {

    public CustomerDto convert(Customer customer){

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setAddress(customer.getAddress());
        customerDto.setCity(CityDto.valueOf(customer.getCity().name()));
        customerDto.setName(customer.getName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());

        return customerDto;

    }
}
