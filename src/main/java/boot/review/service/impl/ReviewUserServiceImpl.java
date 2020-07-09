package boot.review.service.impl;

import boot.review.entity.ReviewUser;
import boot.review.repository.ReviewUserRepository;
import boot.review.service.ReviewUserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewUserServiceImpl implements ReviewUserService {
    private final ReviewUserRepository reviewUserRepository;

    public ReviewUserServiceImpl(ReviewUserRepository reviewUserRepository) {
        this.reviewUserRepository = reviewUserRepository;
    }

    @Override
    public List<ReviewUser> getReviewUsers() {
        return reviewUserRepository.findAll();
    }

    @Override
    public ReviewUser addReviewUser(ReviewUser reviewuser) {
        return reviewUserRepository.save(reviewuser);
    }

    @Override
    public ReviewUser getReviewUserById(String userId) {
        return reviewUserRepository.getOne(userId);
    }
}
