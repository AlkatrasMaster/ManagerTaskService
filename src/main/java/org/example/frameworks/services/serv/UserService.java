package org.example.frameworks.services.serv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.UserDto;
import org.example.frameworks.entity.Task;
import org.example.frameworks.entity.User;
import org.example.frameworks.repository.TaskRepository;
import org.example.frameworks.repository.UserRepository;
import org.example.frameworks.services.crudes.UserCRUDServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserCRUDServices<UserDto> {

    /**
     * Репозиторий для работы с пользователями в базе данных.
     * Инъецируется через конструктор благодаря @RequiredArgsConstructor.
     */
    private final UserRepository userRepository;

    /**
     * Сервис для работы с задачами.
     * Используется для проверки существования задач при назначении исполнителя.
     */
    private final TaskRepository taskRepository;


    /**
     * Получает пользователя по ID.
     * @param id идентификатор пользователя
     * @return пользователь в формате DTO
     * @throws ResponseStatusException если пользователь не найден
     */

    @Override
    public UserDto getById(Integer id) throws ResponseStatusException {
        log.info("Получения пользователя с ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Пользователь с ID %d не найдено", id)));
        return mapToDto(user);
    }

    /**
     * Получает всех пользователей в системе.
     * @return список всех пользователей в формате DTO
     */
    @Override
    public List<UserDto> getALl() {
        log.info("Получение все пользователей");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserService::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Создает нового пользователя в системе.
     * @param userDto данные пользователя для создания
     * @return созданный пользователь в формате DTO
     * @throws ResponseStatusException если пользователь с таким именем или email уже существует
     */
    @Override
    public UserDto create(UserDto userDto) throws ResponseStatusException {
        log.info("Создание нового пользователя");

        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Пользователь с именем %s уже существует", userDto.getUsername()));
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Пользователь с email %s уже существует", userDto.getEmail()));
        }

        User user = mapToEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    /**
     * Обновляет данные пользователя.
     * @param id ID пользователя для обновления
     * @param userDto новые данные пользователя
     * @return обновленный пользователь в формате DTO
     * @throws ResponseStatusException если пользователь не найден
     */
    @Override
    public UserDto update(Integer id, UserDto userDto) throws ResponseStatusException {
        log.info("Обновление пользователя с ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Пользователь с ID %d не найден", id)));

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
         return mapToDto(updatedUser);
    }

    /**
     * Удаляет пользователя по ID.
     * @param id ID пользователя для удаления
     * @throws ResponseStatusException если пользователь не найден
     */
    @Override
    public void deleteById(Integer id) throws ResponseStatusException {
        log.info("Удаление пользователя с ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Пользователь с ID %d не найден", id));
        }
        userRepository.deleteById(id);
    }

    /**
     * Преобразует сущность пользователя в DTO.
     * @param user сущность для преобразования
     * @return DTO объект пользователя
     */
    public static UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdateAt(user.getUpdatedAt());
        return userDto;
    }

    /**
     * Преобразует DTO в сущность пользователя.
     * @param userDto DTO для преобразования
     * @return сущность пользователя
     */
    public static User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
