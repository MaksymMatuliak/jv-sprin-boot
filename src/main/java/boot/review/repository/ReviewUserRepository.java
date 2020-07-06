package boot.review.repository;

import boot.review.entity.ReviewUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewUserRepository extends JpaRepository<ReviewUser, String> {
}
