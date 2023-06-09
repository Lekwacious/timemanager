package com.time.timemanager.security;


import com.time.timemanager.model.TimeManagerUser;
import com.time.timemanager.repository.TimeManagerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   @Autowired
   TimeManagerUserRepository userRepository;

   @Transactional
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       // Let people login with either username or email

       TimeManagerUser user  = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : ".concat(usernameOrEmail)));

       return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        TimeManagerUser user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User notfound with iid: "+ id));
    return UserPrincipal.create(user);
   }
}
