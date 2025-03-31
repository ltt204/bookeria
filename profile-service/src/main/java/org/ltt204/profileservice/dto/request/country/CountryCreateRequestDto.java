package org.ltt204.profileservice.dto.request.country;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryCreateRequestDto {
    String countryName;
    String countryCode;
    String zipCode;
    String postalCode;
}
