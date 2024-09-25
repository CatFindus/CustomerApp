package ru.puchinets.customerapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListResponse {
    private Long id;
    private String firstName;
    private String lastName;
}
