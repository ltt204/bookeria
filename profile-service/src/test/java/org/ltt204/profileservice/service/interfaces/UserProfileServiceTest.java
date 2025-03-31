package org.ltt204.profileservice.service.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ltt204.profileservice.dto.request.address.AddressCreateRequestDto;
import org.ltt204.profileservice.dto.request.country.CountryCreateRequestDto;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.entity.Address;
import org.ltt204.profileservice.entity.Country;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserProfileServiceTest {
    @MockitoBean
    UserProfileService userProfileService;

    private UserProfileCreateRequestDto userProfileCreateRequestDto;
    private UserProfileDetailDto userProfileDto;

    private Address address;
    private Country country;

    @BeforeEach
    void setUp() {
        var country = CountryCreateRequestDto.builder()
                .countryName("Vietnam")
                .countryCode("VN")
                .zipCode("123124")
                .postalCode("123456")
                .build();

        var address = AddressCreateRequestDto.builder()
                .street("123 Main St")
                .city("Anytown")
                .ward("Ward 1")
                .district("District 1")
                .province("Province 1")
                .country(country)
                .build();

        userProfileCreateRequestDto = UserProfileCreateRequestDto.builder()
                .firstName("john")
                .lastName("doe")
                .email("johndoe@test.example")
                .address(address)
                .build();

        userProfileDto = UserProfileDetailDto.builder()
                .firstName("john")
                .lastName("doe")
                .email("johndoe@test.example")
                .build();
    }

    @Test
    void createUserProfile_validRequest_success() {
        // GIVEN
        var response = userProfileService.createUserProfile(userProfileCreateRequestDto);

        // WHEN
        when(userProfileService.createUserProfile(any())).thenReturn(userProfileDto);

        // THEN
        assertThat(response.getEmail()).isEqualTo(userProfileDto.getEmail());
        assertThat(response.getAddress()).isNotNull();
        assertThat(response.getAddress().getCountry()).isNotNull();
    }

    @Test
    void getUserProfileById() {
    }

    @Test
    void updateUserProfile() {
    }

    @Test
    void deleteUserProfile() {
    }
}