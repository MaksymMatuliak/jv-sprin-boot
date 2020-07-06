package boot.review.service;

import boot.review.entity.ReviewUser;
import java.util.List;

public interface ReviewUserService {
    List<ReviewUser> getReviewUsers();

    ReviewUser addReviewUser(ReviewUser reviewuser);

    ReviewUser getReviewUserById(String userId);
}
