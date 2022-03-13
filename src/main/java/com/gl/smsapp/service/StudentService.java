package com.gl.smsapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.smsapp.entity.Student;

@Service
public interface StudentService {

	public List<Student> findAll();	
	
	public Student findById(int id);
	
	public void save(Student student); // save or update
	
	public void deleteById(int id);
}
