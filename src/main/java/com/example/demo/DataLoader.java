package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        // Create roles
        roleRepository.save(new Role("USER"));                  // User role
        roleRepository.save(new Role("ADMIN"));                 // Administrator role
        roleRepository.save(new Role("SUPER"));                 // Supervisor role-for courses, this would be the Dean
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");
        Role superRole = roleRepository.findByRole("SUPER");

        // Create users
        User user;
        user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new User("boss@boss.com", "password", "Boss", "Man", true, "boss");
        user.setRoles(Arrays.asList(superRole));
        userRepository.save(user);


        // Create courses
        Course course;

        course = new Course("Astrophysics", "Neil D Tyson", "Just a course on stars", 3);
        courseRepository.save(course);

        course = new Course("Calculus", "Carol Henley", "Rate of Change of the Rate of Change", 3);
        courseRepository.save(course);

        course = new Course("Freshman English", "Geraldine Pegram", "Jearn your landuage children", 3);
        courseRepository.save(course);

        course = new Course("World War II History", "H. G. Wells", "Analysis of troop movements during the war.", 3);
        courseRepository.save(course);
    }
}