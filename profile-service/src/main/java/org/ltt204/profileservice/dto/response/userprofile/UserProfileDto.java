package org.ltt204.profileservice.dto.response.userprofile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileDto {
    String firstName;
    String lastName;
    String email;
}
