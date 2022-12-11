package com.ooadproject.hotelmanagement.controller;
import com.ooadproject.hotelmanagement.model.Customer;
import com.ooadproject.hotelmanagement.model.RoomType;
import com.ooadproject.hotelmanagement.model.Staff;
import com.ooadproject.hotelmanagement.repository.CustomerRepository;
import com.ooadproject.hotelmanagement.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;


    // Customer - controller section
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        customer.setUserId(UUID.randomUUID().toString().split("-")[0]);
        return customerRepository.save(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable String customerId){
        return customerRepository.findById(customerId).get();
    }

    @PutMapping("/customer/{customerId}")
    public Customer modifyCustomer(@RequestBody Customer customerRequest, @PathVariable String customerId){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Customer existingCustomer = customerRepository.findById(customerId).get();
        existingCustomer.setName(customerRequest.getName());
        existingCustomer.setEmail(customerRequest.getEmail());
        existingCustomer.setPassword(customerRequest.getPassword());
        existingCustomer.setPhone(customerRequest.getPhone());
        return customerRepository.save(existingCustomer);
    }

    @DeleteMapping("/customer/{customerId}")
    public String deleteCustomer(@PathVariable String customerId){
        customerRepository.deleteById(customerId);
        return customerId +" customer deleted from system ";
    }

    @GetMapping("/customer/name")
    public List<Customer> findByCustomerNameStartsWith(@RequestParam("name") String name) {
        return customerRepository.findByNameStartsWith(name);
    }

    @GetMapping("/customer/email")
    public List<Customer> findByCustomerEmail(@RequestParam("email") String email) {
        return customerRepository.findByEmail(email);
    }

    // Staff - Controller section
    @PostMapping("/staff")
    @ResponseStatus(HttpStatus.CREATED)
    public Staff createStaff(@RequestBody Staff staff){
        staff.setUserId(UUID.randomUUID().toString().split("-")[0]);
        return staffRepository.save(staff);
    }

    @GetMapping("/staff")
    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    @GetMapping("/staff/{staffId}")
    public Staff getStaff(@PathVariable String staffId){
        return staffRepository.findById(staffId).get();
    }

    @PutMapping("/staff/{staffId}")
    public Staff modifyStaff(@RequestBody Staff staffRequest, @PathVariable String staffId){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Staff existingStaff = staffRepository.findById(staffId).get();
        existingStaff.setName(staffRequest.getName());
        existingStaff.setEmail(staffRequest.getEmail());
        existingStaff.setPassword(staffRequest.getPassword());
        existingStaff.setPhone(staffRequest.getPhone());
        existingStaff.setSalary(staffRequest.getSalary());
        existingStaff.setPosition(staffRequest.getPosition());
        return staffRepository.save(existingStaff);
    }

    @DeleteMapping("/staff/{staffId}")
    public String deleteStaff(@PathVariable String staffId){
        staffRepository.deleteById(staffId);
        return staffId +" staff deleted from system ";
    }

    @GetMapping("/staff/name")
    public List<Staff> findByStaffNameStartsWith(@RequestParam("name") String name) {
        return staffRepository.findByNameStartsWith(name);
    }

    @GetMapping("/staff/email")
    public List<Staff> findByStaffEmail(@RequestParam("email") String email) {
        return staffRepository.findByEmail(email);
    }

    @GetMapping("/staff/position")
    public List<Staff> findByPosition(@RequestParam("position") String position) {
        return staffRepository.findByPosition(position);
    }
}