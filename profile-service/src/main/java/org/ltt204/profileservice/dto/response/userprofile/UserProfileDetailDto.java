package org.ltt204.profileservice.dto.response.userprofile;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.ltt204.profileservice.dto.response.address.AddressDto;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileDetailDto extends UserProfileDto {
    AddressDto address;

    @Builder
    public UserProfileDetailDto(
            String firstName,
            String lastName,
            String email,
            AddressDto address
    ) {
        super(firstName, lastName, email);
        this.address = address;
    }
}
