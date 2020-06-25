package boot.review.service;

import boot.review.entity.Feedback;
import boot.review.entity.User;
import java.util.List;

public interface FIleParserService {
    List<User> parseUsers(List<String> lines);

    List<Feedback> parseFeedbacks(List<String> lines);
}
