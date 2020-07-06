package boot.review.service.impl;

import boot.review.entity.Feedback;
import boot.review.entity.ReviewUser;
import boot.review.service.FIleParserService;
import boot.review.service.FeedbackService;
import boot.review.service.ReviewUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CvsFileParserServiceImpl implements FIleParserService {
    private static final int indexOfUserId = 2;
    private static final int indexOfUserName = 3;
    private static final int indexOfFeedbackText = 9;
    private final ReviewUserService reviewUserService;
    private final FeedbackService feedbackService;

    public CvsFileParserServiceImpl(ReviewUserService reviewUserService,
                                    FeedbackService feedbackService) {
        this.reviewUserService = reviewUserService;
        this.feedbackService = feedbackService;
    }

    @Override
    public List<ReviewUser> parseUsers(List<String> lines) {
        List<ReviewUser> reviewUsers = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(",");
            ReviewUser reviewuser = new ReviewUser();
            reviewuser.setUserId(data[indexOfUserId]);
            reviewuser.setName(data[indexOfUserName]);
            reviewUsers.add(reviewuser);
        }
        return reviewUsers;
    }

    @Override
    public List<Feedback> parseFeedbacks(List<String> lines) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(",");
            Feedback feedback = new Feedback();
            feedback.setProductId(data[1]);
            feedback.setReviewUser(reviewUserService.getReviewUserById(data[indexOfUserId]));
            feedback.setText(data[indexOfFeedbackText]);
            feedbackService.addFeedback(feedback);
            feedbacks.add(feedback);
        }
        return feedbacks;
    }
}
