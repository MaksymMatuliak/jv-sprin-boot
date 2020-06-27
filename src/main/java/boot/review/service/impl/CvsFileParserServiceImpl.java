package boot.review.service.impl;

import boot.review.entity.Feedback;
import boot.review.entity.User;
import boot.review.service.FIleParserService;
import boot.review.service.FeedbackService;
import boot.review.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CvsFileParserServiceImpl implements FIleParserService {
    private static final int indexOfUserId = 2;
    private static final int indexOfUserName = 3;
    private static final int indexOfFeedbackText = 9;
    private final UserService userService;
    private final FeedbackService feedbackService;

    public CvsFileParserServiceImpl(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @Override
    public List<User> parseUsers(List<String> lines) {
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(",");
            User user = new User();
            user.setUserId(data[indexOfUserId]);
            user.setName(data[indexOfUserName]);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<Feedback> parseFeedbacks(List<String> lines) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(",");
            Feedback feedback = new Feedback();
            feedback.setProductId(data[1]);
            feedback.setUser(userService.getById(data[indexOfUserId]));
            feedback.setText(data[indexOfFeedbackText]);
            feedbackService.addFeedback(feedback);
            feedbacks.add(feedback);
        }
        return feedbacks;
    }
}
