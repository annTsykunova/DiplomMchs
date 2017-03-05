package by.bsu.modulestatic.service;

import by.bsu.modulestatic.dao.RequestDao;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.Calls;
import by.bsu.modulestatic.entity.StatisticCalls;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RequestService {
    @Autowired
    RequestDao requestDao;

    @Autowired
    TableService tableService;

    public List<StatisticCalls> getStatisticsCalls(String startDate, String endDate) throws ServiceException {
        List<StatisticCalls> callses;
        try {
            callses = requestDao.getStatisticsCalls(startDate,endDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return callses;
    }

    public List<StatisticCalls> getStatisticsCallsRegionsBy(String startDate, String endDate) throws ServiceException {
        List<StatisticCalls> callses;
        try {
            callses = requestDao.getStatisticsCallsRegionsByReason(startDate,endDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return callses;
    }

    public List<StatisticCalls> getStatisticsCallByRegion(String startDate, String endDate, int reasonId) throws ServiceException {
        List<StatisticCalls> callses;
        try {
            callses = requestDao.getStatisticsCallByRegion(startDate,endDate,reasonId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return callses;
    }

    public List<StatisticCalls> getStatisticsReasonByRegion(String startDate, String endDate, int reasonId) throws ServiceException {
        List<StatisticCalls> callses;
        try {
            callses = requestDao.getStatisticsReasonsByRegion(startDate,endDate,reasonId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return callses;
    }

    public Map<String,Integer> getCount(List<Calls> callses) throws ServiceException {
        Map<String,Integer> counts = new HashMap<>();
        int reasonId;
        for(Calls call:callses){
            reasonId = call.getReasonId();
            counts.put(tableService.getNameCallReasonById(reasonId),call.getCount());
        }
        return counts;
    }
}
