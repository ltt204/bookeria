package org.ltt204.profileservice.dto.request.userprofile;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.ltt204.profileservice.dto.request.address.AddressUpdateRequestDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileUpdateRequestDto {
    String firstName;
    String lastName;
    String email;
    AddressUpdateRequestDto address;
}
