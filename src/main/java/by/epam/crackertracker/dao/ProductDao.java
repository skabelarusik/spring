/**
 * interface for different product dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface ProductDao extends TrackerDao {

    List<Product> selectAll(int pag, String type) throws TrackerDBException;

    void deleteById(int id, String name) throws TrackerDBException;

    void insert(Product product) throws TrackerDBException;

    void updateProduct(Product product) throws TrackerDBException;

    List<Product> selectAllByRangeCallories(int min, int max, int page, String type) throws TrackerDBException;

    List<Product> searchProducts(String[] product) throws TrackerDBException;

}
