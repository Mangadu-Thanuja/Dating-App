package com.mydating.datingapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mydating.datingapp.dao.UserDao;
import com.mydating.datingapp.dto.MatchingUser;
import com.mydating.datingapp.entity.User;
import com.mydating.datingapp.util.UserGender;
import com.mydating.datingapp.util.UserSorting;
@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	
	

	public ResponseEntity<?> saveUsers(User user) {
		User savedUsers=userDao.saveUsers(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
	}




	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleUsers = userDao.findAllMaleUsers();
		if (maleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Male Users found in the database table");
		}
		return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
	}
	
	
	
	
	public ResponseEntity<?> findAllFemaleUsers() {
		List<User> femaleUsers = userDao.findAllFemaleUsers();
		if (femaleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Male Users found in the database table");
		}
		return ResponseEntity.status(HttpStatus.OK).body(femaleUsers);
	}



	public ResponseEntity<?> findBestMatch(int id, int top) {
		Optional<User> optional = userDao.findUserById(id);

		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user id unable to find best match");
		}
		User user = optional.get();
		List<User> users = null;
		if (user.getGender().equals(UserGender.MALE)) {
			users = userDao.findAllFemaleUsers();
		} else {
			users = userDao.findAllMaleUsers();

		}

		List<MatchingUser> matchingUsers = new ArrayList<>();

		for (User u : users) {

			MatchingUser mu = new MatchingUser();

			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setPhone(u.getPhone());
			mu.setIntrests(u.getIntrests());
			mu.setGender(u.getGender());
			mu.setAgeDiff(Math.abs(user.getAge() - u.getAge()));

			List<String> interests1 = user.getIntrests();

			List<String> interests2 = u.getIntrests();

			int mic = 0;
			for (String s : interests1) {
				if (interests2.contains(s)) {
					mic++;
				}
			}

			mu.setMic(mic);
			matchingUsers.add(mu);

		}

		Comparator<MatchingUser> c = new UserSorting();

		Collections.sort(matchingUsers, c);

		List<MatchingUser> result = new ArrayList<>();

		for (MatchingUser mu : matchingUsers) {
			if (top == 0) {
				break;
			} else {
				result.add(mu);
				top--;
			}

		}

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	
	
	public ResponseEntity<?> findUserById(@PathVariable int id){
		Optional<User> optional=userDao.findUserById(id);
		if(optional.isPresent()) {
			   User user = optional.get();
			return ResponseEntity.status(200).body(user);
		}
		return ResponseEntity.status(404).body("Invalid id");
	}
	
	
	
	
	
	
	
	

}
