package com.example.demo3;

import com.example.demo3.entity.Category;
import com.example.demo3.entity.Product;
import com.example.demo3.service.CategoryService;
import com.example.demo3.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AjaxDemoServlet", value = "/validateProduct")
public class AjaxDemoServlet extends HttpServlet {

    private ProductService service = new ProductService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("product_name");
        Long productId = Long.parseLong(request.getParameter("product_id"));
        String submit = request.getParameter("submit");
        System.out.println("Product id: " + productId);
        System.out.println("Product name: " + name);
        System.out.println("Submit value: " + submit);
        Product product = service.getProductById(productId);


        String msg = "";
        if (product != null) {
            msg = productId + " already exists";

            response.setContentType("text/plain");
            response.getWriter().write(msg);
        } else if (name != null && !name.isEmpty()) {

            // Respond with success message
            //response.setContentType("text/plain");
            //response.getWriter().write("Product saved successfully!");


            var newProduct = new Product(productId, name);
            Category category = categoryService.getCategoryById(1001L);
            newProduct.setCategory(category);
            service.addProduct(newProduct);

            ObjectMapper objectMapper = new ObjectMapper();
            String productJson = objectMapper.writeValueAsString(newProduct);
            System.out.println(productJson.toString());
            response.setContentType("application/json");
            response.getWriter().write(productJson);


        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

        //String name = request.getParameter("product_name");
        //String productIdParam = request.getParameter("product_id");
        //
        //if (productIdParam != null && !productIdParam.isEmpty() && name != null && !name.isEmpty()) {
        //    Long productId = Long.parseLong(productIdParam);
        //
        //    Product existingProduct = service.getProductById(productId);
        //    if (existingProduct != null) {
        //        response.setContentType("text/plain");
        //        response.getWriter().write("Product ID " + productId + " already exists.");
        //    } else {
        //        var newProduct = new Product(productId, name);
        //        Category category = categoryService.getCategoryById(1001L);
        //        newProduct.setCategory(category);
        //        service.addProduct(newProduct);
        //
        //        response.setContentType("text/plain");
        //        response.getWriter().write("Product saved successfully!");
        //    }
        //} else {
        //    response.setContentType("text/plain");
        //    response.getWriter().write("Invalid input. Please provide both Product ID and Product Name.");
        //}
    }
}
