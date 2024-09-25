package ru.puchinets.customerapp.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.customerapp.model.dto.CustomerFullResponse;
import ru.puchinets.customerapp.model.dto.CustomerRequest;
import ru.puchinets.customerapp.model.dto.CustomerListResponse;

import java.util.Optional;

public interface CustomerService {
    Page<CustomerListResponse> getAll(Pageable pageable);
    Optional<CustomerFullResponse> getById(Long id);
    CustomerListResponse create(CustomerRequest customerRequest);
    Optional<CustomerListResponse> updateById(Long id, CustomerRequest customerRequest);
    boolean deleteById(Long id);
}
