package boot.review.util;

import boot.review.entity.ReviewUser;
import boot.review.entity.dto.ReviewUserRequestDto;
import boot.review.entity.dto.ReviewUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserConvertUtil {
    public ReviewUserResponseDto entityToResponseDto(ReviewUser reviewuser) {
        ReviewUserResponseDto reviewUserResponseDto = new ReviewUserResponseDto();
        reviewUserResponseDto.setName(reviewuser.getName());
        reviewUserResponseDto.setUserId(reviewuser.getUserId());
        return reviewUserResponseDto;
    }

    public ReviewUser requestDtoToEntity(ReviewUserRequestDto reviewUserRequestDto) {
        ReviewUser reviewuser = new ReviewUser();
        reviewuser.setName(reviewUserRequestDto.getName());
        return reviewuser;
    }
}
