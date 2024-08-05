package com.projebank.projebanka.dto;

import com.projebank.projebanka.model.Currency;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AccountDto {

    private String id;
    private String customerId;
    private Double balance;
    private Currency currency;

}
