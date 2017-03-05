package by.bsu.modulestatic.controller;

import by.bsu.modulestatic.dao.MainDao;
import by.bsu.modulestatic.service.MainService;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    MainService mainService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView showAllRegions() throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        List<String> tables = mainService.getAllTables();
        modelAndView.addObject("allTables",tables);
        modelAndView.setViewName("main");
        return modelAndView;
    }

}
