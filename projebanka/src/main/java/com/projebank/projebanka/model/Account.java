package com.projebank.projebanka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Account {

    @Id
    private String id;

    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;
}
