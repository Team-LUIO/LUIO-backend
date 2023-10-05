package LUIO.luiobackend.api.user.controller;

import LUIO.luiobackend.api.user.dto.UserDto;
import LUIO.luiobackend.api.user.service.ApiUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PatchMapping("/user")
    public ResponseEntity<?> editUser( @RequestBody UserDto userDto ) {
        apiUserService.createUser( userDto );

        String response = "{'ret':'ok'}";
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser( @PathVariable( "userName" ) String userName ) {
        String result = "unknown error";

        try {
            apiUserService.deleteUser( userName );
            result = "ok";
        }
        catch( ExecutionException ex ) {
            result = "회원 정보 삭제 중 오류가 발생하였습니다.";
        }
        catch( TimeoutException ex ) {
            result = "회원 정보 삭제 중 Timeout 발생하였습니다.";
        }
        catch( Exception ex ) {
            result = "회원 정보 삭제 중 알 수 없는 오류가 발생하였습니다. " + ex.getMessage();
        }

        String response = "{'ret':'" + result + "'}";
        return ResponseEntity.ok(response);
    }



}
