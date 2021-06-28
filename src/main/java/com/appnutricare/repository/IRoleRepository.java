package com.appnutricare.repository;

import com.appnutricare.entities.Role;
import com.appnutricare.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleName roleName);
}
