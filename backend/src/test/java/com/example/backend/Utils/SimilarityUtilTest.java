package com.example.logindemo.Utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityUtilTest {

    SimilarityUtil similarityUtil;

    @BeforeEach
    void setUp() {
        this.similarityUtil = new SimilarityUtil();
    }

    @AfterEach
    void tearDown() {
        this.similarityUtil = null;
    }

    @Test
    void getModelFilePath() {
        assertSame("D:\\codes\\event_search_engine\\res\\hanlp-wiki-vec-zh.txt", this.similarityUtil.getModelFilePath());
    }

    @Test
    void setModelFilePath() {
    }

    @Test
    void getSimilarity() throws IOException {

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String ans = decimalFormat.format(this.similarityUtil.getSimilarity("这是一个测试","这是一次运行"));

        assertEquals("0.38", ans);
    }
}