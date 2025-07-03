package com.mydating.datingapp.dto;

import java.util.List;

import com.mydating.datingapp.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Data
public class MatchingUser {
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private UserGender gender;
	private List<String> intrests;
	private int ageDiff;
	private int mic;

}
