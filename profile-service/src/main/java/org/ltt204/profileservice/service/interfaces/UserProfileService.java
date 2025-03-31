package org.ltt204.profileservice.service.interfaces;

import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileUpdateRequestDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDto;

public interface UserProfileService {
    /**
     * Create a new user profile.
     *
     * @param userProfileCreateRequestDto the user profile create request DTO
     * @return the created user profile DTO
     */
    UserProfileDetailDto createUserProfile(UserProfileCreateRequestDto userProfileCreateRequestDto);

    /**
     * Get a user profile by ID.
     *
     * @param id the ID of the user profile
     * @return the user profile DTO
     */
    UserProfileDetailDto getUserProfileById(String id);

    /**
     * Update a user profile by ID.
     *
     * @param id                          the ID of the user profile
     * @param userProfileUpdateRequestDto the user profile update request DTO
     * @return the updated user profile DTO
     */
    UserProfileDto updateUserProfile(String id, UserProfileUpdateRequestDto userProfileUpdateRequestDto);

    /**
     * Delete a user profile by ID.
     *
     * @param id the ID of the user profile
     */
    void deleteUserProfile(String id);
}
