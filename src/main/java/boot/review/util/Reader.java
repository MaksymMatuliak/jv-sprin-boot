package boot.review.util;

import boot.review.entity.Feedback;
import boot.review.entity.User;
import boot.review.service.FeedbackService;
import boot.review.service.UserService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Reader {
    private final UserService userService;
    private final FeedbackService feedbackService;

    public Reader(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @PostConstruct
    private void injectData() throws Exception {
        File file = new File("C:\\Users\\maksy\\IdeaProjects\\"
                + "review\\src\\main\\resources\\Reviews.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String dataInString;
        bufferedReader.readLine();
        while ((dataInString = bufferedReader.readLine()) != null) {
            String[] data = dataInString.split(",");
            User user = new User();
            user.setUserId(data[2]);
            user.setName(data[3]);
            userService.addUser(user);
            Feedback feedback = new Feedback();
            feedback.setProductId(data[1]);
            feedback.setUser(user);
            feedback.setText(data[9]);
            feedbackService.addFeedback(feedback);
        }
    }
}
