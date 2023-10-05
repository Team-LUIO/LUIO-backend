package LUIO.luiobackend.api.user.service;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.domain.user.entity.User;
import LUIO.luiobackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserService {

    private final UserService userService;

    public void createUser(UserDto userDto) {
        User user = userDto.toEntity();

        userService.saveUser(user);

    }

    public void deleteUser( String userName ) throws Exception {
        userService.deleteUser( userName );
    }
}
