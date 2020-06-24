package boot.review.service;

import boot.review.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    List<Feedback> getFeedbacks();

    Feedback addFeedback(Feedback feedback);
}
