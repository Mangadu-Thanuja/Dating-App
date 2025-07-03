package com.mydating.datingapp.entity;

import java.util.List;

import com.mydating.datingapp.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	
	@ElementCollection
//	@OneToMany
	private List<String> intrests;
	

}
