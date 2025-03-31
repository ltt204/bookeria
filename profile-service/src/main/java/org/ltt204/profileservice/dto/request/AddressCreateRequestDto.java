package org.ltt204.profileservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreateRequestDto {
    String street;
    String ward;
    String district;
    String province;
    String city;
    CountryCreateRequestDto country;
}
