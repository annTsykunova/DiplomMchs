package by.bsu.modulestatic.service;

import by.bsu.modulestatic.dao.MainDao;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MainService {
    @Autowired
    MainDao mainDao;

    public List<String> getAllTables() throws ServiceException {
        List<String> tables;
        List<String> newTables = new ArrayList<>();
        try {
            tables = mainDao.getAllTables();
            for(String str: tables){
                if(!str.equals("users")) {
                    if (str.contains("_")) {
                        String[] s = str.split("_");
                        newTables.add(s[0] + " " + s[1]);
                    } else {
                        newTables.add(str);
                    }
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newTables;
    }
}
