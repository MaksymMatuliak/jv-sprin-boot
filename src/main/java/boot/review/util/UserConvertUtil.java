package boot.review.util;

import boot.review.entity.User;
import boot.review.entity.dto.UserRequestDto;
import boot.review.entity.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserConvertUtil {
    public UserResponseDto entityToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setUserId(user.getUserId());
        return userResponseDto;
    }

    public User requestDtoToEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        return user;
    }
}
