package boot.review.service.impl;

import boot.review.entity.Feedback;
import boot.review.repository.FeedbackRepository;
import boot.review.service.FeedbackService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
