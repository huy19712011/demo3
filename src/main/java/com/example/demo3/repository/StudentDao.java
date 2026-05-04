package com.example.demo3.repository;


import com.example.demo3.entity.Student;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import com.example.demo3.utils.EntityManagerUtils;

public class StudentDao {

    public ArrayList<Student> getStudents() {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            List<Student> students = em.createQuery("select s from Student s", Student.class).getResultList();
            return new ArrayList<>(students);
        }
    }

    public void deleteStudent(long id) {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        }
    }

    public void addStudent(Student student) {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }

    public Student getStudentById(Long id) {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            return em.find(Student.class, id);
        }
    }

    public void updateStudent(Student student) {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        }
    }
}
