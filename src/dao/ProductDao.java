package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connection.ConnectionPostgreSQL;
import models.ProductModel;

public class ProductDao {

    private final ConnectionPostgreSQL factoryConnection;

    public ProductDao() {
        this.factoryConnection = new ConnectionPostgreSQL();
    }

    public boolean register(ProductModel productModel) {

        try {

            String query = "INSERT INTO tb_product(code, name, category, purchase, sale, gift, price, status) "
                    + " VALUES( ?, ?, ?, ?, ?, ?, ?, ? )";

            Connection connection = this.factoryConnection.connect();

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, productModel.getCode());
            statement.setString(2, productModel.getName());
            statement.setString(3, productModel.getCategory());
            statement.setBoolean(4, productModel.isPurchase());
            statement.setBoolean(5, productModel.isSale());
            statement.setBoolean(6, productModel.isGift());
            statement.setDouble(7, productModel.getPrice());
            statement.setBoolean(8, productModel.isState());

            int rowsAffected = statement.executeUpdate();
            
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error when registering product: " + e.getMessage());
            return false;
        }
    }
}
