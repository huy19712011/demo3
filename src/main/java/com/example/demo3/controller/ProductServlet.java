package com.example.demo3.controller;

import com.example.demo3.entity.Category;
import com.example.demo3.entity.Product;
import com.example.demo3.service.CategoryService;
import com.example.demo3.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = {
        "/products",
        "/products/new",
        "/products/insert",
        "/products/delete",
        "/products/edit",
        "/products/update"
})
public class ProductServlet extends HttpServlet {

    private ProductService service = new ProductService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        String path = request.getServletPath();
        System.out.println(path);
        switch (path) {
            case "/products":
                listProducts(request, response);
                break;
            case "/products/new":
                showNewForm(request, response);
                break;
            case "/products/insert":
                insertProduct(request, response);
                break;
            case "/products/delete":
                deleteProduct(request, response);
                break;
            case "/products/edit":
                editProduct(request, response);
                break;
            case "/products/update":
                updateProduct(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        //processRequest(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Category> categories = categoryService.getCategories();

        request.setAttribute("categories", categories);

        getServletContext()
                .getRequestDispatcher("/view/addProductForm.jsp")
                .forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // read product info from form
        Product product = getProductFromForm(request);

        // update product
        service.updateProduct(product);

        // redirect to list products
        response.sendRedirect("/products");
    }

    private Product getProductFromForm(HttpServletRequest request) {

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        long categoryId = Long.parseLong(request.getParameter("category_id"));
        Category category = categoryService.getCategoryById(categoryId);

        // create product object
        Product product = new Product(id, name);
        product.setCategory(category);
        return product;
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // load product info to the form
        // get product id from form
        Long id = Long.parseLong(request.getParameter("id"));
        System.out.println(id);
        // get product by id
        Product product = service.getProductById(id);
        // send product to form
        request.setAttribute("product", product);
        // categories for dropdown list; categoryId for selected item
        ArrayList<Category> categories = categoryService.getCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("categoryId", product.getCategory().getId());
        // redirect to form
        request
                .getRequestDispatcher("/view/updateProductForm.jsp")
                .forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long id = Integer.parseInt(request.getParameter("id"));

        service.deleteProduct(id);

        response.sendRedirect("/products");
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read product info from form
        Product product = getProductFromForm(request);

        // save product to list
        service.addProduct(product);

        // redirect to list products
        response.sendRedirect("/products");
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //data: products
        ArrayList<Product> products = new ArrayList<>();

        products = service.getProducts();
        // => view: productList.jsp
        request.setAttribute("products", products);
        request.getRequestDispatcher("/view/productList.jsp").forward(request, response);
    }

}
