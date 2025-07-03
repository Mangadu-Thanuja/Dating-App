package com.mydating.datingapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mydating.datingapp.entity.User;
import com.mydating.datingapp.repository.UserRepository;
import com.mydating.datingapp.util.UserGender;

@Repository
public class UserDao {
	
	@Autowired
	UserRepository userRepositoty;

	public User saveUsers(User user) {
		return userRepositoty.save(user);
	}
	
	

	public List<User> findAllMaleUsers() {
		return userRepositoty.findByGender(UserGender.MALE);
	}



	public List<User> findAllFemaleUsers() {
		return userRepositoty.findByGender(UserGender.FEMALE);
	}
	
	

	public Optional<User> findUserById(int id) {
		return userRepositoty.findById(id);
	}
	
	
	
	

}
