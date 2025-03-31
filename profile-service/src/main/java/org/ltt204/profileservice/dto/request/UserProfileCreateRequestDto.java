package org.ltt204.profileservice.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

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
