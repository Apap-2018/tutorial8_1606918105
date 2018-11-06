package com.apap.tutorial6.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial6.model.DealerModel;
import com.apap.tutorial6.model.PassModel;
import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.repository.UserRoleDB;
import com.apap.tutorial6.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired 
	private UserRoleService userService;
	
	@RequestMapping(value= "/addUser", method=RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value="/ubahPass" , method = RequestMethod.GET)
	private String ubahPassword(Model model) {
		return "updatepassword";
	}
	
	public boolean validatePassword(String password) {
		if (password.length()>=8 && Pattern.compile("[0-9]").matcher(password).find() &&  Pattern.compile("[a-zA-Z]").matcher(password).find())  {
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value="/submitPass" , method = RequestMethod.POST)
	 private String	submitUbahPassword(@ModelAttribute PassModel pass, Model model) {
		//using encoder untuk menyamakan format pada database 
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserRoleModel user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		String message = "";
			if (pass.getPasskonfirmasi().equals(pass.getPassbaru())) {
				
				
				if(passwordEncoder.matches(pass.getPasslama(), user.getPassword())){
					if(validatePassword(pass.getPassbaru())) {
					userService.changePass(pass.getPassbaru(), user);
					message = "password berhasil diubah";
					}
					else {message="password baru anda belum sesuai ketentuan: lebih dari 8 karakter, mengandung minimal 1 huruf dan 1 angka";
						}
					}
				else {
					message="password lama salah";
				}
			
		}
			else {
				message="password konfirmasi tidak sesuai dengan password baru";
			}
			model.addAttribute("msg",message);
			return "updatepassword";
	}
	
	
	
	
}
