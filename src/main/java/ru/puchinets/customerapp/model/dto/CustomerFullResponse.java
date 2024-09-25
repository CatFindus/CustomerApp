package ru.puchinets.customerapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.puchinets.customerapp.model.entity.ContactData;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFullResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ContactData> contacts;
}
