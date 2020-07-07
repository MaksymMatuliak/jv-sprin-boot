package boot.review.repository;

import boot.review.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Role.RoleName> {
}
