/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raju.swoleMe;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author ismailu
 */
@Service
public class SwoleMeService {

    HtmlPage page;
    HtmlPage page1;
    WebClient webClient;

    public SwoleMeService() throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("contractor of SwoleMeService");

        this.webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setPopupBlockerEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setDownloadImages(false);
        webClient.getOptions().setGeolocationEnabled(false);
        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getCookieManager().setCookiesEnabled(true);

        WebRequest request = new WebRequest(new URL("http://swole.me/"));
//        this.page = webClient.getPage(request);
//        this.page = webClient.getPage("http://swole.me/");
        this.page = webClient.getPage(request);
//        int i = webClient.waitForBackgroundJavaScript(1000);
//
//        while (i > 0) {
//            i = webClient.waitForBackgroundJavaScript(1000);
//
//            if (i == 0) {
//                break;
//            }
//            synchronized (this.page) {
//                System.out.println("wait");
//                try {
//                    this.wait(500);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        webClient.getAjaxController().processSynchron(page, request, false);

        HtmlElement generatorOptionsBtn = this.page.querySelector("button.generator_options");
        this.page1 = generatorOptionsBtn.click();
        System.out.println("date2 click:" + dateFormat.format(new Date())); //2016/11/16 12:08:43

        webClient.waitForBackgroundJavaScript(12000);

    }

    public String get(Map<String, String> pm) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        System.out.println("date1:" + dateFormat.format(new Date())); //2016/11/16 12:08:43

        String CalInputValue = pm.get("CalInputValue");
        String NumMealsValue = pm.get("NumMealsValue");

        String CarbInputValue = pm.get("CarbInputValue");
        String ProteinInputValue = pm.get("ProteinInputValue");
        String FatInputValue = pm.get("FatInputValue");

        String NumVeggiesValue = pm.get("NumVeggiesValue");

        Boolean BigBreakfastValue = Boolean.valueOf(pm.get("BigBreakfastValue"));
        Boolean WorkoutDayValue = Boolean.valueOf(pm.get("WorkoutDayValue"));
        Boolean NoCarbsValue = Boolean.valueOf(pm.get("NoCarbsValue"));
        Boolean MetricUnitsValue = Boolean.valueOf(pm.get("MetricUnitsValue"));
        String DietSelectorValue = pm.get("DietSelectorValue");//paleo,vegetarian,vegan,nonspecific,default

        Gson gson = new Gson();

        if (page1 instanceof HtmlPage) {//1st page
            System.out.println("date3 in (page1 instanceof HtmlPage):" + dateFormat.format(new Date())); //2016/11/16 12:08:43

            HtmlElement cal_input = (HtmlElement) page1.getElementById("cal_input");
            cal_input.type(CalInputValue + "");

            HtmlSelect numMealsSelect = (HtmlSelect) page1.getElementById("num_meals");
            HtmlOption numMealsOption = numMealsSelect.getOptionByValue(NumMealsValue + "");
            numMealsSelect.setSelectedAttribute(numMealsOption, true);

            HtmlElement carb_input = (HtmlElement) page1.getElementById("carb_input");
            carb_input.setAttribute("value", "");
            carb_input.type(CarbInputValue + "");

            HtmlElement protein_input = (HtmlElement) page1.getElementById("protein_input");
            protein_input.setAttribute("value", "");
            protein_input.type(ProteinInputValue + "");

            HtmlElement fat_input = (HtmlElement) page1.getElementById("fat_input");
            fat_input.setAttribute("value", "");
            fat_input.type(FatInputValue + "");

            HtmlSelect numVeggiesSelect = (HtmlSelect) page1.getElementById("num_veggies");
            HtmlOption numVeggiesOption = numVeggiesSelect.getOptionByValue(NumVeggiesValue + "");
            numVeggiesSelect.setSelectedAttribute(numVeggiesOption, true);

            HtmlCheckBoxInput bigBreakfastCheckBox = (HtmlCheckBoxInput) page1.getElementById("big_breakfast");
            bigBreakfastCheckBox.setChecked(BigBreakfastValue);

            HtmlCheckBoxInput workoutDayCheckBox = (HtmlCheckBoxInput) page1.getElementById("workout_day");
            workoutDayCheckBox.setChecked(WorkoutDayValue);

            HtmlCheckBoxInput noCarbsCheckBox = (HtmlCheckBoxInput) page1.getElementById("no_carbs");
            noCarbsCheckBox.setChecked(NoCarbsValue);

            HtmlCheckBoxInput metricUnitsCheckBox = (HtmlCheckBoxInput) page1.getElementById("metric_units");
            metricUnitsCheckBox.setChecked(MetricUnitsValue);

            HtmlSelect dietSelectorSelect = (HtmlSelect) page1.getFirstByXPath(".//select[@class='diet_selector']");
            HtmlOption dietSelectorOption = dietSelectorSelect.getOptionByValue(DietSelectorValue + "");
            dietSelectorSelect.setSelectedAttribute(dietSelectorOption, true);

            HtmlElement genButton = page1.querySelector("button.gen_button");
            HtmlPage page2 = genButton.click();
            System.out.println("date4 click:" + dateFormat.format(new Date())); //2016/11/16 12:08:43
            Thread.sleep(3_000);
            webClient.waitForBackgroundJavaScript(12000);
            if (page2 instanceof HtmlPage) {
                System.out.println("date5 (page2 instanceof HtmlPage):" + dateFormat.format(new Date())); //2016/11/16 12:08:43
                System.out.println("page2.asText():" + page2.asText()); //2016/11/16 12:08:43

                List<HtmlDivision> HtmlDivisions = page2.getByXPath(".//div[@class='meal_box'][not(contains(@style, 'display: none'))]");

                List<MealUnit> FinalMealUnits = new ArrayList<>();

                for (HtmlDivision mealBox : HtmlDivisions) {
                    MealUnit mealUnit = new MealUnit();

                    HtmlDivision mealNameDiv = (HtmlDivision) mealBox.getFirstByXPath(".//div[@class='mealname']");
                    String mealName = mealNameDiv.getTextContent();
                    mealUnit.setName(mealName.trim());

                    List<HtmlElement> es = mealBox.getByXPath(".//li[@class='diet_draggable']");
                    List<FoodInfo> foodInfos = new ArrayList<>();

                    MealStats mealStats = new MealStats();
                    if (es.size() > 0) {

                        HtmlDivision mealStatsDiv = (HtmlDivision) mealBox.getFirstByXPath(".//div[@class='mealstats']");

                        String mealStatsString = mealStatsDiv.asText();
                        String[] mealStatsArray = mealStatsString.split("\\n");

                        for (int i = 0; i < mealStatsArray.length; i++) {

                            mealStatsArray[i] = mealStatsArray[i].trim().split(" ")[0];
                        }

                        mealStats.setCarbs(mealStatsArray[0]);
                        mealStats.setFat(mealStatsArray[1]);
                        mealStats.setProtein(mealStatsArray[2]);
                        mealStats.setCalories(mealStatsArray[3]);

                    }

                    for (HtmlElement e : es) {

                        FoodInfo foodInfo = new FoodInfo();

                        HtmlDivision foodNameDiv = e.getFirstByXPath(".//div[@class='print_name']");
                        String foodName = foodNameDiv.getTextContent();
                        foodInfo.setFoodName(foodName.trim());

                        HtmlElement staticAmountDiv = e.getFirstByXPath(".//span[@class='static_amount']");
                        String staticAmount = staticAmountDiv.getTextContent();
                        foodInfo.setFoodAmount(staticAmount.trim());

                        HtmlElement staticUnitsDiv = e.getFirstByXPath(".//span[@class='static_units']");
                        String staticUnits = staticUnitsDiv.getTextContent();
                        foodInfo.setFoodUnits(staticUnits.trim());

                        foodInfos.add(foodInfo);
                    }
                    mealUnit.setFoodsInfo(foodInfos);
                    mealUnit.setMealStats(mealStats);

                    FinalMealUnits.add(mealUnit);

                    foodInfos = null;
                    mealStats = null;
                    mealUnit = null;
                }
                System.out.println("date last:" + dateFormat.format(new Date())); //2016/11/16 12:08:43
                webClient.close();

                return gson.toJson(FinalMealUnits);
            }

        }

        return null;
    }
}
