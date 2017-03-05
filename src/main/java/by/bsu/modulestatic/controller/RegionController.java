package by.bsu.modulestatic.controller;

import by.bsu.modulestatic.entity.CallReason;
import by.bsu.modulestatic.entity.DictionaryRegions;
import by.bsu.modulestatic.entity.VechicleClass;
import by.bsu.modulestatic.entity.VechicleType;
import by.bsu.modulestatic.service.TableService;
import by.bsu.modulestatic.service.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RegionController {

    @Autowired
    private TableService tableService;

    @RequestMapping(value = "/tables", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView showSingleTable(@RequestParam("table")String table) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        if(table.equals("call_reason")){
            List<CallReason> allCallReason = tableService.getAllCallReason();
            modelAndView.addObject("allCallReason",allCallReason);
            modelAndView.addObject("callReason",new CallReason());
        }else if(table.equals("dictionary_regions")){
            List<DictionaryRegions> allRegions = tableService.getAllRegions();
            modelAndView.addObject("allRegions",allRegions);
            modelAndView.addObject("region",new DictionaryRegions());
        }else if(table.equals("vechicle_class")){
            List<VechicleClass> allVechicleClass = tableService.getAllVechicleClass();
            modelAndView.addObject("allVechicleClass",allVechicleClass);
            modelAndView.addObject("vechicleClass",new VechicleClass());
        }else if(table.equals("vechicle_type")){
            List<VechicleType> allVechicleType = tableService.getAllVechicleType();
            modelAndView.addObject("allVechicleType",allVechicleType);
            modelAndView.addObject("vechicleType",new VechicleType());
        }
        modelAndView.setViewName("tables");
        return modelAndView;
    }
    @RequestMapping(value = "/tables/edit", method = {RequestMethod.POST,RequestMethod.GET}, params={"save"})
    public ModelAndView saveCallReason(@ModelAttribute("callReason")CallReason callReason,@ModelAttribute("vechicleType")VechicleType vechicleType, @ModelAttribute("vechicleClass") VechicleClass vechicleClass, @ModelAttribute("region") DictionaryRegions region, BindingResult result)
            throws ServiceException{
        String table = null;
        if (!result.hasErrors()) {
            if(callReason.getReasonId()!= 0 || callReason.getNameReason()!= null) {
                tableService.updateCallReason(callReason);
                table = "call_reason";
            }else if(vechicleType.getVersionId() != 0 || vechicleType.getNameType() != null){
                tableService.updateVechicleType(vechicleType);
                table = "vechicle_type";
            }else if( vechicleClass.getVechicleId()!= 0 || vechicleClass.getName() != null){
                tableService.updateVechicleClass(vechicleClass);
                table = "vechicle_class";
            }else if(region.getRegionId()!= 0 || region.getRegionName() != null){
                tableService.updateDictionaryRegions(region);
                table = "dictionary_regions";
            }
        }
        return new ModelAndView("redirect:/tables").addObject("table",table);
    }

    @RequestMapping(value = "/tables/edit/delete",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView deleteReason(@RequestParam("id") int id,@RequestParam("table")String table) throws ServiceException{
        tableService.deleteItem(id, table);
        return new ModelAndView("redirect:/tables").addObject("table",table);
    }


}
