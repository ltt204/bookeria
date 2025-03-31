package org.ltt204.profileservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileUpdateRequestDto;
import org.ltt204.profileservice.dto.response.common.ApplicationResponseDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDto;
import org.ltt204.profileservice.mapper.UserProfileMapper;
import org.ltt204.profileservice.service.implementations.UserProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user-profiles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileServiceImpl profileService;
    UserProfileMapper userProfileMapper;

    @PostMapping
    public ResponseEntity<ApplicationResponseDto<UserProfileDetailDto>> createUserProfile(
            @RequestBody UserProfileCreateRequestDto userProfileCreateRequestDto
    ) {
        var newUser = profileService.createUserProfile(userProfileCreateRequestDto);

        var response = ApplicationResponseDto.success(newUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApplicationResponseDto<UserProfileDetailDto>> getUserProfile(
            @PathVariable String userId
    ) {
        log.info("getUserProfile");
        var userProfile = profileService.getUserProfileById(userId);

        var response = ApplicationResponseDto.success(userProfile);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApplicationResponseDto<UserProfileDto>> updateUserProfile(
            @PathVariable String userId,
            @RequestBody UserProfileUpdateRequestDto userProfileUpdateRequestDto
    ) {
        log.info("updateUserProfile");
        var updatedUser = profileService.updateUserProfile(userId, userProfileUpdateRequestDto);

        var response = ApplicationResponseDto.success(updatedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApplicationResponseDto<Object>> deleteUserProfile(
            @PathVariable String userId
    ) {
        log.info("deleteUserProfile");
        profileService.deleteUserProfile(userId);

        var response = ApplicationResponseDto.success();

        return ResponseEntity.ok(response);
    }
}
