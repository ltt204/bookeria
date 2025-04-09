package org.ltt204.profileservice.mapper;

import org.ltt204.profileservice.dto.request.userprofile.UserProfileCreateRequestDto;
import org.ltt204.profileservice.dto.request.userprofile.UserProfileUpdateRequestDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDetailDto;
import org.ltt204.profileservice.dto.response.userprofile.UserProfileDto;
import org.ltt204.profileservice.entity.UserProfile;
import org.ltt204.profileservice.events.consumers.UserCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserProfileMapper {
    UserProfileDto toDto(UserProfile userProfile);

    UserProfileDetailDto toDetailDto(UserProfile userProfile);

    List<UserProfileDto> toListDto(List<UserProfile> userProfiles);

    @Mapping(target = "address", source = "userProfileCreateRequestDto.address", qualifiedByName = "createAddress")
    UserProfile toEntity(UserProfileCreateRequestDto userProfileCreateRequestDto);

    @Mapping(target = "address", source = "userProfileUpdateRequestDto.address", qualifiedByName = "updateAddress")
    UserProfile toEntity(UserProfileUpdateRequestDto userProfileUpdateRequestDto);
}
