package com.mydating.datingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydating.datingapp.entity.User;
import com.mydating.datingapp.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByGender(UserGender male);

}
