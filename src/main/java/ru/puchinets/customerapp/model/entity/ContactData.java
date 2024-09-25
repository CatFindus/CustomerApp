package ru.puchinets.customerapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.puchinets.customerapp.enums.ContactType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contacts", schema = "customer_management")
public class ContactData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name =  "customer_id")
    @JsonIgnore
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private ContactType type;
    private String data;
}
