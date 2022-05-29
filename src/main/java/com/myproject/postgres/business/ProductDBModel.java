package com.myproject.postgres.business;


import java.sql.*;
import java.util.ArrayList;

public class ProductDBModel {

    private static ConnectorForDB instance = ConnectorForDB.getInstance();


    public static ArrayList<Product> select() {

        ArrayList<Product> products = new ArrayList<>();

        try (Connection connection = instance.createConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                Product product = new Product(id, name, price);
                products.add(product);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return products;
    }


    public static Product selectOne(int id) {

        Product product = null;

        try (Connection connection = instance.createConnection()) {

            String sql = "SELECT * FROM products WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int prodId = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    product = new Product(prodId, name, price);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return product;
    }


    public static int insert(Product product) {

        try (Connection connection = instance.createConnection()) {
            String sql = "INSERT INTO products (name, price) Values (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getPrice());

                return preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return 0;
    }


    public static int update(Product product) {

        try (Connection connection = instance.createConnection()) {
            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.setInt(3, product.getId());

                return preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }


    public static int delete(int id) {

        try (Connection connection = instance.createConnection()) {
            String sql = "DELETE FROM products WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                return preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
}
