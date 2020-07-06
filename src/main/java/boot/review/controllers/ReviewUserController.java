package boot.review.controllers;

import boot.review.entity.dto.ReviewUserRequestDto;
import boot.review.entity.dto.ReviewUserResponseDto;
import boot.review.service.ReviewUserService;
import boot.review.util.UserConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review-users")
public class ReviewUserController {
    private final ReviewUserService reviewUserService;
    private final UserConvertUtil userConvertUtil;

    public ReviewUserController(
            ReviewUserService reviewUserService, UserConvertUtil userConvertUtil) {
        this.reviewUserService = reviewUserService;
        this.userConvertUtil = userConvertUtil;
    }

    @GetMapping
    public List<ReviewUserResponseDto> getUsers() {
        return reviewUserService.getReviewUsers()
                .stream()
                .map(userConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addUser(ReviewUserRequestDto reviewUserRequestDto) {
        reviewUserService.addReviewUser(userConvertUtil.requestDtoToEntity(reviewUserRequestDto));
    }
}
