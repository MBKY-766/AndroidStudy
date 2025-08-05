package com.example.accountbook.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.accountbook.R;
import com.example.accountbook.adapter.BillListAdapter;
import com.example.accountbook.datebase.BillDBHelper;
import com.example.accountbook.entity.BillInfo;

import java.util.List;


public class BillFragment extends Fragment {


    public static BillFragment newInstance(String yearMonth) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putString("yearMonth", yearMonth);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //拿到布局视图
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        //从布局视图中获取名叫lv_bill的列表视图
        ListView lv_bill = view.findViewById(R.id.lv_bill);
        BillDBHelper dbHelper = BillDBHelper.getInstance(getContext());
        String yearMonth = getArguments().getString("yearMonth");
        List<BillInfo> billInfoList = dbHelper.queryByMonth(yearMonth);
        //创建适配器
        BillListAdapter adapter = new BillListAdapter(getContext(), billInfoList);
        //设置适配器
        lv_bill.setAdapter(adapter);
        return view;

    }
}