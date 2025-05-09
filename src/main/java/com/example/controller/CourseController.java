package com.example.controller;

import com.example.model.Course;
import com.example.model.Registration;
import com.example.repository.CourseRepository;
import com.example.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/check-crn/{crn}")
    public String checkCRN(@PathVariable int crn) {
        if (courseRepository.existsById(crn)) {
            return "exists";
        } else {
            return "not exists";
        }
    }

    @PostMapping("/register-course")
    public String registerCourse(@RequestParam String username, @RequestParam int crn) {
        Registration reg = new Registration(username, crn);
        registrationRepository.save(reg);
        return "Course registered successfully!";
    }

    
    @PostMapping("/delete-course")
public ResponseEntity<String> deleteCourse(@RequestParam String username, @RequestParam int crn) {
    try {
        System.out.println("Attempting to delete: username = " + username + ", crn = " + crn);

        Optional<Registration> registration = registrationRepository.findByUsernameAndCrn(username, crn);
        if (registration.isPresent()) {
            registrationRepository.delete(registration.get());
            return ResponseEntity.ok("Course deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("No registration found for username and crn.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body("Error deleting course: " + e.getMessage());
    }
}

    @GetMapping("/get-registrations")
    public List<Course> getRegisteredCourses(@RequestParam String username) {
        List<Registration> registrations = registrationRepository.findByUsername(username);
        List<Course> registeredCourses = new ArrayList<>();

        for (Registration registration : registrations) {
            Optional<Course> course = courseRepository.findById(registration.getCrn());
            course.ifPresent(registeredCourses::add);
        }

        return registeredCourses;
    }

    @GetMapping("/get-course/{crn}")
    public ResponseEntity<Course> getCourse(@PathVariable int crn) {
        Optional<Course> course = courseRepository.findById(crn);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/get-courses")
public List<Course> getAllCourses() {
    return courseRepository.findAll();
}
}

   

