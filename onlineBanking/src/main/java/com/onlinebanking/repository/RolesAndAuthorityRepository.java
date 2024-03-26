package com.onlinebanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.entity.RolesAndAuthority;

public interface RolesAndAuthorityRepository extends JpaRepository<RolesAndAuthority, Integer> {
	Optional<RolesAndAuthority> findByName(String name);
}
