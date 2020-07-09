package boot.review.service;

import boot.review.entity.Feedback;
import boot.review.entity.ReviewUser;
import java.util.List;

public interface FIleParserService {
    List<ReviewUser> parseUsers(List<String> lines);

    List<Feedback> parseFeedbacks(List<String> lines);
}
