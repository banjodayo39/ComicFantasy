package com.example.comicfantasy.home.ui;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;

import java.util.ArrayList;
import java.util.HashMap;

public class Recommender {

    Module model;
    private HashMap<Float, Float> hashMap;
    private ArrayList<Float> ratings;
    private HashMap<Double, String> movieMap;

    public Recommender(String modelPath) {
        model = Module.load(modelPath);
    }

    public int argMax(float[] inputs) {

        int maxIndex = -1;
        float maxvalue = 0.0f;

        for (int i = 0; i < inputs.length; i++) {

            if (inputs[i] > maxvalue) {

                maxIndex = i;
                maxvalue = inputs[i];
            }
        }
        float [] six = new float[4];
        long[] l = new long[1];
        predict(six,l);
        return maxIndex;
    }

    public String predict(float[] data, long[] size) {
        Tensor tensor = Tensor.fromBlob(data, size);
        IValue inputs = IValue.from(tensor);
        Tensor outputs = model.forward(inputs).toTensor();
        float[] scores = outputs.getDataAsFloatArray();
        for (int i = 0; i < hashMap.size(); i++) {
            Float rating = ratings.get(i);
            hashMap.put(scores[i], rating);
        }
        int classIndex = argMax(scores);
        Float maxRating = hashMap.get(classIndex);

        return movieMap.get(maxRating.doubleValue());
    }
}
