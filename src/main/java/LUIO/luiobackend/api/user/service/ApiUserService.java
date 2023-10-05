package LUIO.luiobackend.api.user.service;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.domain.user.entity.User;
import LUIO.luiobackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiUserService {

    private final UserService userService;

    public void createUser(UserDto userDto) {
        User user = userDto.toEntity();

        userService.saveUser(user);

    }

    public List<UserDto> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.findAllUsers().stream()
                .map(o -> new UserDto(o.getUserName(),o.getUserMbti(),o.getUserImageUrl(),o.getUserIntroduce()))
                .collect(Collectors.toList());
    }
}
