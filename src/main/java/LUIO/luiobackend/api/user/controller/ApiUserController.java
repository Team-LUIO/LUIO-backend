package LUIO.luiobackend.api.user.controller;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.api.user.service.ApiUserService;
import LUIO.luiobackend.domain.user.repository.UserRepository;
import LUIO.luiobackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/user")
    public ResponseEntity<?> editUser( @RequestBody UserDto userDto ) {
        String result = "unknown error";
        try {
            apiUserService.createUser( userDto );
            result = "ok";
        }
        catch( Exception ex ) {
            result = ex.getMessage();
        }

        String response = "{'ret':'" + result + "'}";
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser( @PathVariable( "userName" ) String userName ) {
        String result = "unknown error";
        try {
            apiUserService.deleteUser( userName );
            result = "ok";
        }
        catch( Exception ex ) {
            result = ex.getMessage();
        }

        String response = "{'ret':'" + result + "'}";
        return ResponseEntity.ok(response);
    }
}
