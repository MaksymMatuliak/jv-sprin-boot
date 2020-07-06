package boot.review.entity.dto;

import boot.review.entity.ReviewUser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedbackResponseDto {
    private Long feedbackId;
    private ReviewUser reviewuser;
    private String productId;
    private String text;
}
