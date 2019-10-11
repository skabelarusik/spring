package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.BucketDao;
import by.epam.crackertracker.dao.BucketDaoJdbcImpl;
import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BucketService {
    @Autowired
    private BucketDao dao;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private ProductNameValidator productNameValidator;

    @Autowired
    private PortionsValidator portionsValidator;

    @Autowired
    private IdValidator idValidator;

    public List<Bucket> addProduct(String login, String product, String portions) throws TrackerServiceException {
        List<Bucket> list = new ArrayList<>();
        boolean status = false;
        try{
            if(loginValidator.isValidate(login) && productNameValidator.isValidate(product) &&
                    portionsValidator.isValidate(portions)) {
                Double port = Double.parseDouble(portions);
                dao = new BucketDaoJdbcImpl();
                dao.insert(login, product, port);
                if (status) {
                    list = dao.selectAll(login);
                } else {
                    throw new TrackerServiceException("Wrong add Product");
                }
            }
        } catch (TrackerDBException e){
            throw new TrackerServiceException("Wrong add Product");
        }
        return list;
    }

    public void removeAll(String login) throws TrackerServiceException {
        if(loginValidator.isValidate(login)){
            dao = new BucketDaoJdbcImpl();
            try {
                dao.removeAll(login);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException("Wrong login in bucket service");
            }
        } else {
            throw new TrackerServiceException("Wrong login in bucket service");
        }
    }

    public List<Bucket> removeId(String id, String login) throws TrackerServiceException {
        List<Bucket> list = new ArrayList<>();
        boolean status = false;
        if(idValidator.isValidate(id) && loginValidator.isValidate(login)){
            dao = new BucketDaoJdbcImpl();
            int idProd = Integer.parseInt(id);
            try {
                dao.deleteById(idProd);
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
