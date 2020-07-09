package boot.review.service;

import boot.review.entity.Role;
import java.util.Optional;

public interface RoleService {
    Role add(Role role);

    Optional<Role> getRoleByName(Role.RoleName roleName);
}
