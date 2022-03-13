package com.gl.smsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.smsapp.entity.Student;
import com.gl.smsapp.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		
		List<Student> students = studentService.findAll();
		theModel.addAttribute("students",students);
		return "list-students"; 
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormforAdd(Model theModel) {
		Student theStudent = new Student();
		
		theModel.addAttribute("Student",theStudent);
		
		return "Student-form";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int id,Model theModel) {
		Student theStudent = studentService.findById(id);
		
		theModel.addAttribute("Student",theStudent);
		
		return "Student-form";
		
	}
	
	
	
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("course") String course,
			@RequestParam("country") String country) {
	
		System.out.println(id);
		
	Student theStudent;
	
	if(id!=0) {
		theStudent  = studentService.findById(id);
		theStudent.setFirstName(firstName);
		theStudent.setLastName(lastName);
		theStudent.setCourse(course);
		theStudent.setCountry(country);		
	}
	else
		theStudent = new Student(firstName,lastName,course,country);
	studentService.save(theStudent);
	
	return "redirect:/student/list";
	
}
	
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

		// delete the Student
		studentService.deleteById(theId);

		// redirect to /student/list
		return "redirect:/student/list";

	}
}
