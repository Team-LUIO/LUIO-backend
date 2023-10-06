package LUIO.luiobackend.api.user.service;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.domain.user.entity.User;
import LUIO.luiobackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
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
        return userService.findAllUsers().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public void deleteUser( String userName ) throws InterruptedException, ExecutionException, TimeoutException {
        userService.findByUsername( userName );
        userService.deleteUser( userName );
    }

    public UserDto getUserDtoByUserName(String userName) throws ExecutionException, InterruptedException {
        User user = userService.findByUsername(userName);
        return new UserDto(user);
    }

    public void editUser( String userNameFromPath, UserDto userDto ) throws ExecutionException, InterruptedException {
        User user = userDto.toEntity();
        userService.findByUsername( userNameFromPath );
        userService.saveUser( user );
    }
}
