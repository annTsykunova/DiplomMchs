package by.bsu.modulestatic.service;

import by.bsu.modulestatic.dao.TableDao;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.CallReason;
import by.bsu.modulestatic.entity.DictionaryRegions;
import by.bsu.modulestatic.entity.VechicleClass;
import by.bsu.modulestatic.entity.VechicleType;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TableService {

    @Autowired
    TableDao tableDao;

    public List<CallReason> getAllCallReason() throws ServiceException {
        List<CallReason> callReasons = null;
        try {
            callReasons =  tableDao.getAllCallReason();
        } catch (DaoException e) {
           throw new ServiceException(e);
        }
        return callReasons;
    }

    public List<DictionaryRegions> getAllRegions() throws ServiceException {
        List<DictionaryRegions> dictionaryRegionses = null;
        try {
            dictionaryRegionses =  tableDao.getAllRegions();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dictionaryRegionses;
    }
    public List<VechicleClass> getAllVechicleClass() throws ServiceException {
        List<VechicleClass> vechicleClasses = null;
        try {
            vechicleClasses =  tableDao.getAllVechicleClass();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return vechicleClasses;
    }
    public List<VechicleType> getAllVechicleType() throws ServiceException {
        List<VechicleType> vechicleTypes = null;
        try {
            vechicleTypes =  tableDao.getAllVechicleType();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return vechicleTypes;
    }

    public void deleteItem(int id,String table) throws ServiceException {
        try {
            tableDao.deleteItem(id,table);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateCallReason(CallReason callReason) throws ServiceException {
        try {
            if(callReason.getReasonId()!=0) {
                tableDao.updateCallReason(callReason);
            }else {
                tableDao.insertCallReason(callReason);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateVechicleType(VechicleType vechicleType) throws ServiceException {
        try {
            if(vechicleType.getVersionId()!= 0) {
                tableDao.updateVechicleType(vechicleType);
            }else {
                tableDao.insertVechicleType(vechicleType);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateVechicleClass(VechicleClass vechicleClass) throws ServiceException {
        try {
            if(vechicleClass.getVechicleId()!=0) {
                tableDao.updateVechicleClass(vechicleClass);
            }else {
                tableDao.insertVechicleClass(vechicleClass);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateDictionaryRegions(DictionaryRegions dictionaryRegions) throws ServiceException {
        try {
            if(dictionaryRegions.getRegionId() != 0) {
                tableDao.updateDictionaryRegions(dictionaryRegions);
            }else {
                tableDao.insertDictionary(dictionaryRegions);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String getNameCallReasonById(int id) throws ServiceException {
        String reason;
        try {
            reason = tableDao.getNameCallReasonById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reason;
    }
}
