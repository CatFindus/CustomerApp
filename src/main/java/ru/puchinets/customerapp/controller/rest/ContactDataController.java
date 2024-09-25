package ru.puchinets.customerapp.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.customerapp.enums.ContactType;
import ru.puchinets.customerapp.model.entity.ContactData;
import ru.puchinets.customerapp.service.ContactDataService;

import static ru.puchinets.customerapp.constants.Constants.PAGINATION_EXAMPLE;


@Controller
@RequestMapping("/api/v1/customers/{id}/contacts")
@RequiredArgsConstructor
@Tag(name = "Contacts", description = "Operations related to contacts of customers in the Customer App")
public class ContactDataController {

    private final ContactDataService contactDataService;

    @Operation(summary = "Add contact data", description = "Add contact information to customer and return added contact data")
    @ApiResponse(responseCode = "200", description = "Contact added successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContactData.class)))
    @ApiResponse(responseCode = "404", description = "Customer was not found")
    @PutMapping
    ResponseEntity<ContactData> addContact(@PathVariable("id") Long customerId, @RequestBody ContactData contactData) {
        return contactDataService.create(contactData, customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all contacts of customer", description = "Returns a paginated list of all contacts available for customer")
    @GetMapping
    ResponseEntity<Page<ContactData>> getAllContacts(@Parameter(description = "ID of customer")
                                                     @PathVariable("id") Long customerId,
                                                     @Parameter(name = "pagination parameter",
                                                             description = "Pagination and sorting parameters", example = PAGINATION_EXAMPLE)
                                                     @PageableDefault(sort = "id") Pageable pageable,
                                                     @Parameter(description = "Type of contact. Returns all types unless specified.", required = false)
                                                     @RequestParam(value = "type", required = false) ContactType type) {
        return ResponseEntity.ok(contactDataService.getContactsByCustomerId(pageable, type, customerId));
    }
}
