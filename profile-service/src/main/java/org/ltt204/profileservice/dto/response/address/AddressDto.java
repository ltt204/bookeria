package org.ltt204.profileservice.dto.response.address;

import org.ltt204.profileservice.dto.response.country.CountryDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDto {
    String street;
    String ward;
    String district;
    String province;
    String city;
    String zip;
    CountryDto country;
}
