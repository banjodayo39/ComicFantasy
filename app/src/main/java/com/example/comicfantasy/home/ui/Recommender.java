package com.example.comicfantasy.home.ui;

import org.pytorch.Module;

public class Recommender {

    Module model;

    public Recommender(String modelPath){

        model = Module.load(modelPath);

    }

}
