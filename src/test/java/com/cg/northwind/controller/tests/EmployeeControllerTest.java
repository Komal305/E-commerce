//package com.cg.northwind.controller.tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.cg.northwind.entity.Employees;
//import com.cg.northwind.exception.IdAlreadyExistsException;
//import com.cg.northwind.exception.IdDoesNotExistsException;
//import com.cg.northwind.repository.EmployeeRepository;
//import com.cg.northwind.service.EmployeeServiceImplementation;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class EmployeeControllerTest {
//
//	
//	@InjectMocks
//	private EmployeeServiceImplementation employeeServiceImplementation;
//	
//	@Mock
//	private EmployeeRepository employeeRepository;
//	
//	@Test
//	public void testGetAllEmployees() {
//		
//        Date birthDate = new Date();
//        Date hireDate = new Date();
//        
//		 Employees employee=new Employees(1, "grey", "joe", "Manager", "mr", birthDate, hireDate,
//                 "123-dd St", "New Jersy", "NJ", "1231", "USA", "123-456-234", "1234", "photo.jpg", null, "photos");
//        when(employeeRepository.findAll()).thenReturn(List.of(employee));
//        
//        assertEquals(1,employeeServiceImplementation.getAllEmployees().size());
//        
//	}	
//          
//        @Test
//        public  void testUpdateEmployeeDetailsById()
//        {
//        	 Date birthDate = new Date();
//             Date hireDate = new Date();
//        	
//        	 Employees employee1=new Employees(1, "grey", "joe", "Manager", "mr", birthDate, hireDate,
//                     "123-dd St", "New Jersy", "NJ", "1231", "USA", "123-456-234", "1234", "photo.jpg", null, "photos");
//        	 Employees employee2=new Employees(2, "matrix", "clay", "Sales Executive", "Mr", birthDate, hireDate,
//                     "123-dd St", "New Los Angels", "NJ", "4321", "USA", "123-456-234", "1234", "photo.jpg", null, "photos");
//        	 
//             when(employeeRepository.findById(employee1.getEmployeeId())).thenReturn(Optional.of(employee1));
//             
//             when(employeeRepository.save(employee1)).thenReturn(employee2);
//             
//             Employees result=employeeServiceImplementation.updateEmployeeDetailsById(employee1,employee1.getEmployeeId());
//
//        	assertEquals(employee2,result);
//        	
//        }
//        
//        @Test
//        public void testUpdateEmployeeDetails() throws IdDoesNotExistsException
//        {
//        	
//        	Date birthDate = new Date();
//            Date hireDate = new Date();
//             
//        	Employees existing=new Employees();
//        	existing.setEmployeeId(1);
//        	existing.setLastName("grey");
//        	existing.setFirstName("joe");
//        	existing.setTitle("Manager");
//        	existing.setTitleOfCourtesy("Mr");
//        	existing.setBirthDate(birthDate);
//        	existing.setHireDate(hireDate);
//        	existing.setAddress("123-dd St");
//        	existing.setCity("Los Angels");
//        	existing.setCountry("USA");
//        	existing.setExtension("1233");
//        	existing.setHomePhone("2314265817");
//        	existing.setPostalCode("12311");
//        	existing.setRegion("LS");
//        	existing.setPhoto("photo");
//        	existing.setReportsTo(null);
//        	existing.setPhotoPath("ajdvAKYW");
//        	
//        	Employees updating=new Employees();
//        	
//        	updating.setCity("New Jersey");
//        	
//        	when(employeeRepository.findById(1)).thenReturn(Optional.of(existing));
//        	
//        	boolean result=employeeServiceImplementation.updateEmployeeDetails(1,updating);
//        	
//        	verify(employeeRepository, times(1)).save(existing);
//        	assertTrue(result);
//        	assertEquals("New Jersey",existing.getCity());
//        	
//        	
//        }
//        
//        @Test
//        public void testAddNewEmployee() throws IdAlreadyExistsException
//        {
//        	 Date birthDate = new Date();
//             Date hireDate = new Date();
//        	
//        	 Employees employee=new Employees(1, "grey", "joe", "Manager", "mr", birthDate, hireDate,
//                     "123-dd St", "New Jersy", "NJ", "1231", "USA", "123-456-234", "1234", "photo.jpg", null, "photos");
//        	 when(employeeRepository.save(employee)).thenReturn(employee);
//        	 assertEquals(employee,employeeServiceImplementation.addNewEmployee(employee));
//        	
//        }
//     
//
//	
//}
