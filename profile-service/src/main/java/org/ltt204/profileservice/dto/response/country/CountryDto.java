package org.ltt204.profileservice.dto.response.country;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryDto {
    String countryName;
    String countryCode;
    String zipCode;
    String postalCode;
}
