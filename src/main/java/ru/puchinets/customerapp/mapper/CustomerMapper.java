package ru.puchinets.customerapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.puchinets.customerapp.model.dto.CustomerFullResponse;
import ru.puchinets.customerapp.model.dto.CustomerListResponse;
import ru.puchinets.customerapp.model.dto.CustomerRequest;
import ru.puchinets.customerapp.model.entity.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer dtoToEntity(CustomerRequest request);
    CustomerListResponse entityToListResponse(Customer customer);
    CustomerFullResponse entityToFullResponse(Customer customer);
    default Customer updateFields(Customer customer, CustomerRequest request) {
        if (request==null) return customer;
        if (request.getFirstName()!=null && !request.getFirstName().isBlank()) {
            customer.setFirstName(request.getFirstName());
        }
        if (request.getLastName()!=null && !request.getLastName().isBlank()) {
            customer.setLastName(request.getLastName());
        }
        return customer;
    }
}
