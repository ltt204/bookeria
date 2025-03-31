package org.ltt204.profileservice.service.implementations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileUpdateRequestDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDto;
import org.ltt204.profileservice.exception.AppException;
import org.ltt204.profileservice.exception.ErrorCode;
import org.ltt204.profileservice.mapper.UserProfileMapper;
import org.ltt204.profileservice.repository.UserProfileRepository;
import org.ltt204.profileservice.service.interfaces.UserProfileService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    @Override
    public UserProfileDetailDto createUserProfile(UserProfileCreateRequestDto userProfileCreateRequestDto) {
        var userProfile = userProfileMapper.toEntity(userProfileCreateRequestDto);
        return userProfileMapper.toDetailDto(userProfileRepository.save(userProfile));
    }

    @Override
    public UserProfileDetailDto getUserProfileById(String id) {
        var userProfile = userProfileRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND, "Cannot find UserProfile with id " + id)
        );

        return userProfileMapper.toDetailDto(userProfile);
    }

    @Override
    public UserProfileDto updateUserProfile(String id, UserProfileUpdateRequestDto userProfileUpdateRequestDto) {
        var userProfile = userProfileRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND, "Cannot find UserProfile with id " + id)
        );

        userProfile = userProfileMapper.toEntity(userProfileUpdateRequestDto);

        var updatedUserProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toDto(updatedUserProfile);
    }

    @Override
    public void deleteUserProfile(String id) {
        var userProfile = userProfileRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND, "Cannot find UserProfile with id " + id)
        );

        userProfileRepository.delete(userProfile);
    }
}
