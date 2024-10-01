package org.example.payticketcinema.mapping;

import org.example.payticketcinema.model.dto.UserForLk;
import org.example.payticketcinema.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserForLk toDto (User user);
    User toEntity (UserForLk user);

    List<UserForLk> toDtoList (List<User> users);
    List<User> toEntityList (List<UserForLk> users);
}
