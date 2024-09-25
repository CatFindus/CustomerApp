package ru.puchinets.customerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.puchinets.customerapp.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
