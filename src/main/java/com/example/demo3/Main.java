package com.example.demo3;

import com.example.demo3.entity.Course;
import com.example.demo3.entity.Student;
import com.example.demo3.entity.Student5;
import com.example.demo3.repository.ProductRepository;
import com.example.demo3.utils.HibernateUtilVersion2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        EntityManager em = emf.createEntityManager();

        //EntityManager em = EntityManagerUtils.getEntityManager();

        //EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        //EntityManager em = emf.createEntityManager();

        //Product product1 = new Product(1, "product 1");
        //Product product2 = new Product(2, "product 2");
        //
        //em.getTransaction().begin();
        //em.persist(product1);
        ////product1.setName("product 11");
        //em.persist(product2);
        //em.getTransaction().commit();

        //TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
        //List<Product> list = query.getResultList();
        //list.forEach(System.out::println);
        //System.out.println("Ok");

        //em.createNativeQuery("select * from products10", Product.class).getResultList().forEach(System.out::println);

        //em.createQuery("select s from Student s", Student.class).getResultList().forEach(System.out::println);
        //
        //System.out.println(em.find(Student.class, 1001));

        //Review review = new Review();
        //review.setRating("5");
        //review.setDescription("Ok");
        //
        //
        //Course course = new Course();
        //course.setName("java");
        //course.addReview(review);
        //
        //review.setCourse(course);
        //
        //
        //em.getTransaction().begin();
        //em.persist(course); // also save review if orphanRemoval == persist or all
        ////em.persist(review);
        //
        ////em.remove(course); // also remove associated reviews
        //em.getTransaction().commit();

        ProductRepository productRepository = new ProductRepository();

        productRepository.getProductByNameOrId_2("product 1", 102)
                .forEach(System.out::println);

        productRepository.getProductByNameOrId_3("product 1", 102)
                .forEach(System.out::println);

        productRepository.getProductByNameOrId_4("product 1", 102)
                .forEach(System.out::println);

        productRepository.getProductByNameOrId_5("product 1", 102)
                .forEach(System.out::println);

        productRepository.getProductByNameOrId_6("product 2", 104)
                .forEach(System.out::println);

        productRepository.findProductsByNameContaining("pro")
                .forEach(System.out::println);




        Student student = new Student();
        student.setId(1001L);
        student.setName("student 1");
        student.setEmail("email 1");
        student.setPhone("phone 1");

        Course course = new Course();
        course.setId(101L);
        course.setName("course 1");

        em.getTransaction().begin();
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        em.getTransaction().commit();




        Session session = HibernateUtilVersion2.getSessionFactory().openSession();
        session.beginTransaction();

        Student5 student5 = new Student5("firstName1", "lastName 1", "email 1");
        session.persist(student5);

        session.getTransaction().commit();
    }
}
