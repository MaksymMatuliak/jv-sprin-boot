package boot.review.security;

import boot.review.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    User register(String email, String name, String password);
}
