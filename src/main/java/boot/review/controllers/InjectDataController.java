package boot.review.controllers;

import boot.review.service.FeedbackService;
import boot.review.service.ReviewUserService;
import boot.review.service.impl.CvsFileParserServiceImpl;
import boot.review.service.impl.CvsFileReaderServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectDataController {
    private static final String PATH = "src\\main\\resources\\Reviews.csv";
    private static final String URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private final ReviewUserService reviewUserService;
    private final FeedbackService feedbackService;
    private final CvsFileParserServiceImpl cvsFileParser;
    private final CvsFileReaderServiceImpl cvsFileReader;

    public InjectDataController(ReviewUserService reviewUserService,
                                FeedbackService feedbackService,
                                CvsFileParserServiceImpl cvsFileParser,
                                CvsFileReaderServiceImpl cvsFileReader) {
        this.reviewUserService = reviewUserService;
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
        List<String> firstPart = new ArrayList<>(data.subList(0, (data.size() + 1) / 2));
        List<String> secondPart = new ArrayList<>(data.subList((data.size() + 1) / 2, data.size()));
        ThreadForFeedbacks threadForFeedbacks = new ThreadForFeedbacks(secondPart);
        threadForFeedbacks.start();
        cvsFileParser.parseUsers(firstPart).forEach(reviewUserService::addReviewUser);
        cvsFileParser.parseFeedbacks(firstPart).forEach(feedbackService::addFeedback);
    }

    private void downloadFile() throws IOException {
        InputStream in = new URL(URL).openStream();
        Files.copy(in, Paths.get(PATH), StandardCopyOption.REPLACE_EXISTING);
        in.close();
    }

    private class ThreadForFeedbacks extends Thread {
        private final List<String> data;

        ThreadForFeedbacks(List<String> data) {
            this.data = data;
        }

        public void run() {
            cvsFileParser.parseUsers(data).forEach(reviewUserService::addReviewUser);
            cvsFileParser.parseFeedbacks(data).forEach(feedbackService::addFeedback);
        }
    }
}

