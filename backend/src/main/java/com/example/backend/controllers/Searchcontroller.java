package com.example.backend.controllers;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.entity.Event;
import com.example.backend.entity.Theme;
import com.example.backend.entity.History;
import com.example.backend.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONArray;
import com.example.backend.result.Result;
import com.example.backend.Utils.Utils;
import com.example.backend.constant.CrawlerConstant;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@CrossOrigin
@RestController
public class Searchcontroller {

    @Autowired
    private EventService eventService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private HistoryService historyService;



    @RequestMapping(value = "/search/{target}/{username}/{time}")
    @ResponseBody
    public JSONArray handleSearch(@PathVariable String target,@PathVariable String username,@PathVariable Date time)throws IOException{
        historyService.addToHistory(target,username,time);
        List<Theme>themes=themeService.findAll();
        String thetarget=".*"+target+".*";
        int themeid=0;
        boolean needTosearch=true;
        boolean isMatch=false;
        int size=themes.size();
       // System.out.println(size);
        for(int i=0;i<size;i++){
            isMatch=Pattern.matches(thetarget,themes.get(i).getTheme());
         //   System.out.println(isMatch);
            if(isMatch){
                needTosearch=false;
                themeid=themes.get(i).getThemeid();
                break;
            }
        }

        if(needTosearch){
          String mycookie = CrawlerConstant.TEST_COOKIE;
          int searchLength = CrawlerConstant.SEARCH_LENGTH_TEST;
          List<Result> resultUrl = new ArrayList();
          HashMap<String, String> cookies = Utils.convertCookie(mycookie);
          String Urlbase = CrawlerConstant.SOURCE_BAIDU+target+"&pn=";
         // String Urlbase=CrawlerConstant.SOURCE_BAIDU_TEST;
          for(int j = 0; j < searchLength; ++j) {
            String Url = Urlbase+ Integer.toString(j) + Integer.toString(0);
         //   System.out.println(Url);
            Document document = Jsoup.connect(Url).cookies(cookies).get();
        //    System.out.println(document);
            Elements elements = document.select(".result");
          //  System.out.println(elements.get(0));

            for(int i = 0; i < elements.size(); ++i) {
                String urli = ((Element)elements.get(i)).attr(CrawlerConstant.BAIDU_RESULT_URL_ATTR_NAME);
                Elements title = ((Element)elements.get(i)).select(CrawlerConstant.BAIDU_RESULT_TITLE_CSS_QUERY);
                Elements content = ((Element)elements.get(i)).select(CrawlerConstant.BAIDU_RESULT_CONTENT_CSS_QUERY);
                String thecontent = content.text();
                String elementTitle = title.text();
                if (urli != "") {
                   // System.out.println(((Element)elements.get(i)).attr(CrawlerConstant.BAIDU_RESULT_URL_ATTR_NAME));
                    Elements elementsTime = ((Element)elements.get(i)).select(CrawlerConstant.BAIDU_RESULT_TIME_CLASS_NAME);
                    if (elementsTime.size() == 1) {
                        String dateChinese = ((Element)elementsTime.get(0)).text();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        Date urlDate = null;

                        try {
                            urlDate = simpleDateFormat.parse(dateChinese);
                        } catch (ParseException var29) {
                         //   System.out.println(dateChinese);
                            String regEx = "[^0-9]";
                            Pattern pattern = Pattern.compile(regEx);
                            Matcher matcher = pattern.matcher(dateChinese);
                            try {
                                int num = Integer.parseInt(matcher.replaceAll("").trim());
                          //  System.out.println(num);
                            Calendar calendar = Calendar.getInstance();
                            String hour = ".*小时.*";
                            String day = ".*天.*";
                            String minute = ".*分钟.*";
                            if (Pattern.matches(hour, dateChinese)) {
                                calendar.add(10, -1 * num);
                            }

                            if (Pattern.matches(day, dateChinese)) {
                                calendar.add(5, -1 * num);
                            }

                            if (Pattern.matches(minute, dateChinese)) {
                                calendar.add(12, -1 * num);
                            }

                            urlDate = calendar.getTime();}
                            catch (Exception e1){
                                continue;
                            }
                        }

                        resultUrl.add(new Result(urlDate, urli, elementTitle, thecontent));
                    }
                }
            }
        }

        List<Result> effectiveUrls = Utils.filterUrl(resultUrl);
      //  System.out.println(resultUrl.size());
      //  System.out.println(effectiveUrls.size());
        Collections.sort(effectiveUrls);
        effectiveUrls.forEach((r) -> {
          //  System.out.println(r.time);
          //  System.out.println(r.title);
          //  System.out.println(r.Url);
            String path = "./app.log";

            try {
                FileUtils.writeStringToFile(new File(path), r.content + "\n", StandardCharsets.UTF_8, true);
                FileUtils.writeStringToFile(new File(path), r.Url + "\n", StandardCharsets.UTF_8, true);
            } catch (IOException var3) {
                var3.printStackTrace();
            }

        });

           themeService.createTheme(target);
           Theme theme=themeService.findbyTheme(target);
            eventService.eventStore(effectiveUrls,theme.getThemeid());
            List<Event>events=eventService.findEventsbyThemeid(theme.getThemeid());
            int len=events.size();
            JSONArray array=new JSONArray();
            for(int i=len-1;i>=0;i--){
                JSONArray array1=new JSONArray();
                Event event=events.get(i);
                array1.add(event.getTitle());
                array1.add(event.getContent());
                array1.add(event.getUrl());
                array1.add(event.getTime());
                array1.add(event.getEventid());
                array.add(array1);
            }
           // System.out.println(array);
            return array;
    }
    else{
        List<Event>events=eventService.findEventsbyThemeid(themeid);
        int len=events.size();
        JSONArray array=new JSONArray();
        for(int i=len-1;i>=0;i--){
            JSONArray array1=new JSONArray();
            Event event=events.get(i);
            array1.add(event.getTitle());
            array1.add(event.getContent());
            array1.add(event.getUrl());
            array1.add(event.getTime());
            array1.add(event.getEventid());
            array.add(array1);
        }
           // System.out.println(array);
            return array;
        }
    }

    @RequestMapping(value = "/mindMap/{target}")
    @ResponseBody
    public JSONObject handleSearchMindMap(@PathVariable String target)throws IOException{

        // 查询数据库中是否有该事件（思维导图只能从数据库中读取）
        List<Theme>themes=themeService.findAll();
        String theTarget=".*"+target+".*";
        int themeId=-1;
        for (Theme value : themes) {
            if (Pattern.matches(theTarget, value.getTheme())) {
                // 找到事件
                themeId = value.getThemeid();
                break;
            }
        }

        if (themeId == -1) {
            // 未找到事件，报错
            System.out.println(target);
            System.out.println("Not in the database");
            return new JSONObject();
        }

        // 获取工具类实例
        com.example.backend.Utils.ExtractWords extractWords = new com.example.backend.Utils.ExtractWords();

        // 获取搜索结果
        List<Event> events = eventService.findEventsbyThemeid(themeId);

        JSONObject result = new JSONObject();

        List<String> time = new ArrayList<>();
        List<String> address = new ArrayList<>();
        List<String> organization = new ArrayList<>();
        List<String> person = new ArrayList<>();
        List<String> keyWord = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            if (i >= events.size()) break;
            String eventString = events.get(i).getContent();

            // 设置输入内容
            extractWords.setEventString(eventString);

            JSONObject tmp = extractWords.getExtractInfo();

            time.addAll((List<String>) tmp.get("time"));
            address.addAll((List<String>) tmp.get("address"));
            organization.addAll((List<String>) tmp.get("org"));
            person.addAll((List<String>) tmp.get("person"));
            keyWord.addAll((List<String>) tmp.get("keyWord"));
        }

        com.example.backend.Utils.CountUtil countUtil = new com.example.backend.Utils.CountUtil();

        List<String> addressResult = extractWords.segment(address);
//        System.out.println(addressResult);

        result.put("time", countUtil.getCountResult(time));
        result.put("address", countUtil.getCountResult(addressResult));
        result.put("org", countUtil.getCountResult(organization));
        result.put("person", countUtil.getCountResult(person));
        result.put("keyWord", countUtil.getCountResult(keyWord));

        System.out.println(result);

        return result;
    }

    @RequestMapping(value = "/showHistory/{username}")
    @ResponseBody
    public JSONArray handleShowHistory(@PathVariable String username){
        List<History> histories=historyService.showHistory(username);
        int len=histories.size();
        JSONArray array=new JSONArray();
        for (int i = len-1; i >=0 ; i--) {
            JSONArray array1=new JSONArray();
            History history=histories.get(i);
            array1.add(history.getTheme());
            array1.add(history.getTime().toString());
            array.add(array1);
        }
        return  array;
    }

    @RequestMapping(value = "/deleteHistory/{theme}/{username}/{time}")
    @ResponseBody
    public void handleDeleteHistory(@PathVariable String theme,@PathVariable String username,@PathVariable String time){
       // System.out.println(time);
        historyService.delete(theme,username,time);
    }




}
