package boot.review.controllers;

import boot.review.entity.dto.UserRequestDto;
import boot.review.entity.dto.UserResponseDto;
import boot.review.service.UserService;
import boot.review.util.UserConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserConvertUtil userConvertUtil;

    public UserController(UserService userService, UserConvertUtil userConvertUtil) {
        this.userService = userService;
        this.userConvertUtil = userConvertUtil;
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers()
                .stream()
                .map(userConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addUser(UserRequestDto userRequestDto) {
        userService.addUser(userConvertUtil.requestDtoToEntity(userRequestDto));
    }
}
