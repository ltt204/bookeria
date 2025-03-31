package org.ltt204.profileservice.mapper;

import org.ltt204.profileservice.dto.request.address.AddressCreateRequestDto;
import org.ltt204.profileservice.dto.request.address.AddressUpdateRequestDto;
import org.ltt204.profileservice.dto.response.address.AddressDto;
import org.ltt204.profileservice.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface AddressMapper {
    AddressDto toDto(Address address);

    @Named("createAddress")
    @Mapping(target = "country", source = "addressCreateRequestDto.country", qualifiedByName = "createCountry")
    Address toEntity(AddressCreateRequestDto addressCreateRequestDto);

    @Named("updateAddress")
    @Mapping(target = "country", source = "addressUpdateRequestDto.country", qualifiedByName = "updateCountry")
    Address toEntity(AddressUpdateRequestDto addressUpdateRequestDto);
}
