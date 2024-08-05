package com.projebank.projebanka.dto;

import com.projebank.projebanka.model.City;
import com.projebank.projebanka.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccountRequest {

    private String customerId;
    private Double balance;
    private Currency currency;
    private City city;
}
