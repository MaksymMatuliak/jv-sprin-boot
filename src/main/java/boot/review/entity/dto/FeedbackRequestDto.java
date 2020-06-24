package boot.review.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedbackRequestDto {
    private Long userId;
    private String productId;
    private String text;
}
