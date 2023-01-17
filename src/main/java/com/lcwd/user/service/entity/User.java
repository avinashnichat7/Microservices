package com.lcwd.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	@Id
	private String uid;
	private String name;
	private String email;
	private String about;

	@Transient
	private List<Rating> ratings = new ArrayList<>();

	
}
