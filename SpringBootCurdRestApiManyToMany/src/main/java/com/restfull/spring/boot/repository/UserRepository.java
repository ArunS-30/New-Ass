package com.restfull.spring.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restfull.spring.boot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	// @Query -- write here queryies
	
    @Query(value = "delete  from t_user_roles where users_id= :user_id", nativeQuery = true)
    void deleteRelation(@Param("user_id") Long user_id);
//    @Query("from User where email= :email")
//   User  findByEmail(@Param("email") String email);
}
