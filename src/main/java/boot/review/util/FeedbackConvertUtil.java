package boot.review.util;

import boot.review.entity.Feedback;
import boot.review.entity.dto.FeedbackRequestDto;
import boot.review.entity.dto.FeedbackResponseDto;
import boot.review.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class FeedbackConvertUtil {
    public final UserService userService;

    public FeedbackConvertUtil(UserService userService) {
        this.userService = userService;
    }

    public FeedbackResponseDto entityToResponseDto(Feedback feedback) {
        FeedbackResponseDto feedbackResponseDto = new FeedbackResponseDto();
        feedbackResponseDto.setFeedbackId(feedback.getFeedbackId());
        feedbackResponseDto.setProductId(feedback.getProductId());
        feedbackResponseDto.setUser(feedback.getUser());
        feedbackResponseDto.setText(feedback.getText());
        return feedbackResponseDto;
    }

    public Feedback requestDtoToEntity(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = new Feedback();
        feedback.setText(feedbackRequestDto.getText());
        feedback.setUser(userService.getById(feedbackRequestDto.getUserId()));
        feedback.setProductId(feedbackRequestDto.getProductId());
        return feedback;
    }
}
