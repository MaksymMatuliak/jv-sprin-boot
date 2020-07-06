package boot.review.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewUserResponseDto {
    private String userId;
    private String name;
}
