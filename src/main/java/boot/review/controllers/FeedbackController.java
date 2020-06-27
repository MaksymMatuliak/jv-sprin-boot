package boot.review.controllers;

import boot.review.entity.dto.FeedbackRequestDto;
import boot.review.entity.dto.FeedbackResponseDto;
import boot.review.service.FeedbackService;
import boot.review.util.FeedbackConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final FeedbackConvertUtil feedbackConvertUtil;

    public FeedbackController(FeedbackService feedbackService,
                              FeedbackConvertUtil feedbackConvertUtil) {
        this.feedbackService = feedbackService;
        this.feedbackConvertUtil = feedbackConvertUtil;
    }

    @GetMapping
    public List<FeedbackResponseDto> getFeedbacks() {
        return feedbackService.getFeedbacks()
                .stream()
                .map(feedbackConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addFeedback(FeedbackRequestDto feedbackRequestDto) {
        feedbackService.addFeedback(feedbackConvertUtil.requestDtoToEntity(feedbackRequestDto));
    }
}
