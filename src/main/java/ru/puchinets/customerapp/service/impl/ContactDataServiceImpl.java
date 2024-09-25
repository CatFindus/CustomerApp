package ru.puchinets.customerapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.puchinets.customerapp.enums.ContactType;
import ru.puchinets.customerapp.model.entity.ContactData;
import ru.puchinets.customerapp.model.entity.Customer;
import ru.puchinets.customerapp.repository.ContactDataRepository;
import ru.puchinets.customerapp.repository.CustomerRepository;
import ru.puchinets.customerapp.service.ContactDataService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactDataServiceImpl implements ContactDataService {

    private final ContactDataRepository contactDataRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Optional<ContactData> create(ContactData contactData, Long customerId) {
        Optional<Customer> mayBeCustomer = customerRepository.findById(customerId);
        if (mayBeCustomer.isPresent() && contactData!=null) {
            contactData.setCustomer(mayBeCustomer.get());
            contactDataRepository.saveAndFlush(contactData);
            return Optional.of(contactData);
        }
        else return Optional.empty();
    }

    @Override
    public Page<ContactData> getContactsByCustomerId(Pageable pageable, ContactType type, Long customerId) {
        if (type == null) {
            return contactDataRepository.findContactDataByCustomer_Id(customerId, pageable);
        } else {
            return contactDataRepository.findContactDataByTypeAndCustomer_Id(type, customerId, pageable);
        }
    }

    @Override
    public boolean deleteById(Long contactId) {
        boolean isExists = contactDataRepository.existsById(contactId);
        if (isExists) contactDataRepository.deleteById(contactId);
        return isExists;
    }

    @Override
    public Optional<ContactData> updateContact(Long contactId, ContactData contactData) {
        Optional<ContactData> mayBeContact = contactDataRepository.findById(contactId);
        if (mayBeContact.isPresent()) {
            if (contactData==null) return mayBeContact;
            ContactData dbContact = mayBeContact.get();
            if (contactData.getCustomer()!=null) dbContact.setCustomer(contactData.getCustomer());
            if (contactData.getType()!=null) dbContact.setType(contactData.getType());
            contactDataRepository.saveAndFlush(dbContact);
            return Optional.of(dbContact);
        }
        else return Optional.empty();
    }
}
