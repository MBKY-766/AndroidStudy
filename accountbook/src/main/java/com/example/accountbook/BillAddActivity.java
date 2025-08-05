package com.example.accountbook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.accountbook.datebase.BillDBHelper;
import com.example.accountbook.entity.BillInfo;
import com.example.accountbook.utils.DateUtil;
import com.example.accountbook.utils.ToastUtil;

import java.util.Calendar;

public class BillAddActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private TextView tv_date;
    private RadioGroup rg_type;
    private EditText et_money;
    private EditText et_remark;
    private BillDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_option = findViewById(R.id.tv_option);
        tv_title.setText("请填写账单");
        tv_option.setText("账单列表");

        tv_date = findViewById(R.id.tv_date);
        rg_type = findViewById(R.id.rg_type);
        et_money = findViewById(R.id.et_money);
        et_remark = findViewById(R.id.et_remark);

        findViewById(R.id.btn_save).setOnClickListener(this);
        //显示当前日期
        calendar = Calendar.getInstance();
        tv_date.setText(DateUtil.getNowDate(calendar));

        //点击弹出日期对话框
        tv_date.setOnClickListener(this);

        tv_option.setOnClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);

        dbHelper = BillDBHelper.getInstance(this);
        dbHelper.openReadLink();
        dbHelper.openWriteLink();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_date) {
            DatePickerDialog dialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            dialog.show();
        } else if (v.getId() == R.id.btn_save) {
            //保存订单信息
            BillInfo bill = new BillInfo();
            bill.date = tv_date.getText().toString();
            bill.type = rg_type.getCheckedRadioButtonId() == R.id.rb_income ? BillInfo.BILL_TYPE_INCOME : BillInfo.BILL_TYPE_EXPENDITURE;
            bill.remark = et_remark.getText().toString();
            bill.amount = Double.parseDouble(et_money.getText().toString());
            if(dbHelper.save(bill)>0){
                ToastUtil.show(this,"保存成功");
            }

        }else if(v.getId()==R.id.tv_option){
            //账单列表-跳转页面
            Intent intent = new Intent(this, BillPagerActivity.class);
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
        tv_date.setText(DateUtil.getNowDate(calendar));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.closeLink();
    }
}