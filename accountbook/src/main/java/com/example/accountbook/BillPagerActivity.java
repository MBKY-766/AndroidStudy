package com.example.accountbook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.accountbook.adapter.BillPagerAdapter;
import com.example.accountbook.datebase.BillDBHelper;
import com.example.accountbook.entity.BillInfo;
import com.example.accountbook.utils.DateUtil;
import com.example.accountbook.utils.ToastUtil;

import java.util.Calendar;

public class BillPagerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private TextView tv_month;
    private Calendar calendar;
    private ViewPager vp_bill;
    private BillDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_pager);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_option = findViewById(R.id.tv_option);
        tv_title.setText("账单列表");
        tv_option.setText("添加账单");
        tv_month = findViewById(R.id.tv_month);
        //显示当前日期
        calendar = Calendar.getInstance();
        tv_month.setText(DateUtil.getNowMonth(calendar));

        //点击弹出日期对话框
        tv_month.setOnClickListener(this);

        tv_option.setOnClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
        //打开数据库连接
        dbHelper = BillDBHelper.getInstance(this);
        dbHelper.openReadLink();
        dbHelper.openWriteLink();
        //初始化翻页视图
        initViewPager();
    }

    private void initViewPager() {
        //从布局视图中获取名叫pts_bill的翻页标签栏
        PagerTabStrip pts_bill = findViewById(R.id.pts_bill);
        //设置翻页标签栏的文本大小
        pts_bill.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        vp_bill = findViewById(R.id.vp_bill);
        BillPagerAdapter adapter = new BillPagerAdapter(getSupportFragmentManager(),calendar.get(Calendar.YEAR));
        vp_bill.setAdapter(adapter);
        vp_bill.setCurrentItem(calendar.get(Calendar.MONTH));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_month) {
            DatePickerDialog dialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            dialog.show();
        }else if(v.getId()==R.id.tv_option){
            //添加账单-跳转页面
            Intent intent = new Intent(this, BillAddActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else if (v.getId() == R.id.iv_back) {
            //返回页面-结束当前页面
            finish();

        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //设置给文本显示
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tv_month.setText(DateUtil.getNowMonth(calendar));
        //设置翻页视图显示第几页
        vp_bill.setCurrentItem(month);
    }

}
