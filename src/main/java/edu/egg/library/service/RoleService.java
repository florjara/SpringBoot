package edu.egg.library.service;

import edu.egg.library.entity.Role;
import edu.egg.library.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public void create(Role roleDto) {
        Role role = new Role();
        
        if (roleRepository.existsByName(roleDto.getName())) {
             throw new IllegalArgumentException("El rol indicado ya existe.");
        }
        
        role.setName(roleDto.getName());
        roleRepository.save(role);
    }
    
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
