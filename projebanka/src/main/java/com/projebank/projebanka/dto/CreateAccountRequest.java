package com.projebank.projebanka.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountRequest extends BaseAccountRequest {

    private String id;
}
