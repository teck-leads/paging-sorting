package com.techleads.paging.app.repository;

import com.techleads.paging.app.dto.UserDto;
import com.techleads.paging.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select distinct new com.techleads.paging.app.dto.UserDto( " +
            "u.userId as userId, " +
            "u.name as name, " +
            "u.email as email, " +
            "a.street as street, " +
            "a.city as city, " +
            "a.country as country) " +
            "from User u " +
            "inner join Address a " +
            "on (u.userId = a.userId) " +
            "where u.userId =  (:userId)")
    List<UserDto> findUserDetails(
            @Param("userId") String userId);
    @Query("select distinct new com.techleads.paging.app.dto.UserDto( " +
            "u.userId as userId, " +
            "u.name as name, " +
            "u.email as email, " +
            "a.street as street, " +
            "a.city as city, " +
            "a.country as country) " +
            "from User u " +
            "inner join Address a " +
            "on (u.userId = a.userId) " +
            "where u.userId =  (:userId)")
    Page<UserDto> findUserDetailsPage(@Param("userId") String userId, Pageable pageable);



}
