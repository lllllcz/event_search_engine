package com.example.backend.Utils;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

public class SimilarityUtil {

    private String modelFilePath;

//    public SimilarityUtil() {
//        this.modelFilePath = "/java/hanlp-wiki-vec-zh.txt";
//    }

    public SimilarityUtil() {
        this.modelFilePath = "/java/hanlp-wiki-vec-zh.txt";
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public float getSimilarity(String text1, String text2) throws IOException {

        DocVectorModel docVectorModel = new DocVectorModel(new WordVectorModel(modelFilePath));

        return docVectorModel.similarity(text1,text2);
    }
}
