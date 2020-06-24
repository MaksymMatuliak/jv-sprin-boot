package boot.review.controllers;

import boot.review.entity.Feedback;
import boot.review.entity.User;
import boot.review.service.FeedbackService;
import boot.review.service.UserService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InjectDataController {
    private static final String PATH = "src\\main\\resources\\Reviews.csv";
    private static final String URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private final UserService userService;
    private final FeedbackService feedbackService;

    public InjectDataController(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @PostMapping("/inject")
    private void injectData() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            downloadFile();
        }
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

    private void downloadFile() throws IOException {
        InputStream in = new URL(URL).openStream();
        Files.copy(in, Paths.get(PATH), StandardCopyOption.REPLACE_EXISTING);
        in.close();
    }
}

