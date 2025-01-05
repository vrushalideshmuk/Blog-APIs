package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
	
	
}
