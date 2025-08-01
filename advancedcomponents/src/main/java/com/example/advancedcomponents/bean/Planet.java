package com.example.advancedcomponents.bean;

import com.example.advancedcomponents.R;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    public int image;//图标
    public String name;//名称
    public String desc;//描述

    public Planet(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    private static int[] iconArr = {R.drawable.huoxing, R.drawable.shuixing};
    private static String[] nameArr = {"火星", "水星"};
    private static String[] descArr = {
            "火星时太阳系八大行星之一，排行第四，属于类地行星，直径约为地球的53%",
            "水星上太阳系八大行星最内侧也是最小的一颗行星，也是离太阳最近的行星"
    };

    public static List<Planet> getDefaultList() {
        List<Planet> planetList = new ArrayList<>();
        for (int i = 0; i < iconArr.length; i++) {
            planetList.add(new Planet(iconArr[i], nameArr[i], descArr[i]));

        }
        return planetList;
    }
}
