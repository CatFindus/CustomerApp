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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.customerapp.model.dto.CustomerFullResponse;
import ru.puchinets.customerapp.model.dto.CustomerRequest;
import ru.puchinets.customerapp.model.dto.CustomerListResponse;
import ru.puchinets.customerapp.service.CustomerService;

import static ru.puchinets.customerapp.constants.Constants.PAGINATION_EXAMPLE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers", description = "Operations related to customers in the Customers App")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "Get all customers", description = "Returns a paginated list of all customers available")
    @GetMapping
    ResponseEntity<Page<CustomerListResponse>> getPage(@Parameter(name = "Pagination parameter",
                                                       description = "Pagination and sorting parameters", example = PAGINATION_EXAMPLE)
                                                       Pageable pageable) {
        return ResponseEntity.ok(customerService.getAll(pageable));
    }

    @Operation(summary = "Get customer by ID", description = "Find customer by ID. Required ID of customer")
    @ApiResponse(responseCode = "200", description = "Customer found successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerFullResponse.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/{id}")
    ResponseEntity<CustomerFullResponse> getById(@Parameter(description = "ID of customer")
                                                 @PathVariable("id") Long id) {
        return customerService
                .getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a customer", description = "Create a new customer and return the created object")
    @ApiResponse(responseCode = "201", description = "Customer created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerListResponse.class)))
    @PostMapping
    ResponseEntity<CustomerListResponse> create(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.create(customerRequest), HttpStatus.CREATED);
    }


}
