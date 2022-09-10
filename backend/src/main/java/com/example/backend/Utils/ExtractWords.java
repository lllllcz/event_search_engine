package com.example.backend.Utils;

import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.List;

public class ExtractWords {

    private String eventString;

    public ExtractWords() {}

    public ExtractWords(String eventString) {
        this.eventString = eventString;
    }

    public void setEventString(String eventString) {
        this.eventString = eventString;
    }

    public String getEventString() {
        return eventString;
    }

    public JSONObject getExtractInfo() {
//        System.out.println(eventString);
        if (eventString == null) {
            System.out.println("empty string");
            return new JSONObject();
        }

        List<Term> res = NLPTokenizer.segment(eventString);
//        System.out.println(res);

        List<String> time = new ArrayList<>();
        List<String> address = new ArrayList<>();
        List<String> organization = new ArrayList<>();
        List<String> person = new ArrayList<>();

        for (Term term: res) {
            String str = term.toString();
            int length = str.length();
            if (str.contains("/t")) {
                time.add(term.word);
            }

            if (str.contains("/ns")) {
                address.add(term.word);
            }

            if (str.contains("/nt")) {
                organization.add(term.word);
            }

            if (str.contains("/nr")) {
                person.add(term.word);
            }
        }

        JSONObject obj = new JSONObject();
        obj.put("time", time);
        obj.put("address", address);
        obj.put("org", organization);
        obj.put("person", person);
        obj.put("keyWord", HanLP.extractKeyword(eventString, 1));

        return obj;

    }

    public List<String> segment(List<String> words) {

        List<String> result = new ArrayList<>();

        for (String word : words) {
            List<Term> termList = StandardTokenizer.segment(word);
            for (Term term : termList) {
                if (term.word.length() != 1) {
                    result.add(term.word);
                }
            }
        }

        return result;
    }

}
