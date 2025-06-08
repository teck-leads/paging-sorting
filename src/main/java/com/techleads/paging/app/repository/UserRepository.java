package com.techleads.paging.app.repository;

import com.techleads.paging.app.dto.UserDto;
import com.techleads.paging.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


//    @Query(value = "SELECT u.user_id, u.name, u.email, a.street, a.city, a.country FROM tech_leads.user u INNER JOIN tech_leads.address a ON u.user_id = a.user_id and u.user_id=:userId", nativeQuery = true)
//    List<UserDto> findUserDetailsNative(
//            @Param("userId") String userId);



    @Query(value = "SELECT u.user_id AS userId, u.name AS name, u.email AS email, " +
            "a.street AS street, a.city AS city, a.country AS country " +
            "FROM user u " +
            "INNER JOIN address a ON u.user_id = a.user_id " +
            "WHERE u.user_id = :userId", nativeQuery = true)
    List<UserDto> findUserDetailsNative(@Param("userId") String userId);
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
@Transactional
@Modifying
    @Query(value = "UPDATE User u SET u.name = :name WHERE u.userId = :id")
    int updateUserNameById(@Param("name") String name, @Param("id") Integer id);

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE tech_leads.user u SET u.name = :name WHERE u.user_id = :id", nativeQuery = true)
//    int updateUserNameById(@Param("name") String name, @Param("id") Integer id);
}
