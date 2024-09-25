package ru.puchinets.customerapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.puchinets.customerapp.mapper.CustomerMapper;
import ru.puchinets.customerapp.model.dto.CustomerFullResponse;
import ru.puchinets.customerapp.model.dto.CustomerRequest;
import ru.puchinets.customerapp.model.dto.CustomerListResponse;
import ru.puchinets.customerapp.model.entity.Customer;
import ru.puchinets.customerapp.repository.CustomerRepository;
import ru.puchinets.customerapp.service.CustomerService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Page<CustomerListResponse> getAll(Pageable pageable) {
        return customerRepository
                .findAll(pageable)
                .map(customerMapper::entityToListResponse);
    }

    @Override
    public Optional<CustomerFullResponse> getById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::entityToFullResponse);
    }

    @Override
    public CustomerListResponse create(CustomerRequest customerRequest) {
        Customer customer = customerMapper.dtoToEntity(customerRequest);
        customer = customerRepository.save(customer);
        return customerMapper.entityToListResponse(customer);
    }

    @Override
    public Optional<CustomerListResponse> updateById(Long id, CustomerRequest customerRequest) {
        return customerRepository
                .findById(id)
                .map(customer -> customerMapper.updateFields(customer, customerRequest))
                .map(customerRepository::saveAndFlush)
                .map(customerMapper::entityToListResponse);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = customerRepository.existsById(id);
        customerRepository.deleteById(id);
        return isDeleted;
    }
}
