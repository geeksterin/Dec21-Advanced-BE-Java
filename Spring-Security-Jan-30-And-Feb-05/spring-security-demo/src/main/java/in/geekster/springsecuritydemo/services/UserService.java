package in.geekster.springsecuritydemo.services;

import in.geekster.springsecuritydemo.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(final UserDTO userDTO);
    Optional<UserDTO> getByUsername(final String username);
    List<UserDTO> getAll();
}
