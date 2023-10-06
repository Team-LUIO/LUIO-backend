package LUIO.luiobackend.api.user.controller;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.api.user.service.ApiUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiUserController {

    private final ApiUserService apiUserService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        apiUserService.createUser(userDto);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDto> getUserDtoByUserName(@PathVariable String userName)
            throws ExecutionException, InterruptedException {
        UserDto userDto = apiUserService.getUserDtoByUserName(userName);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws ExecutionException, InterruptedException {
        List<UserDto> userDtoList = apiUserService.getAllUsers();

        return ResponseEntity.ok(userDtoList);
    }

    @PatchMapping("/user/{userName}")
    public ResponseEntity<?> editUser( @PathVariable String userName, @RequestBody UserDto userDto ) throws ExecutionException, InterruptedException, TimeoutException {
        if(!Objects.equals(userName, userDto.getUserName())) {
            return ResponseEntity.ok("{'ret':'이름은 변경 할 수 없습니다.'}");
        }

        apiUserService.editUser( userName, userDto );

        return ResponseEntity.ok("{'ret':'ok'}");
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser( @PathVariable String userName ) throws ExecutionException, InterruptedException, TimeoutException {
        apiUserService.deleteUser( userName );
        String result = "ok";

        String response = "{'ret':'" + result + "'}";
        return ResponseEntity.ok(response);
    }
}
