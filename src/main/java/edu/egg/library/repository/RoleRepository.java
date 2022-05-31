package edu.egg.library.repository;

import edu.egg.library.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    // crear metodo findByName
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
