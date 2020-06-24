package boot.review.controllers;

import boot.review.entity.Feedback;
import boot.review.service.FeedbackService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getFeedbacks() {
        return feedbackService.getFeedbacks();
    }

    @PostMapping
    public void addFeedback(Feedback feedback) {
        feedbackService.addFeedback(feedback);
    }
}
