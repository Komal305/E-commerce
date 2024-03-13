//package com.cg.northwind.config;
//
//	import org.springframework.context.annotation.Bean;
//
//	import org.springframework.context.annotation.Configuration;
//
//	import org.springframework.security.config.Customizer;
//
//	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//	import org.springframework.security.crypto.password.PasswordEncoder;
//
//	import org.springframework.security.web.SecurityFilterChain;
//
//	@Configuration
//	public class SecurityConfiguration {
//	    @Bean
//	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//	        http.csrf((csrf) -> csrf.disable())
//	                .authorizeHttpRequests((requests) -> requests
////	                        .requestMatchers("/api/category/edit/{id}"
////	                        		,"/api/customers/companyname/{companyname}","/api/customers/contactTitle/{contactTitle}"
////	                        		,"/api/customers/country/{country}","/api/customers/city/{city}"
////	                        		,"/api/customers/region/{region}","/api/customers/fax/{fax}"
////	                        		,"/api/customers/regionnotnull","/api/customers/numberofcustomersbycountry"
////	                        		,"/api/customers/","/api/customers/edit/{customerid}"
////	                        		
////	                        		
////	                        		,"/api/employee/firstName/{firstName}","/api/employee/title/{title}","/api/employee/city/{city}"
////	                        		,"/api/employee/manager","/api/employee/addemployee"
////	                        		,"/api/employee/update/employeeid","/api/employee/birthdate/{birthdate}","/api/employee/edit/{employeeid}"
////	                        		
////	                        		
////	                        		,"/api/shipper/edit/{shipperId}","/api/shipper/"
////	                        		
////	                        		,"/api/supplier/edit/{id}","/api/supplier/country/{country}","/api/supplier/regionnotnull"
////	                        		,"/api/supplier/title/{Manager}","/api/supplier/numberofsupplierbycountry").hasRole("ADMIN")
////	                        
////	                        
////	                        .requestMatchers("/api/products/productbymaxprice","/api/products/productdetails"
////	                        		,"/api/products/bycategoryname/{bycategoryname}","").hasRole("USER")
//	                        
//	                        .requestMatchers("/api/customers","/api/employee").authenticated()
//	                        .requestMatchers("/register","/api/products","/swagger-ui.html"
//	                        		,"/swagger-ui/**","/v3/api-docs/**","/api/customers/edit/cust/{customerid}").permitAll())
////	                .formLogin(Customizer.withDefaults())
////	                .httpBasic(Customizer.withDefaults());
//	                .oauth2Login(Customizer.withDefaults());
//	        return http.build();
//	    }
//	 
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	}
