package org.ltt204.profileservice.dto.request.userprofile;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.ltt204.profileservice.dto.request.address.AddressCreateRequestDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileCreateRequestDto {
    String firstName;
    String lastName;
    String email;
    AddressCreateRequestDto address;
}
