package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.BucketDao;
import by.epam.crackertracker.dao.BucketDaoJdbcImpl;
import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public List<Bucket> selectAll(String login) throws TrackerServiceException {
        List<Bucket> list = null;
        try {
            list = dao.selectAll(login);
        }catch (TrackerDBException e){
            throw new TrackerServiceException("Wrong select bucket elements");
        }
        return list;
    }

    public List<Bucket> addProduct(String login, String product, String portions) throws TrackerServiceException {
        List<Bucket> list = new ArrayList<>();
        try{
            if(loginValidator.isValidate(login) && productNameValidator.isValidate(product) &&
                    portionsValidator.isValidate(portions)) {
                Double port = Double.parseDouble(portions);
                 dao.insert(login, product, port);
                list = dao.selectAll(login);
                } else {
                throw new TrackerServiceException("Wrong add Product");
            }
        } catch (TrackerDBException e){
            throw new TrackerServiceException("Wrong add Product");
        }
        return list;
    }

    public void removeAll(String login) throws TrackerServiceException {
        if(loginValidator.isValidate(login)){
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
        if(idValidator.isValidate(id) && loginValidator.isValidate(login)){
            dao = new BucketDaoJdbcImpl();
            int idProd = Integer.parseInt(id);
            try {
                dao.deleteById(idProd);
                list = dao.selectAll(login);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException("Wrong service delete product from bucket id:" + id);
            }
        } else {
            throw new TrackerServiceException("Wrong service delete product from bucket id:" + id);
        }
        return list;
    }

    public int calculate(String login) throws TrackerServiceException {
        int result = 0;
        try {
            List<Bucket> list = dao.selectAll(login);
            for(int i = 0; i < list.size(); i++){
                result += (list.get(i).getCalories() * list.get(i).getPortions());
            }
        } catch (TrackerDBException e) {
            throw new TrackerServiceException("Wrong select bucket");
        }
        return result;
    }
}
