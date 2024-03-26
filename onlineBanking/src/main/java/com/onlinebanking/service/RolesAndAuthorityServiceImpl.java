package com.onlinebanking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlinebanking.entity.RolesAndAuthority;
import com.onlinebanking.repository.RolesAndAuthorityRepository;

public class RolesAndAuthorityServiceImpl implements RolesAndAuthorityService {
	@Autowired
    private RolesAndAuthorityRepository rolesAndAuthorityRepository;
	@Override
	public RolesAndAuthority getRolesAndAuthority(String name) {
		return rolesAndAuthorityRepository.findByName(name).get();
	}

}
