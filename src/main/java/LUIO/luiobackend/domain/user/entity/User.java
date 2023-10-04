package LUIO.luiobackend.domain.user.entity;

import lombok.Getter;

@Getter
public class User {

    private String userName;

    private String userMbti;

    private String userImageUrl;

    private String userIntroduce;

    public User(String userName, String userMbti, String userImageUrl, String userIntroduce) {
        this.userName = userName;
        this.userMbti = userMbti;
        this.userImageUrl = userImageUrl;
        this.userIntroduce = userIntroduce;
    }
}
