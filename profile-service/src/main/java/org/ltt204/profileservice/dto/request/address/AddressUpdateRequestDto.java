package org.ltt204.profileservice.dto.request.address;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.ltt204.profileservice.dto.request.country.CountryCreateRequestDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressUpdateRequestDto {
    String street;
    String ward;
    String district;
    String province;
    String city;
    CountryCreateRequestDto country;
}
