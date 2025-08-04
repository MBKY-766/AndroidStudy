package com.example.advancedcomponents.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.advancedcomponents.R;
import com.example.advancedcomponents.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LaunchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LaunchFragment extends Fragment {


    // TODO: Rename and change types and number of parameters
    public static LaunchFragment newInstance(int count,int position, int image_id) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("count", count);
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        Bundle arguments = getArguments();

        int count = arguments.getInt("count", 0);
        int position = arguments.getInt("position", 0);
        int imageId = arguments.getInt("image_id", 0);

        View view = LayoutInflater.from(context).inflate(R.layout.item_launch, null);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
        Button btn_start = view.findViewById(R.id.btn_start);

        iv_launch.setImageResource(imageId);
        //每个页面都分配一组对应的单选按钮
        for (int i1 = 0; i1 < count; i1++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            radioButton.setClickable(false);
            radioButton.setPadding(10, 10, 10, 10);
            rg_indicate.addView(radioButton);
        }
        //当前位置的单选按钮要高亮度显示
        ((RadioButton) rg_indicate.getChildAt(position)).setChecked(true);
        //如果是最后一个引导页，则显示入口按钮，以便用户点击按钮进入主页
        if (position == count - 1) {
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v -> {
                ToastUtil.show(context, "帅炸了！");
            });
        }

        // Inflate the layout for this fragment
        return view;
    }
}