package eu.epicclan.spring.websocket.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.epicclan.spring.websocket.login.models.ERole;
import eu.epicclan.spring.websocket.login.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}