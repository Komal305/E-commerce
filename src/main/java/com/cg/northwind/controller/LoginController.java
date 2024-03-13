//package com.cg.northwind.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cg.northwind.entity.UserData;
//import com.cg.northwind.repository.UserDataRepository;
//
//@RestController
//public class LoginController {
//
//    @Autowired
//    private UserDataRepository customerRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody UserData customer) {
//        UserData savedCustomer = null;
//        ResponseEntity response = null;
//        try {
//            String hashPassword = passwordEncoder.encode(customer.getPassword());
//            customer.setPassword(hashPassword);
//            savedCustomer = customerRepository.save(customer);
//            if (savedCustomer.getId() > 0) {
//                response = ResponseEntity
//                        .status(HttpStatus.CREATED)
//                        .body("you are registered");
//            }
//        } catch (Exception ex) {
//            response = ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Exception due to " + ex.getMessage());
//        }
//        return response;
//        
//    }
//    
//    @GetMapping("/")
//    public String OAuth(OAuth2AuthenticationToken token) {
//    	System.out.println(token.getPrincipal());
//    	return "Welcome to OAuth";
//    	
//    	
//    }
//}