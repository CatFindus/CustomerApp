package ru.puchinets.customerapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.customerapp.enums.ContactType;
import ru.puchinets.customerapp.model.entity.ContactData;

import java.util.Optional;


public interface ContactDataService {

    Optional<ContactData> create(ContactData contactData, Long customerId);
    Page<ContactData> getContactsByCustomerId(Pageable pageable, ContactType type, Long customerId);
    boolean deleteById(Long contactId);
    Optional<ContactData> updateContact(Long contactId, ContactData contactData);
}
