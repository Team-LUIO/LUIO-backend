package LUIO.luiobackend.api.user.dto;

import LUIO.luiobackend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;

    private String userMbti;

    private String userImageUrl;

    private String userIntroduce;

    public User toEntity() {
        return new User(userName, userMbti, userImageUrl, userIntroduce);
    }

    public UserDto(User user) {
        userName = user.getUserName();
        userMbti = user.getUserMbti();
        userImageUrl = user.getUserImageUrl();
        userIntroduce = user.getUserIntroduce();
    }

}
