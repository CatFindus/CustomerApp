package ru.puchinets.customerapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.puchinets.customerapp.enums.ContactType;
import ru.puchinets.customerapp.model.entity.ContactData;


public interface ContactDataRepository extends JpaRepository<ContactData, Long> {
    Page<ContactData> findContactDataByTypeAndCustomer_Id(ContactType type, Long customerId, Pageable pageable);
    Page<ContactData> findContactDataByCustomer_Id(Long customerId, Pageable pageable);
}
