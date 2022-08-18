package com.restfull.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfull.spring.boot.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
