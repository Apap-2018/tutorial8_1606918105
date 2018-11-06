package com.apap.tutorial6.service;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.model.PassModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	public void changePass(String password, UserRoleModel user);
	public UserRoleModel findByUsername(String username);

}
