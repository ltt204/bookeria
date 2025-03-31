package org.ltt204.profileservice.mapper;

import org.ltt204.profileservice.dto.request.country.CountryCreateRequestDto;
import org.ltt204.profileservice.dto.request.country.CountryUpdateRequestDto;
import org.ltt204.profileservice.dto.response.country.CountryDto;
import org.ltt204.profileservice.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(Country country);
    List<CountryDto> toDtoList(List<Country> countries);

    @Named("createCountry")
    Country toEntity(CountryCreateRequestDto countryCreateRequestDto);

    @Named("updateCountry")
    Country toEntity(CountryUpdateRequestDto countryUpdateRequestDto);
}
