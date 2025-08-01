package com.example.advancedcomponents.entity;

import com.example.advancedcomponents.R;

import java.util.ArrayList;

public class GoodsInfo {
    public int id;
    public String name;//名字
    public String description;//描述
    public float price;//价格
    public String picturePath;//图片路径
    public int picture;//图片编号

    //手机商品名称数组
    public static String[] nameArr = {
            "iPhone16 ProMax", "HUAWEI Mate70", "OPPO Find X8 Ultra", "xiaomi 15 Ultra"
    };
    //手机商品描述数组
    public static String[] descArr = {
            "Apple iPhone16 ProMax 1TB 沙漠色",
            "HUAWEI Mate70 512GB 风信紫",
            "OPPO Find X8 Ultra 256GB 月光白",
            "xiaomi 15 Ultra 1TB 樱花粉"
    };
    //手机价格数组
    public static float[] priceArr = {
            12899,
            7999,
            5299,
            6999,
    };
    //手机图片资源数组
    public static int[] pictureArr={
            R.drawable.iphone,
            R.drawable.huawei,
            R.drawable.oppo,
            R.drawable.xiaomi
    };

    //获取默认手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList(){
        ArrayList<GoodsInfo> goodsList = new ArrayList<>();
        for (int i = 0; i < nameArr.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.id = i;
            info.name = nameArr[i];
            info.description = descArr[i];
            info.price = priceArr[i];
            info.picture = pictureArr[i];
            goodsList.add(info);
        }
        return goodsList;
    }




}
