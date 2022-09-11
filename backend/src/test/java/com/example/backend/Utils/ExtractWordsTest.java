package com.example.backend.Utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtractWordsTest {

    ExtractWords extractWords;
    ExtractWords extractWords2;

    @BeforeEach
    void setUp() {
        extractWords = new ExtractWords();
        extractWords2 = new ExtractWords("这是一个测试");
    }

    @AfterEach
    void tearDown() {
        extractWords = null;
        extractWords2 = null;
    }

    @Test
    void setEventString() {
    }

    @Test
    void getEventString() {

        assertSame("这是一个测试", extractWords2.getEventString());

    }

    @Test
    void getExtractInfo() {
    }
}