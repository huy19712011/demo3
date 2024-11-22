package com.example.demo3;

import com.example.demo3.entity.Product;
import com.example.demo3.service.CategoryService;
import com.example.demo3.service.ProductService;
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

        Long productId = Long.parseLong(request.getParameter("product_id"));
        System.out.println("Product id: " + productId);
        Product product = service.getProductById(productId);

        String msg = "";
        if (product != null) {
            msg = productId + " already exists";
        }

        response.setContentType("text/plain");
        response.getWriter().write(msg);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
