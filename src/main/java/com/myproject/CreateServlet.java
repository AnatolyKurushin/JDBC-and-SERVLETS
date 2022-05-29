package com.myproject;


import com.myproject.postgres.business.Product;
import com.myproject.postgres.business.ProductDBModel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            Product product = new Product(name, price);
            ProductDBModel.insert(product);
            response.sendRedirect(request.getContextPath()+"/index");
        } catch(Exception ex) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }
}
