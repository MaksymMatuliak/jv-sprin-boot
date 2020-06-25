package boot.review.controllers;

import boot.review.service.FeedbackService;
import boot.review.service.UserService;
import boot.review.service.impl.CvsFileParserServiceImpl;
import boot.review.service.impl.CvsFileReaderServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InjectDataController {
    private static final String PATH = "src\\main\\resources\\Reviews.csv";
    private static final String URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private final UserService userService;
    private final FeedbackService feedbackService;
    private final CvsFileParserServiceImpl cvsFileParser;
    private final CvsFileReaderServiceImpl cvsFileReader;

    public InjectDataController(UserService userService, FeedbackService feedbackService,
                                CvsFileParserServiceImpl cvsFileParser,
                                CvsFileReaderServiceImpl cvsFileReader) {
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.cvsFileParser = cvsFileParser;
        this.cvsFileReader = cvsFileReader;
    }

    @PostMapping("/inject")
    private void injectData() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            downloadFile();
        }
        List<String> data = cvsFileReader.read(PATH);
        MyThread myThread = new MyThread(data);
        cvsFileParser.parseUsers(data).forEach(userService::addUser);
        myThread.start();
    }

    private void downloadFile() throws IOException {
        InputStream in = new URL(URL).openStream();
        Files.copy(in, Paths.get(PATH), StandardCopyOption.REPLACE_EXISTING);
        in.close();
    }

    private class MyThread extends Thread {
        private final List<String> data;

        MyThread(List<String> data) {
            this.data = data;
        }

        public void run() {
            cvsFileParser.parseFeedbacks(data).forEach(feedbackService::addFeedback);
        }
    }
}

