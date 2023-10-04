package LUIO.luiobackend.api.user.dto;

import LUIO.luiobackend.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userName;

    private String userMbti;

    private String userImageUrl;

    private String userIntroduce;

    public User toEntity() {
        return new User(userName, userMbti, userImageUrl, userIntroduce);
    }

}
