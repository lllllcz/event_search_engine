package com.example.backend.Utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountUtilTest {

    CountUtil countUtil;

    @BeforeEach
    void setUp() {
        this.countUtil = new CountUtil();
    }

    @AfterEach
    void tearDown() {
        this.countUtil = null;
    }

    @Test
    void getCountResult() {

        List<String> input = new ArrayList<String>(){{
            add("aaa");
            add("aaa");
            add("bbbb");
            add("ccccc");
        }};

        List<String> output = new ArrayList<String>(){{
            add("aaa");
        }};

        assertSame(output.get(0), this.countUtil.getCountResult(input).get(0));
    }
}