package com.projebank.projebanka.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerRequest extends BaseCustomerRequest {

    private String id;

}
