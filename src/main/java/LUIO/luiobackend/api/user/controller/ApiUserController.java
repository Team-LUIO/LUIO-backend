package LUIO.luiobackend.api.user.controller;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.api.user.service.ApiUserService;
import LUIO.luiobackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
