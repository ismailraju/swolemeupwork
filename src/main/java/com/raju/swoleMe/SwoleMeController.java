/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raju.swoleMe;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ismailu
 */
@RestController
public class SwoleMeController {

    @Autowired
    private SwoleMeService swoleMe;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public String hello(HttpServletRequest request) {

        System.out.println("request.getParameter(\"BigBreakfastValue\"):" + request.getParameter("BigBreakfastValue"));

        String CalInputValue = request.getParameter("CalInputValue");
        String NumMealsValue = request.getParameter("NumMealsValue");

        String CarbInputValue = request.getParameter("CarbInputValue");
        String ProteinInputValue = request.getParameter("ProteinInputValue");
        String FatInputValue = request.getParameter("FatInputValue");

        String NumVeggiesValue = request.getParameter("NumVeggiesValue");

        String BigBreakfastValue = request.getParameter("BigBreakfastValue");
        String WorkoutDayValue = request.getParameter("WorkoutDayValue");
        String NoCarbsValue = request.getParameter("NoCarbsValue");
        String MetricUnitsValue = request.getParameter("MetricUnitsValue");

        String DietSelectorValue = request.getParameter("DietSelectorValue");//paleo,vegetarian,vegan,nonspecific,default

        System.out.println(
                "\n CalInputValue :" + CalInputValue
                + "\n NumMealsValue :" + NumMealsValue
                + "\n CarbInputValue :" + CarbInputValue
                + "\n ProteinInputValue :" + ProteinInputValue
                + "\n FatInputValue :" + FatInputValue
                + "\n NumVeggiesValue :" + NumVeggiesValue
                + "\n BigBreakfastValue :" + BigBreakfastValue
                + "\n WorkoutDayValue :" + WorkoutDayValue
                + "\n NoCarbsValue :" + NoCarbsValue
                + "\n MetricUnitsValue :" + MetricUnitsValue
                + "\n DietSelectorValue :" + DietSelectorValue
        );

        Map<String, String> pm = new HashMap<>();

        pm.put("CalInputValue", CalInputValue + "");
        pm.put("NumMealsValue", NumMealsValue + "");

        pm.put("CarbInputValue", CarbInputValue + "");
        pm.put("ProteinInputValue", ProteinInputValue + "");
        pm.put("FatInputValue", FatInputValue + "");

        pm.put("NumVeggiesValue", NumVeggiesValue + "");

        pm.put("BigBreakfastValue", BigBreakfastValue + "");
        pm.put("WorkoutDayValue", WorkoutDayValue + "");
        pm.put("NoCarbsValue", NoCarbsValue + "");
        pm.put("MetricUnitsValue", MetricUnitsValue + "");

        pm.put("DietSelectorValue", DietSelectorValue + "");

        try {
            return swoleMe.get(pm);
        } catch (FailingHttpStatusCodeException ex) {
            Logger.getLogger(SwoleMeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SwoleMeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SwoleMeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
