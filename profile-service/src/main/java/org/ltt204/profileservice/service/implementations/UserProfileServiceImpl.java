package org.ltt204.profileservice.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileUpdateRequestDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDto;
import org.ltt204.profileservice.entity.UserProfile;
import org.ltt204.profileservice.events.consumers.UserCreatedEvent;
import org.ltt204.profileservice.events.consumers.UserDeletedEvent;
import org.ltt204.profileservice.events.consumers.UserUpdatedEvent;
import org.ltt204.profileservice.exception.AppException;
import org.ltt204.profileservice.exception.ErrorCode;
import org.ltt204.profileservice.mapper.UserProfileMapper;
import org.ltt204.profileservice.repository.UserProfileRepository;
import org.ltt204.profileservice.service.interfaces.UserProfileService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    @Override
    @Transactional
    public void createUserProfileFromEvent(UserCreatedEvent userCreatedEvent) {
        log.info("Received UserCreatedEvent: {}", userCreatedEvent.getUserId());

        if (userProfileRepository.existsById(userCreatedEvent.getUserId())) {
            throw new AppException(ErrorCode.CONFLICT, "Profile is already created for user with id " + userCreatedEvent.getUserId());
        }

        var userProfile = UserProfile.builder()
                .id(userCreatedEvent.getUserId())
                .email(userCreatedEvent.getUsername())
                .firstName(userCreatedEvent.getFirstname())
                .lastName(userCreatedEvent.getLastname())
                .dateOfBirth(userCreatedEvent.getDateOfBirth())
                .build();

        userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional
    public void updateUserProfile(UserUpdatedEvent userUpdatedEvent) {
        log.info("Received UserUpdatedEvent: {}", userUpdatedEvent.getUserId());

        if (!userProfileRepository.existsById(userUpdatedEvent.getUserId())) {
            throw new AppException(ErrorCode.CONFLICT, String.format("Profile with %s can not be found ", userUpdatedEvent.getUserId()));
        }

        var userProfile = UserProfile.builder()
                .id(userUpdatedEvent.getUserId())
                .email(userUpdatedEvent.getUsername())
                .firstName(userUpdatedEvent.getFirstname())
                .lastName(userUpdatedEvent.getLastname())
                .dateOfBirth(userUpdatedEvent.getDateOfBirth())
                .build();

        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteUserProfile(UserDeletedEvent userDeletedEvent) {
        log.info("Received UserDeletedEvent: {}", userDeletedEvent.getUserId());

        if (!userProfileRepository.existsById(userDeletedEvent.getUserId())) {
            throw new AppException(ErrorCode.CONFLICT, String.format("Profile with %s can not be found ", userDeletedEvent.getUserId()));
        }

        userProfileRepository.deleteById(userDeletedEvent.getUserId());
    }

    @Override
    public UserProfileDetailDto createUserProfile(UserProfileCreateRequestDto userProfileCreateRequestDto) {
        var userProfile = userProfileMapper.toEntity(userProfileCreateRequestDto);
        return userProfileMapper.toDetailDto(userProfileRepository.save(userProfile));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileDto> getAllUserProfiles(Pageable pageable) {
        return userProfileRepository.findAll(
                        PageRequest.of(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                pageable.getSortOr(
                                        Sort.by(Sort.Direction.ASC, "firstName")
                                )
                        )
                )
                .stream().map(
                        userProfileMapper::toDto
                ).toList();
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
