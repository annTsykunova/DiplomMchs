package by.bsu.modulestatic.controller;

import by.bsu.modulestatic.entity.Calls;
import by.bsu.modulestatic.entity.DictionaryRegions;
import by.bsu.modulestatic.entity.StatisticCalls;
import by.bsu.modulestatic.entity.wrapper.StatisticCallsListWrapper;
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
    @RequestMapping("/request")
    public String requestInfo() {
        return "request";
    }

    @ModelAttribute("region")
    public DictionaryRegions addTagToModel() {
        return new DictionaryRegions();
    }

    @RequestMapping("/request/count")
    public ModelAndView getStatistics(@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        List<StatisticCalls> callses = requestService.getStatisticsCalls(startDate,endDate);
        listWrapper.setStatisticCallses(new ArrayList<>(callses));
        modelAndView.addObject("callses",listWrapper);
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/region")
    public ModelAndView getStatisticsCallsByRegion(@ModelAttribute("region") DictionaryRegions region,@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsCallByRegion(startDate,endDate,region.getRegionId());
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("calls",listWrapper);
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/reason/region")
    public ModelAndView getStatisticsReasonByRegion(@ModelAttribute("region") DictionaryRegions region,@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsReasonByRegion(startDate,endDate,region.getRegionId());
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("callsReason",listWrapper);
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping("/region/reason")
    public ModelAndView getStatisticsRegionByReason(@ModelAttribute("region") DictionaryRegions region,@RequestParam("startdate")String startDate,@RequestParam("enddate") String endDate) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        StatisticCallsListWrapper listWrapper = new StatisticCallsListWrapper();
        List<StatisticCalls> calls = requestService.getStatisticsCallsRegionsBy(startDate,endDate);
        listWrapper.setStatisticCallses(new ArrayList<>(calls));
        modelAndView.addObject("callsRegion",listWrapper);
        modelAndView.setViewName("request");
        return  modelAndView;
    }

    @RequestMapping(value = "/request/export", method = RequestMethod.POST)
    public ModelAndView exportToFile(@ModelAttribute("calls") StatisticCallsListWrapper calls){
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("request");
        ArrayList<StatisticCalls> callses = calls.getStatisticCallses();
        return new ModelAndView("excelView", "statisticCalls", callses );
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
