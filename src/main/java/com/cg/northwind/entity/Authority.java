//package com.cg.northwind.entity;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Table(name = "authorities")
//
//public class Authority {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
//    @GenericGenerator(name = "native",strategy = "native")
//    private Long id;
//
//    private String role;
//
//    @ManyToOne
//    @JoinColumn(name = "userdata_id")
//    private UserData userdata;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public UserData getUserdata() {
//		return userdata;
//	}
//
//	public void setUserdata(UserData userdata) {
//		this.userdata = userdata;
//	}
//
//  
//
//}