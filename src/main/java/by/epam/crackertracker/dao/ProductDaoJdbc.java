/**
 * jdbc dao class with different method for receive table products in DB
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.mapper.ProductMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoJdbc implements ProductDao {

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
    public static final String DELETE_BY_ID = "DELETE FROM products where idproducts = ?";
    public static final String SELECT_BY_ID = "SELECT idproducts FROM products where idproducts = ?";

    public static final String UPDATE_PRODUCT_DATA = "UPDATE products SET name = ?, calories = ?, proteins = ?, fats = ? , " +
            "carbs = ? WHERE idproducts = ?";
    public static final int COUNT_PRODUCTS = 11;

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> selectAll(int page, String type) throws TrackerDBException {
        List<Product> listProduct;
        String sqlReq;
        switch (type){
            case "INCREASE_NAME": sqlReq = SELECT_ALL_PRODUCT_INREASE_NAME;
                break;
            case "DECREASE_NAME": sqlReq = SELECT_ALL_PRODUCT_DECREASE_NAME;
                break;
            case "INCREASE_CALORIES": sqlReq = SELECT_ALL_PRODUCT;
                break;
            case "DECREASE_CALORIES": sqlReq = SELECT_ALL_PRODUCT_DECREASE_CALORIES;
                break;
            default: sqlReq = SELECT_ALL_PRODUCT_INCREASE_CALORIES;
                break;
        }
        try {
            listProduct = template.query(sqlReq, mapper, COUNT_PRODUCTS, ((page - 1) * COUNT_PRODUCTS - (page - 1)));
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new TrackerDBException("Wrong statement or connection");
        }
        return listProduct;
    }

    @Override
    public void deleteById(int id) throws TrackerDBException {
        try {
            template.update(DELETE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong delete product");
        }
    }

    @Override
    public void insert(Product product) throws TrackerDBException  {
        try {
            template.update(INSERT_PRODUCT, product.getName(),product.getCalories(), product.getProteins(),
                    product.getFats(),product.getCarbs() );
            LOGGER.warn("product" + product.getName() + " inserted");
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong insert product");
        }
    }

    @Override
    public List<Product> selectAllByRangeCallories(int min, int max, int page, String type) throws TrackerDBException {
        List<Product> listProduct;
        String sqlReq = null;
        try{
            if(min <= max && max >= 0){
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
                listProduct = template.query(sqlReq, mapper, min, max, COUNT_PRODUCTS,
                        ((page - 1) * COUNT_PRODUCTS - (page - 1)));
            } else  {
                throw new TrackerDBException("Wrong range calories");
            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong range calories" ,e);
        }
        return listProduct;
    }

    @Override
    public void updateProduct(Product product) throws TrackerDBException {
        try {
            template.update(UPDATE_PRODUCT_DATA, product.getName(), product.getCalories(), product.getProteins(),
                    product.getFats(), product.getCarbs(), product.getId());
            LOGGER.warn("product " + product.getName() + " updated");
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong update product", e);
        }
    }

    public List<Product> searchProducts(String [] param) throws TrackerDBException {
        List<Product> tempList = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        try{
            for(int i = 0; i < param.length; i++){
                tempList = template.query(SEARCH_PRODUCTS, mapper, ("%" + param[i] + "%"));
                list.addAll(tempList);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TrackerDBException("Wrong search products", e);
        }
        return list;
    }
}

