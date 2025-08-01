package com.example.advancedcomponents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.advancedcomponents.R;
import com.example.advancedcomponents.bean.Planet;

import java.util.List;

public class PlanetBaseAdapter extends BaseAdapter {
    private Context mContext;
    private List<Planet> mPlanetList;

    public PlanetBaseAdapter(Context mContext, List<Planet> mPlanetList) {
        this.mContext = mContext;
        this.mPlanetList = mPlanetList;
    }

    @Override
    public int getCount() {
        return mPlanetList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlanetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //根据布局文件item_list.xml生成转换视图对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_desc = view.findViewById(R.id.tv_desc);

        //给控件设置数据
        Planet planet = mPlanetList.get(position);
        iv_icon.setImageResource(planet.image);
        tv_name.setText(planet.name);
        tv_desc.setText(planet.desc);

        return view;
    }
}
