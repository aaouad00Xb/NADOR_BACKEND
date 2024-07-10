package com.example.demo.Service;


import com.example.demo.Dao.UserRepository;
import com.example.demo.Entities.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImplimentation implements UserService, UserDetailsService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepo.findByUsername(username);
        if(user == null){
            log.info("user not found   ");
            throw new UsernameNotFoundException("user not found   ");
        }else{
            log.info("user  found   ");
        }

        Collection<SimpleGrantedAuthority> auth = new ArrayList<>();

            auth.add(new SimpleGrantedAuthority(user.getRole().name()));


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),auth);
    }


    @Autowired
    public UserServiceImplimentation(UserRepository userRepo,  PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public MyUser save(MyUser user) {
        return this.userRepo.save(user);
    }
    @Override
    public MyUser saveUser(MyUser user) {
        log.info("save user ");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MyUser myUser=  userRepo.findByUsername(user.getUsername());
        if(myUser!= null){
            return this.userRepo.save(user);

        }
        throw new UsernameNotFoundException("userAlready exist");

    }


    @Override
    public void addRoleToUser(String username, String rolName) {
//        log.info("adding role to users");
//        MyUser myUser = userRepo.findByUsername(username);
//        Role role = roleRepo.findByName(rolName);
//        log.info("adding  role {} to user {}",rolName,username);
//        myUser.setRole(role);
    }
    @Override
    public MyUser getUser(String username) {
        log.info("getting new user");
        return this.userRepo.findByUsername(username);
    }

    public Optional<MyUser> getUserById(Long id) {
        log.info("getting new user");
        return this.userRepo.findById(id);
    }


    @Override
    public List<MyUser> getUsers() {
        log.info("getting users");
        return this.userRepo.findAll();
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return null;
    }

}
