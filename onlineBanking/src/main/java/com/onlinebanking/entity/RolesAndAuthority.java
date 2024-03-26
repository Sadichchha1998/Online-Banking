package com.onlinebanking.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class RolesAndAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "authoritiesSet")
	@ToString.Exclude
	private Set<User> customerSet;

	public RolesAndAuthority(String name) {
		super();
		this.name = name;
	}
}
