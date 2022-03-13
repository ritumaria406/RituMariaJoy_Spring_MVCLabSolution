package com.gl.smsapp.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.gl.smsapp.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService{

	private SessionFactory sessionFactory;
	private Session session;
	
	
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	public List<Student> findAll() {
		
		Transaction tx = session.beginTransaction();
		
		// from "EntityName"
		List<Student> students = session.createQuery("from Student", Student.class).list();
		
		tx.commit();
		
		return students;
	}
	

	public Student findById(int id) {
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class,id);
		
		tx.commit();
		return student;
	}

	public void save(Student student) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
	}

	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		
		try {
			Student student = session.get(Student.class, id);
		session.delete(student);
		} finally {
			tx.commit();
		}
		
	}

}
