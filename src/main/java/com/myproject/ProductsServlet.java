package com.myproject;


import com.myproject.postgres.business.ConnectorForDB;
import com.myproject.postgres.business.Product;
import com.myproject.postgres.business.ProductDBModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private ConnectorForDB connectorForDB;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resulSet;

    public ProductsServlet() {
        this.connectorForDB = ConnectorForDB.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter writer = response.getWriter();

        try {
            connection = connectorForDB.createConnection();
            writer.println("Successful connection to DB.");

        }  catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }


    }
}