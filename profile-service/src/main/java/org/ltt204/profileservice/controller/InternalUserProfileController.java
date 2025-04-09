package org.ltt204.profileservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.response.common.ApplicationResponseDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.service.implementations.UserProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    UserProfileServiceImpl profileService;

    @PostMapping("/user-profiles")
    public ResponseEntity<ApplicationResponseDto<UserProfileDetailDto>> createUserProfile(
            @RequestBody UserProfileCreateRequestDto userProfileCreateRequestDto
    ) {
        var newUser = profileService.createUserProfile(userProfileCreateRequestDto);

        var response = ApplicationResponseDto.success(newUser);

        return ResponseEntity.ok(response);
    }
}
