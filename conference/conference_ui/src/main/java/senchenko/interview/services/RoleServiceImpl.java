package senchenko.interview.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senchenko.interview.entities.Role;
import senchenko.interview.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Long getListenerId() {
        return roleRepository.findIdByTitle("ROLE_LISTENER");
    }
}
