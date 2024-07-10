package com.example.demo.Service;

import com.example.demo.Entities.MyUser;
import javax.management.Notification;
import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface UserService {
    MyUser saveUser(MyUser user);
    MyUser save(MyUser user);

    void addRoleToUser(String username,String rolName);
    MyUser getUser(String username);
    Optional<MyUser> getUserById(Long id);
    List<MyUser> getUsers();

    List<Notification> getNotificationsByUserId(Long userId);
}
