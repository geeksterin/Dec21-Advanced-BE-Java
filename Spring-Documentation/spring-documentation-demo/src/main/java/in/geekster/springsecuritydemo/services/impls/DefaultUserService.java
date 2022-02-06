package in.geekster.springsecuritydemo.services.impls;

import in.geekster.springsecuritydemo.dtos.UserDTO;
import in.geekster.springsecuritydemo.entities.UserEntity;
import in.geekster.springsecuritydemo.enums.AccountStatus;
import in.geekster.springsecuritydemo.repositories.UserRepository;
import in.geekster.springsecuritydemo.services.UserService;
import in.geekster.springsecuritydemo.utils.LoggedInContext;
import in.geekster.springsecuritydemo.utils.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(final UserDTO userDTO) {
        log.debug("Creating User with User Details: {}", userDTO);
        UserEntity userEntity = MapperUtil.convert(userDTO, UserEntity.class);
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setCreatedBy(LoggedInContext.getCurrentUser()); // -999 [Public]
        userEntity.setStatus(AccountStatus.ACTIVE);
        userEntity.setCreatedOn(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        log.debug("Saved User Entity: {}", userEntity);
        return MapperUtil.convert(userEntity, UserDTO.class);
    }

    @Override
    public Optional<UserDTO> getByUsername(final String username) {
        log.debug("Finding user by username: {}", username);
        final Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isEmpty()) {
            log.warn("No User Entity found by username: {}", username);
            return Optional.empty();
        }
        final UserEntity userEntity = userEntityOptional.get();
        log.debug("Retrieved User Entity: {}", userEntity);
        final UserDTO userDTO = MapperUtil.convert(userEntity, UserDTO.class);
        return Optional.of(userDTO);
    }

    @Override
    public List<UserDTO> getAll() {
        log.debug("Getting all users");
        final List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach((ue) -> {
            final UserDTO u = MapperUtil.convert(ue, UserDTO.class);
            userDTOS.add(u);
        });
        return userDTOS;
    }
}
