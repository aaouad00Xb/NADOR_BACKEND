package com.example.demo.Dao;


import com.example.demo.Entities.ERole;
import com.example.demo.Entities.MyUser;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.management.Notification;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<MyUser,Long> {
MyUser findByUsername(String username);

    Optional<MyUser> findUserByUsername(String username);

Optional<MyUser> findById(Long id);
Optional<List<MyUser>> findAllByRole(ERole role);


@Query(value = "select f from MyUser f where f.province.id_province=:id")
    Optional<List<MyUser>> findAllByProvince_Id_province(@Param("id") Long id);
//    @Query("SELECT u.notifications FROM MyUser u WHERE u.id = :userId ")
//    List<Notification> findNotificationsByUserId(Long userId);
}
