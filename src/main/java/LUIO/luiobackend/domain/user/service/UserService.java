package LUIO.luiobackend.domain.user.service;

import LUIO.luiobackend.domain.user.entity.User;
import LUIO.luiobackend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public List<User> findAllUsers() throws ExecutionException, InterruptedException {
        return userRepository.findAllUsers();
    }

    public void deleteUser( String userName ) throws InterruptedException, ExecutionException, TimeoutException {
        userRepository.deleteUser( userName );
    }

    public User findByUsername(String userName) throws ExecutionException, InterruptedException {
        return userRepository.findByUserName(userName);
    }
}
