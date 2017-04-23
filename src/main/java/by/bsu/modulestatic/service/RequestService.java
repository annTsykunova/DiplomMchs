package by.bsu.modulestatic.service;

import by.bsu.modulestatic.dao.RequestDao;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.Calls;
import by.bsu.modulestatic.entity.StatisticCalls;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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

    public List<StatisticCalls> getStatisticsCallsReasonByReason(String startDate, String endDate, int reasonId) throws ServiceException {
        List<StatisticCalls> callses;
        try {
            callses = requestDao.getStatisticsCallsReasonsByReason(startDate, endDate, reasonId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return callses;
    }

    public List<StatisticCalls> getStatisticsCallsReasonByVecclass(String startDate, String endDate, int vecclassId) throws ServiceException {
        List<StatisticCalls> callses;
        try {
            callses = requestDao.getStatisticsCallsReasonsByVecclass(startDate, endDate, vecclassId);
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

    public double getAverageValue(List<StatisticCalls> calls) throws ServiceException{
        int sum = 0;
        for(StatisticCalls statisticCalls:calls){
            sum+=statisticCalls.getCount();
        }
        return (double)sum/calls.size();
    }

    public double getDisperssion(List<StatisticCalls> calls, double aver) throws ServiceException{
        double sum = 0;
        for(StatisticCalls statisticCalls:calls){
            sum += Math.pow(statisticCalls.getCount() - aver,2);
        }

        return sum/(calls.size()-1);
    }

    public int getVariationsInScope(List<StatisticCalls> calls){
        int max = calls.get(0).getCount();
        int min = 0;
        for(StatisticCalls statisticCalls:calls){
           if(max < statisticCalls.getCount()){
               max = statisticCalls.getCount();
           }
           if(min > statisticCalls.getCount()){
               min = statisticCalls.getCount();
           }
        }

        return max - min;
    }

    public double getCoefficientOfVariation(double aver, double averPow){
        return (averPow/aver);
    }

    public double getOscilationCoefficient(double aver, int variations){
        return variations/aver;
    }


    public double getAveragePow(List<StatisticCalls> calls, double aver) throws ServiceException{
        double sum = 0;
        for(StatisticCalls statisticCalls:calls){
            sum += Math.pow(statisticCalls.getCount() - aver,2);
        }

        return Math.sqrt(sum/(calls.size()-1));
    }

}
