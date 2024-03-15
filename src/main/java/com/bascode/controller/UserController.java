package com.bascode.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bascode.dto.UserDTO;
import com.bascode.response.UserResponse;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private RestTemplate restTemplate;
	 
	
	//creating user
	@PostMapping("/create")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO userDTO){
	
		return restTemplate.postForEntity(
				"https://crudcrud.com/api/5e1e03beb4d74bd7af4988ba04c70267/unicorns"
				, userDTO, UserResponse.class);
		
	}
	
	//getting all users
	@GetMapping("/users")
	public List<UserResponse> getAll(){
		return Arrays.asList(restTemplate.getForObject("https://crudcrud.com/api/5e1e03beb4d74bd7af4988ba04c70267/unicorns",
				UserResponse[].class));
	}
	
	// get single user
	@GetMapping("/user/{id}")
	public ResponseEntity<String> getOneUser(@PathVariable String id){
		 return restTemplate.getForEntity("https://crudcrud.com/api/5e1e03beb4d74bd7af4988ba04c70267/unicorns/"+ id, String.class);
		 
	} 
	
	//updating single user
	@PutMapping("/update/{id}")
	public String updateUser(@RequestBody UserDTO userDTO, @PathVariable String id) {
		restTemplate.put("https://crudcrud.com/api/5e1e03beb4d74bd7af4988ba04c70267/unicorns/"+ id, userDTO);
		return "user with id "+id+" updated successfully";
	}
	
	// deleting single user
		@DeleteMapping("/delete/{id}")
		public String deleteOneUser(@PathVariable String id){
		  restTemplate.delete("https://crudcrud.com/api/5e1e03beb4d74bd7af4988ba04c70267/unicorns/"+ id);
		  return "user deleted successfully";
			 
		} 

}
