package senchenko.interview.services;

import senchenko.interview.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAllRoles();

    Optional<Role> findRoleById(Long id);
}
