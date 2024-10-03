package org.example.payticketcinema.mapping;

import org.example.payticketcinema.model.dto.UserAuthDto;
import org.example.payticketcinema.model.dto.UserForLkDto;
import org.example.payticketcinema.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserForLkDto toDto (User user);

    UserAuthDto toUserAuthDto(User user);

    User toEntity (UserForLkDto user);
    User toUserFromAuthDto(UserAuthDto userAuthDto);


    List<UserForLkDto> toDtoList (List<User> users);
    List<User> toEntityList (List<UserForLkDto> users);
}
