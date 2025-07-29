package com.example.demo3.controller;

import com.example.demo3.entity.Student;
import com.example.demo3.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

//@WebServlet(name = "StudentServlet", value = "/StudentServlet/*")
@WebServlet(name = "StudentServlet", value = {
        "/students",
        "/students/new",
        "/students/insert",
        "/students/delete",
        "/students/edit",
        "/students/update",
        "/students/saveStudent",
        "/students/showStudents"
})
public class StudentServlet extends HttpServlet {

    private StudentService service = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        String path = request.getServletPath();
        System.out.println(path);
        switch (path) {
            case "/students":
                listStudents(request, response);
                break;
            case "/students/new":
                showNewForm(request, response);
                break;
            case "/students/insert":
                insertStudent(request, response);
                break;
            case "/students/delete":
                deleteStudent(request, response);
                break;
            case "/students/edit":
                editStudent(request, response);
                break;
            case "/students/update":
                updateStudent(request, response);
                break;
            case "/students/saveStudent":
                saveStudent(request, response);
                break;
            case "/students/showStudents":
                showStudents(request, response);
                break;
        }

    }

    private void showStudents(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("Show all students...");
        ArrayList<Student> students = service.getStudents();
        students.stream().forEach(student -> System.out.println(student));

        JSONArray jsonArray = new org.json.JSONArray(students);
        response.setContentType("application/json");

        response.getWriter().write(jsonArray.toString()); // send this data to ajax call
    }

    private String saveStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("Adding ...");
        Student student = getStudentFromForm(request);
        service.addStudent(student);

        JSONObject json = new JSONObject();
        json.put("id", student.getId());
        json.put("name", student.getName());
        json.put("email", student.getEmail());
        json.put("phone", student.getPhone());

        response.setContentType("application/json");
        response.getWriter().write(json.toString()); // send this data to ajax call

        return json.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        //processRequest(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher("/view/addStudentForm.jsp")
                .forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // read student info from form
        Student student = getStudentFromForm(request);

        // update student
        service.updateStudent(student);

        // redirect to list students
        response.sendRedirect("/students");
    }

    private Student getStudentFromForm(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // create student object
        Student student = new Student(id, name, email, phone);
        return student;
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // load student info to the form
        // get student id from form
        Long id = Long.parseLong(request.getParameter("id"));
        // get student by id
        Student student = service.getStudentById(id);
        // send student to form
        request.setAttribute("student", student);
        // redirect to form
        request
                .getRequestDispatcher("/view/updateStudentForm.jsp")
                .forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long id = Integer.parseInt(request.getParameter("id"));

        service.deleteStudent(id);

        response.sendRedirect("/students");
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read student info from form
        Student student = getStudentFromForm(request);

        // save student to list
        service.addStudent(student);

        // redirect to list students
        response.sendRedirect("/students");
        //listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //data: students
        ArrayList<Student> students = new ArrayList<>();

        students = service.getStudents();
        // => view: studentList.jsp
        request.setAttribute("students", students);
        request.getRequestDispatcher("/view/studentList.jsp").forward(request, response);
    }

}
