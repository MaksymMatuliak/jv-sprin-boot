package boot.review.entity.dto;

import boot.review.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedbackResponseDto {
    private Long feedbackId;
    private User user;
    private String productId;
    private String text;
}
