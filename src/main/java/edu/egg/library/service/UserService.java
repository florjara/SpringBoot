package edu.egg.library.service;

import edu.egg.library.entity.User;
import edu.egg.library.repository.RoleRepository;
import edu.egg.library.repository.UserRepository;
import static java.util.Collections.singletonList;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository) {
//        this.encoder = encoder;
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Supplier supplier = () -> new UsernameNotFoundException("no existe el email");
        User user = userRepository.findByEmail(email).orElseThrow(supplier);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),singletonList(authority));
        /*este metodo lo maneja spring
        se utiliza para el logueo
        agrega los usuarios al contexto de seguridad */
    }

    @Transactional
    public void create(User userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
       if (userDto.getRole()== null) { //????
            user.setRole(roleRepository.findByName("USER").orElseThrow(()-> new IllegalArgumentException("Error")));        
        } 
        
        //user.setAlta(true);

        userRepository.save(user);
    }

}
