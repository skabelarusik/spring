package by.epam.crackertracker.mapper;

import by.epam.crackertracker.entity.Product;

import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = null;
            product = new Product(resultSet.getString(ParameterConstant.NAME), resultSet.getInt(ParameterConstant.PRODUCT_CALORIES));
            product.setIdProducts(resultSet.getInt(ParameterConstant.PRODUCT_ID));
            product.setCarbs(resultSet.getDouble(ParameterConstant.PRODUCT_CARBS));
            product.setFats(resultSet.getDouble(ParameterConstant.PRODUCT_FATS));
            product.setProteins(resultSet.getDouble(ParameterConstant.PRODUCT_PROTEINS));
        return product;
    }
}
