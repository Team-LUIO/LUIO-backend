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
        apiUserService.editUser( userName, userDto );

        // path로 받은 userName과, userDto의 userName이 다를경우, 기존 내용은 지워야 한다.
        if(!Objects.equals(userName, userDto.getUserName())) {
            apiUserService.deleteUser( userName );
        }

        String response = "{'ret':'ok'}";
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser( @PathVariable String userName ) throws ExecutionException, InterruptedException, TimeoutException {
        apiUserService.deleteUser( userName );
        String result = "ok";

        String response = "{'ret':'" + result + "'}";
        return ResponseEntity.ok(response);
    }
}
