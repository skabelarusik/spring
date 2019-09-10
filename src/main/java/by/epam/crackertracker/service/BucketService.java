package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.BucketDaoJdbcImpl;
import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BucketService {
    public List<Bucket> addProduct(String login, String product, String portions) throws TrackerServiceException {
        LoginValidator validator = new LoginValidator();
        ProductNameValidator productNameValidator = new ProductNameValidator();
        PortionsValidator portionsValidator = new PortionsValidator();
        BucketDaoJdbcImpl dao;
        List<Bucket> list = new ArrayList<>();
        boolean status = false;
        try{
            if(validator.isValidate(login) && productNameValidator.isValidate(product) &&
                    portionsValidator.isValidate(portions)){
                Double port = Double.parseDouble(portions);
                dao = new BucketDaoJdbcImpl();
                status = dao.insert(login, product, port);
                if(status){
                    list = dao.selectAll(login);
                } else {
                    throw new TrackerServiceException("Wrong add Product");
                }
            } else {
                throw new TrackerServiceException("Wrong add Product");
            }
        } catch (TrackerDBException e){
            throw new TrackerServiceException("Wrong add Product");
        }
        return list;
    }

    public boolean removeAll(String login) throws TrackerServiceException {
        LoginValidator validator = new LoginValidator();
        BucketDaoJdbcImpl dao;
        boolean status = false;
        if(validator.isValidate(login)){
            dao = new BucketDaoJdbcImpl();
            try {
                status = dao.removeAll(login);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException("Wrong login in bucket service");
            }
        } else {
            throw new TrackerServiceException("Wrong login in bucket service");
        }
        return status;
    }

    public List<Bucket> removeId(String id, String login) throws TrackerServiceException {
        IdValidator validator = new IdValidator();
        LoginValidator validator1 = new LoginValidator();
        List<Bucket> list = new ArrayList<>();
        boolean status = false;
        BucketDaoJdbcImpl dao;
        if(validator.isValidate(id) && validator1.isValidate(login)){
            dao = new BucketDaoJdbcImpl();
            int idProd = Integer.parseInt(id);
            try {
                status = dao.deleteById(idProd);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException("Wrong service delete product from bucket id:" + id);
            }
            if(status){
                try {
                    list = dao.selectAll(login);
                } catch (TrackerDBException e) {
                    throw new TrackerServiceException("Wrong service delete product from bucket id:" + id);
                }
            }
        } return list;
    }
}
