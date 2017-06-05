package by.bsu.modulestatic.controller;

import by.bsu.modulestatic.entity.*;
import by.bsu.modulestatic.entity.wrapper.StatisticCallsListWrapper;
import by.bsu.modulestatic.entity.wrapper.StatisticValueListWrapper;
import by.bsu.modulestatic.service.RequestService;
import by.bsu.modulestatic.service.TableService;
import by.bsu.modulestatic.service.util.ServiceException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RequestController {
    @Autowired
    RequestService requestService;

    @Autowired
    TableService tableService;

    @ModelAttribute("allRegions")
    public List<DictionaryRegions> populateRegions() throws ServiceException {
        return tableService.getAllRegions();
    }

    @ModelAttribute("allVecclass")
    public List<VechicleClass> populateVecclass() throws ServiceException {
        return tableService.getAllVechicleClass();
    }

    @ModelAttribute("allReason")
    public List<CallReason> populateReason() throws ServiceException {
        return tableService.getAllCallReason();
    }

    @RequestMapping("/request")
    public String requestInfo() {
        return "request";
    }

    @ModelAttribute("region")
    public DictionaryRegions addTagToModel() {
        return new DictionaryRegions();
    }

    @ModelAttribute("callReason")
    public CallReason addCallReason() {
        return new CallReason();
    }

    @ModelAttribute("vechicleClass")
    public VechicleClass addVecclass() {
        return new VechicleClass();
    }

    @RequestMapping("/request/count")
    public ModelAndView getStatistics(@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        //StatisticValueListWrapper listWrapper1 = new StatisticValueListWrapper();
        List<StatisticCalls> callses = requestService.getStatisticsCalls(startDate,endDate);
        listWrapper.setStatisticCallses(new ArrayList<>(callses));
        modelAndView.addObject("callses",listWrapper);
        double average = requestService.getAverageValue(callses);
        double averagePow = requestService.getAveragePow(callses,average);
        int variations = requestService.getVariationsInScope(callses);
        /*List<String> listStatisticsVar1 = new ArrayList<>();
        listStatisticsVar1.add(String.format("%.2f",average));
        listStatisticsVar1.add(String.format("%.4f",requestService.getDisperssion(callses,average)));
        listStatisticsVar1.add(String.format("%.4f",averagePow));
        listStatisticsVar1.add(String.valueOf(variations));
        listStatisticsVar1.add( String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        listStatisticsVar1.add(String.format("%.2f",requestService.getOscilationCoefficient(average,variations)));
        listWrapper1.setStatisticValue(new ArrayList<>(listStatisticsVar1));
        modelAndView.addObject("listStatisticsVar6",listWrapper1);*/
        modelAndView.addObject("average1", String.format("%.2f",average));
        modelAndView.addObject("dispersia1",String.format("%.4f",requestService.getDisperssion(callses,average)));
        modelAndView.addObject("averageSqrt1", String.format("%.4f",averagePow));
        modelAndView.addObject("variations1", variations);
        modelAndView.addObject("coefOfVaer1", String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        modelAndView.addObject("oscilCoeff1", String.format("%.2f",requestService.getOscilationCoefficient(average,variations)) );
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/region")
    public ModelAndView getStatisticsCallsByRegion(@ModelAttribute("region") DictionaryRegions region,@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        //StatisticValueListWrapper listWrapper1 = new StatisticValueListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsCallByRegion(startDate,endDate,region.getRegionId());
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("calls",listWrapper);
        double average = requestService.getAverageValue(calls);
        double averagePow = requestService.getAveragePow(calls,average);
        int variations = requestService.getVariationsInScope(calls);
        /*List<String> listStatisticsVar2 = new ArrayList<>();
        listStatisticsVar2.add(String.format("%.2f",average));
        listStatisticsVar2.add(String.format("%.4f",requestService.getDisperssion(calls,average)));
        listStatisticsVar2.add(String.format("%.4f",averagePow));
        listStatisticsVar2.add(String.valueOf(variations));
        listStatisticsVar2.add( String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        listStatisticsVar2.add(String.format("%.2f",requestService.getOscilationCoefficient(average,variations)));
        listWrapper1.setStatisticValue(new ArrayList<>(listStatisticsVar2));
        modelAndView.addObject("listStatisticsVar6",listWrapper1);*/
        modelAndView.addObject("average2", String.format("%.2f",average));
        modelAndView.addObject("dispersia2",String.format("%.4f",requestService.getDisperssion(calls,average)));
        modelAndView.addObject("averageSqrt2", String.format("%.4f",averagePow));
        modelAndView.addObject("variations2", variations);
        modelAndView.addObject("coefOfVaer2", String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        modelAndView.addObject("oscilCoeff2", String.format("%.2f",requestService.getOscilationCoefficient(average,variations)) );
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/reason/region")
    public ModelAndView getStatisticsReasonByRegion(@ModelAttribute("region") DictionaryRegions region,@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        //StatisticValueListWrapper listWrapper1 = new StatisticValueListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsReasonByRegion(startDate,endDate,region.getRegionId());
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("callsReason",listWrapper);
        double average = requestService.getAverageValue(calls);
        double averagePow = requestService.getAveragePow(calls,average);
        int variations = requestService.getVariationsInScope(calls);
        /*List<String> listStatisticsVar3 = new ArrayList<>();
        listStatisticsVar3.add(String.format("%.2f",average));
        listStatisticsVar3.add(String.format("%.4f",requestService.getDisperssion(calls,average)));
        listStatisticsVar3.add(String.format("%.4f",averagePow));
        listStatisticsVar3.add(String.valueOf(variations));
        listStatisticsVar3.add( String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        listStatisticsVar3.add(String.format("%.2f",requestService.getOscilationCoefficient(average,variations)));
        listWrapper1.setStatisticValue(new ArrayList<>(listStatisticsVar3));*/
        //modelAndView.addObject("listStatisticsVar6",listWrapper1);
        modelAndView.addObject("average3", String.format("%.2f",average));
        modelAndView.addObject("dispersia3",String.format("%.4f",requestService.getDisperssion(calls,average)));
        modelAndView.addObject("averageSqrt3", String.format("%.4f",averagePow));
        modelAndView.addObject("variations3", variations);
        modelAndView.addObject("coefOfVaer3", String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        modelAndView.addObject("oscilCoeff3", String.format("%.2f",requestService.getOscilationCoefficient(average,variations)) );
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/region/reason")
    public ModelAndView getStatisticsRegionByReason(@ModelAttribute("region") DictionaryRegions region,@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        //StatisticValueListWrapper listWrapper1 = new StatisticValueListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsCallsRegionsBy(startDate,endDate);
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("callsRegion",listWrapper);
        double average = requestService.getAverageValue(calls);
        double averagePow = requestService.getAveragePow(calls,average);
        int variations = requestService.getVariationsInScope(calls);
        /*List<String> listStatisticsVar4 = new ArrayList<>();
        listStatisticsVar4.add(String.format("%.2f",average));
        listStatisticsVar4.add(String.format("%.4f",requestService.getDisperssion(calls,average)));
        listStatisticsVar4.add(String.format("%.4f",averagePow));
        listStatisticsVar4.add(String.valueOf(variations));
        listStatisticsVar4.add( String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        listStatisticsVar4.add(String.format("%.2f",requestService.getOscilationCoefficient(average,variations)));
        listWrapper1.setStatisticValue(new ArrayList<>(listStatisticsVar4));
        modelAndView.addObject("listStatisticsVar6",listWrapper1);*/
        modelAndView.addObject("average4", String.format("%.2f",average));
        modelAndView.addObject("dispersia4",String.format("%.4f",requestService.getDisperssion(calls,average)));
        modelAndView.addObject("averageSqrt4", String.format("%.4f",averagePow));
        modelAndView.addObject("variations4", variations);
        modelAndView.addObject("coefOfVaer4", String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        modelAndView.addObject("oscilCoeff4", String.format("%.2f",requestService.getOscilationCoefficient(average,variations)) );
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/reason/reason")
    public ModelAndView getStatisticsReasonByReason(@ModelAttribute("callReason") CallReason callReason, @RequestParam("startdate")String startDate, @RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        //StatisticValueListWrapper listWrapper1 = new StatisticValueListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsCallsReasonByReason(startDate,endDate,callReason.getReasonId());
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("callsReasonDate",listWrapper);
        double average = requestService.getAverageValue(calls);
        double averagePow = requestService.getAveragePow(calls,average);
        int variations = requestService.getVariationsInScope(calls);
        /*List<String> listStatisticsVar5 = new ArrayList<>();
        listStatisticsVar5.add(String.format("%.2f",average));
        listStatisticsVar5.add(String.format("%.4f",requestService.getDisperssion(calls,average)));
        listStatisticsVar5.add(String.format("%.4f",averagePow));
        listStatisticsVar5.add(String.valueOf(variations));
        listStatisticsVar5.add( String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        listStatisticsVar5.add(String.format("%.2f",requestService.getOscilationCoefficient(average,variations)));
        listWrapper1.setStatisticValue(new ArrayList<>(listStatisticsVar5));
        modelAndView.addObject("listStatisticsVar6",listWrapper1);*/
        modelAndView.addObject("average5", String.format("%.2f",average));
        modelAndView.addObject("dispersia5",String.format("%.4f",requestService.getDisperssion(calls,average)));
        modelAndView.addObject("averageSqrt5", String.format("%.4f",averagePow));
        modelAndView.addObject("variations5", variations);
        modelAndView.addObject("coefOfVaer5", String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        modelAndView.addObject("oscilCoeff5", String.format("%.2f",requestService.getOscilationCoefficient(average,variations)) );
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/reason/vecclass")
    public ModelAndView getStatisticsReasonByVecclass(@ModelAttribute("vechicleClass") VechicleClass vechicleClass, @RequestParam("startdate")String startDate, @RequestParam("enddate") String endDate ) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        StatisticValueListWrapper listWrapper1 = new StatisticValueListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsCallsReasonByVecclass(startDate,endDate,vechicleClass.getClassId());
        if(!calls.isEmpty()) {
            listWrapper.setStatisticCallses(new ArrayList<>(calls));
            modelAndView.addObject("callsReasonDateVecclass", listWrapper);
            double average = requestService.getAverageValue(calls);
            double averagePow = requestService.getAveragePow(calls, average);
            int variations = requestService.getVariationsInScope(calls);
        /*List<String> listStatisticsVar6 = new ArrayList<>();
        listStatisticsVar6.add(String.format("%.2f",average));
        listStatisticsVar6.add(String.format("%.4f",requestService.getDisperssion(calls,average)));
        listStatisticsVar6.add(String.format("%.4f",averagePow));
        listStatisticsVar6.add(String.valueOf(variations));
        listStatisticsVar6.add( String.format("%.2f",requestService.getCoefficientOfVariation(average, averagePow)));
        listStatisticsVar6.add(String.format("%.2f",requestService.getOscilationCoefficient(average,variations)));
        listWrapper1.setStatisticValue(new ArrayList<>(listStatisticsVar6));
        modelAndView.addObject("listStatisticsVar6",listWrapper1);*/
            modelAndView.addObject("average6", String.format("%.2f", average));
            modelAndView.addObject("dispersia6", String.format("%.4f", requestService.getDisperssion(calls, average)));
            modelAndView.addObject("averageSqrt6", String.format("%.4f", averagePow));
            modelAndView.addObject("variations6", variations);
            modelAndView.addObject("coefOfVaer6", String.format("%.2f", requestService.getCoefficientOfVariation(average, averagePow)));
            modelAndView.addObject("oscilCoeff6", String.format("%.2f", requestService.getOscilationCoefficient(average, variations)));
        }
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping(value = "/request/export", method = RequestMethod.POST)
    public ModelAndView exportToFile(@ModelAttribute("callsReasonDateVecclass") StatisticCallsListWrapper calls, @ModelAttribute("listStatisticsVar3") StatisticValueListWrapper values){
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("request");
        ArrayList<StatisticCalls> callses = calls.getStatisticCallses();
        ArrayList<String> valuess = values.getStatisticValue();
        modelAndView.setViewName("excelView");
        modelAndView.addObject("statisticCalls", callses);
        modelAndView.addObject("statisticValues", valuess);
        return modelAndView;
    }

   /* @RequestMapping(value = "/request/piechart")
    public void drawPie(){
        getPieChart();
    }*/

/*private void getPieChart() {

        DefaultPieDataset pieDataset = new DefaultPieDataset();
    pieDataset.setValue("JavaWorld", new Integer(75));
    pieDataset.setValue("Other", new Integer(25));

        JFreeChart chart = ChartFactory.createPieChart("Discounts Used by Category ", pieDataset, true, true, false);
        //chart.setBackgroundPaint(new Color(222, 222, 255));
            final PiePlot plot = (PiePlot) chart.getPlot();
            plot.setBackgroundPaint(Color.BLUE);
            plot.setCircular(true);

        try {

            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file1 = new File("D:\\images\\piechart.png");

            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
        } catch (Exception e) {
            System.out.println(e);

        }
    }*/

}
