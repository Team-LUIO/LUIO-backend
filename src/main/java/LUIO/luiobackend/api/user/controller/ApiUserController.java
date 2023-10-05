package LUIO.luiobackend.api.user.controller;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.api.user.service.ApiUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws ExecutionException, InterruptedException {
        List<UserDto> userDtoList = apiUserService.getAllUsers();

        return ResponseEntity.ok(userDtoList);
    }
}
