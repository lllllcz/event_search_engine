package com.example.backend.Utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountUtil {

    public CountUtil() {};

    public List<String> getCountResult(List<String> target) {

        JSONObject counter = new JSONObject();
        List<String> countResult = new ArrayList<>();

        for (String str : target) {
            Integer integer = counter.getInteger(str);
            if (integer == null) {
                // 第一次发现该字符串
                counter.put(str, 1);
            } else {
                if (integer == 1) {
                    // 如果第二次发现该字符串，则将其加入结果中
                    countResult.add(str);
                }
                counter.put(str, integer + 1);
            }
        }

        return countResult;
    }
}
