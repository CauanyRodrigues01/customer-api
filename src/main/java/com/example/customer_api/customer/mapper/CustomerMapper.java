package com.example.customer_api.customer.mapper;

import java.util.List;

import com.example.customer_api.customer.dto.request.CustomerCreateRequest;
import com.example.customer_api.customer.dto.request.CustomerPatchRequest;
import com.example.customer_api.customer.dto.request.CustomerUpdateRequest;
import com.example.customer_api.customer.dto.response.CustomerDetailsResponse;
import com.example.customer_api.customer.dto.response.CustomerResponse;
import com.example.customer_api.customer.dto.response.CustomerSummary;
import com.example.customer_api.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    // create
    Customer toEntity(CustomerCreateRequest request);

    // update (put)
    void updateEntityFromUpdateRequest(CustomerUpdateRequest request, @MappingTarget Customer customer);


    // update (patch)
    void updateEntityFromPatchRequest(CustomerPatchRequest  request, @MappingTarget Customer customer);

    // reponses
    CustomerResponse toResponse(Customer customer);
    CustomerDetailsResponse toDetailsResponse(Customer customer);
    CustomerSummary toSummary(Customer customer);
    List<CustomerSummary> toSummaryList(List<Customer> customers);

}
