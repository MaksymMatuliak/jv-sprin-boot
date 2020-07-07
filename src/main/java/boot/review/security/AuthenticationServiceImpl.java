package boot.review.security;

import boot.review.entity.Role;
import boot.review.entity.User;
import boot.review.service.RoleService;
import boot.review.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(roleService.getRoleByName(Role.RoleName.USER).orElseThrow()));
        user = userService.addUser(user);
        return user;
    }
}
