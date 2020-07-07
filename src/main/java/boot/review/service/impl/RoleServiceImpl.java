package boot.review.service.impl;

import boot.review.entity.Role;
import boot.review.repository.RoleRepository;
import boot.review.service.RoleService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleByName(Role.RoleName roleName) {
        return roleRepository.findById(roleName);
    }
}
