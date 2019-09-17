/**
 * jdbc dao class with different method for receive table products in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerConnectionPoolException;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcTest {
/*
    public static final String SELECT_ALL_PRODUCT = "SELECT idproducts, name, calories, proteins,  fats, carbs from products limit ? offset ?";
    public static final String SELECT_ALL_PRODUCT_INCREASE_CALORIES = "SELECT idproducts, name, calories," +
            "proteins, fats, carbs from products ORDER BY calories limit ? offset ?";
    public static final String SELECT_ALL_PRODUCT_DECREASE_CALORIES = "SELECT idproducts, name, calories," +
            "proteins, fats, carbs from products ORDER BY calories DESC limit ? offset ?";
    public static final String SELECT_ALL_PRODUCT_DECREASE_NAME = "SELECT idproducts, name, calories," +
            "proteins, fats, carbs from products ORDER BY name DESC limit ? offset ?";
    public static final String SELECT_ALL_PRODUCT_INREASE_NAME = "SELECT idproducts, name, calories," +
            "proteins, fats, carbs from products ORDER BY name limit ? offset ?";

    public static final String SELECT_PRODUCTS_RANGE_CALORIES = "SELECT idproducts, name, calories, proteins, fats, carbs " +
            "from products where calories between ? and ? limit ? offset ?";
    public static final String SELECT_PRODUCTS_RANGE_CALORIES_INCREASE = "SELECT idproducts, name, calories, proteins, fats, carbs " +
            "from products where calories between ? and ? ORDER BY calories limit ? offset ?";
    public static final String SELECT_PRODUCTS_RANGE_CALORIES_DECREASE = "SELECT idproducts, name, calories, proteins, fats, carbs " +
            "from products where calories  between ? and ? ORDER BY calories DESC limit ? offset ?";
    public static final String SELECT_PRODUCTS_RANGE_NAME_DECREASE = "SELECT idproducts, name, calories, proteins, fats, carbs " +
            "from products where calories  between ? and ? ORDER BY name DESC limit ? offset ?";
    public static final String SELECT_PRODUCTS_RANGE_NAME_INCREASE = "SELECT idproducts, name, calories, proteins, fats, carbs " +
            "from products where calories between ? and ? ORDER BY name limit ? offset ?";
    public static final String SEARCH_PRODUCTS = "SELECT idproducts, name, calories, proteins,  fats, carbs from products where name like ?";
    public static final String INSERT_PRODUCT = "INSERT INTO products (name, calories, proteins, fats, carbs) VALUES (?,?,?,?,?)";
    public static final String SELECT_NAME_PRODUCT = "SELECT idproducts from products where name = ?";
    public static final String SELECT_ID_PRODUCT = "SELECT idproducts from products where idproducts = ?";
    public static final String DELETE_BY_ID = "DELETE FROM products where idproducts = ?";
    public static final String SELECT_BY_NAME_ID = "SELECT idproducts FROM products where idproducts = ? and name = ?";
    public static final String UPDATE_PRODUCT_DATA = "UPDATE products SET name = ?, calories = ?, proteins = ?, fats = ? , " +
            "carbs = ? WHERE idproducts = ?";
    public static final int COUNT_PRODUCTS = 11;

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public List<Product> selectAll(int page, String type) throws TrackerDBException {

        Connection connection = null;
        List<Product> listProduct = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlReq = null;
        switch (type){
            case "INCREASE_CALORIES": sqlReq = SELECT_ALL_PRODUCT;
                break;
            case "DECREASE_CALORIES": sqlReq = SELECT_ALL_PRODUCT_DECREASE_CALORIES;
                break;
            case "INCREASE_NAME": sqlReq = SELECT_ALL_PRODUCT_INREASE_NAME;
                break;
            case "DECREASE_NAME": sqlReq = SELECT_ALL_PRODUCT_DECREASE_NAME;
                break;
            default: sqlReq = SELECT_ALL_PRODUCT_INCREASE_CALORIES;
                break;
        }
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1,COUNT_PRODUCTS);
            statement.setInt(2,((page - 1) * COUNT_PRODUCTS - (page - 1)));
            resultSet = statement.executeQuery();
            listProduct = createListProduct(resultSet);
        } catch (TrackerConnectionPoolException | SQLException ex) {
            LOGGER.error(ex);
            throw new TrackerDBException("Wrong statement or connection");
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return listProduct;
    }

    @Override
    public boolean deleteById(int id, String name) throws TrackerDBException {
        boolean status = false;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if (hasIdName(connection, id, name)) {
                PreparedStatement statement = null;
                try {
                    statement = connection.prepareStatement(DELETE_BY_ID);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } finally {
                    this.closeQuietly(statement);
                }
                status = true;
                LOGGER.warn("Product " + name + " was delete");
            }
        } catch (SQLException | TrackerConnectionPoolException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete product");
        } finally {
            this.closeQuietly(connection);
        }
        return status;
    }


    @Override
    public boolean insert(Product product) throws TrackerDBException  {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if(!isUniqueProduct(connection, product.getName())){
                statement = connection.prepareStatement(INSERT_PRODUCT);
                statement.setString(1, product.getName());
                statement.setInt(2, product.getCalories());
                statement.setDouble(3, product.getProteins());
                statement.setDouble(4, product.getFats());
                statement.setDouble(5, product.getCarbs());
                statement.executeUpdate();
                status = true;
                LOGGER.warn("product" + product.getName() + " inserted");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert product");
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

        @Override
    public List<Product> selectAllByRangeCallories(int min, int max, int page, String type) throws TrackerDBException {
        Connection connection = null;
        List<Product> listProduct = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sqlReq = null;
        try{
        if(min <= max && max >= 0){
            connection = ConnectionPool.getInstance().takeConnection();
            switch (type){
                case "INCREASE_CALORIES": sqlReq = SELECT_PRODUCTS_RANGE_CALORIES;
                    break;
                case "DECREASE_CALORIES": sqlReq = SELECT_PRODUCTS_RANGE_CALORIES_DECREASE;
                    break;
                case "INCREASE_NAME": sqlReq = SELECT_PRODUCTS_RANGE_NAME_INCREASE;
                    break;
                case "DECREASE_NAME": sqlReq = SELECT_PRODUCTS_RANGE_NAME_DECREASE;
                    break;
                default: sqlReq = SELECT_PRODUCTS_RANGE_CALORIES_INCREASE;
                    break;
            }
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, min);
            statement.setInt(2, max);
            statement.setInt(3, COUNT_PRODUCTS);
            statement.setInt(4, (page - 1) * COUNT_PRODUCTS - (page - 1));
            resultSet = statement.executeQuery();
            listProduct = createListProduct(resultSet);
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong range calories" ,e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return listProduct;
    }

    @Override
    public boolean updateProduct(Product product) throws TrackerDBException {
        boolean status = false;
        Connection connection  = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            if(hasId(connection, product.getId())) {
                statement = connection.prepareStatement(UPDATE_PRODUCT_DATA);
                statement.setString(1, product.getName());
                statement.setInt(2, product.getCalories());
                statement.setDouble(3, product.getProteins());
                statement.setDouble(4, product.getFats());
                statement.setDouble(5, product.getCarbs());
                statement.setInt(6, product.getId());
                statement.executeUpdate();
                status = true;
                LOGGER.warn("product " + product.getName() + " updated");
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong update product", e);
        } finally {
            this.closeQuietly(statement);
            this.closeQuietly(connection);
        }
        return status;
    }

    public List<Product> searchProducts(String [] param) throws TrackerDBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> list = new ArrayList<>();
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            for(int i = 0; i < param.length; i++){
                statement = connection.prepareStatement(SEARCH_PRODUCTS);
                statement.setString(1, "%" + param[i] + "%");
                resultSet = statement.executeQuery();
                List<Product> temp = createListProduct(resultSet);
                list.addAll(temp);
            }
        } catch (TrackerConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong search products", e);
        } finally {
            closeQuietly(resultSet);
            closeQuietly(statement);
            closeQuietly(connection);
        }
        return list;
    }

    private boolean isUniqueProduct(Connection connection, String name) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status;
        try {
            statement = connection.prepareStatement(SELECT_NAME_PRODUCT);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert product",e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    private boolean hasId(Connection connection, int id) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status = false;
        try {
            statement = connection.prepareStatement(SELECT_ID_PRODUCT);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select id product" ,e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    private boolean hasIdName(Connection connection, int id, String name) throws TrackerDBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean status;
        try {
            statement = connection.prepareStatement(SELECT_BY_NAME_ID);
            statement.setInt(1, id);
            statement.setString(2, name);
            resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong select id product" ,e);
        } finally {
            this.closeQuietly(resultSet);
            this.closeQuietly(statement);
        }
        return status;
    }

    private List<Product> createListProduct(ResultSet resultSet) throws TrackerDBException {
        List<Product> list = new ArrayList<>();
        try{
            while (resultSet.next()) {
                int id = resultSet.getInt(1);

                String name = resultSet.getString(2);
                int calories = resultSet.getInt(3);
                double proteins = resultSet.getDouble(4);
                double fats = resultSet.getDouble(5);
                double carbs = resultSet.getDouble(6);
                Product product = new Product(name, calories);
                product.setIdProducts(id);
                product.setProteins(proteins);
                product.setFats(fats);
                product.setCarbs(carbs);
                list.add(product);
            }
        }catch (SQLException e){
            LOGGER.error(e);
            throw new TrackerDBException("Wrong create product",e);
        }
        return list;
    }

 */
}
