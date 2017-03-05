package by.bsu.modulestatic.service;

import by.bsu.modulestatic.dao.MainDao;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainService {
    @Autowired
    MainDao mainDao;

    public List<String> getAllTables() throws ServiceException {
        List<String> tables;
        try {
            tables = mainDao.getAllTables();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tables;
    }
}
