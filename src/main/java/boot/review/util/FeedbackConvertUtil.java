package boot.review.util;

import boot.review.entity.Feedback;
import boot.review.entity.dto.FeedbackRequestDto;
import boot.review.entity.dto.FeedbackResponseDto;
import boot.review.service.ReviewUserService;
import org.springframework.stereotype.Component;

@Component
public class FeedbackConvertUtil {
    public final ReviewUserService reviewUserService;

    public FeedbackConvertUtil(ReviewUserService reviewUserService) {
        this.reviewUserService = reviewUserService;
    }

    public FeedbackResponseDto entityToResponseDto(Feedback feedback) {
        FeedbackResponseDto feedbackResponseDto = new FeedbackResponseDto();
        feedbackResponseDto.setFeedbackId(feedback.getFeedbackId());
        feedbackResponseDto.setProductId(feedback.getProductId());
        feedbackResponseDto.setReviewuser(feedback.getReviewUser());
        feedbackResponseDto.setText(feedback.getText());
        return feedbackResponseDto;
    }

    public Feedback requestDtoToEntity(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = new Feedback();
        feedback.setText(feedbackRequestDto.getText());
        feedback.setReviewUser(reviewUserService.getReviewUserById(feedbackRequestDto.getUserId()));
        feedback.setProductId(feedbackRequestDto.getProductId());
        return feedback;
    }
}
