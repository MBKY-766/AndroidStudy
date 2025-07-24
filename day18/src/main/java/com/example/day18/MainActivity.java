package com.example.day18;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.day18.Util.FileUtil;
import com.example.day18.Util.ToastUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name_et;
    private EditText age_et;
    private EditText height_et;
    private EditText weight_et;
    private Button save_btn;
    private CheckBox married_ck;

    private String path;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_et = findViewById(R.id.name);
        age_et = findViewById(R.id.age);
        height_et = findViewById(R.id.height);
        weight_et = findViewById(R.id.weight);
        married_ck = findViewById(R.id.married);
        result = findViewById(R.id.result);
        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);
        findViewById(R.id.read_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_btn) {
            String name = name_et.getText().toString();
            String age = age_et.getText().toString();
            String height = height_et.getText().toString();
            String weight = weight_et.getText().toString();
            Boolean married = married_ck.isChecked();
            StringBuilder sb = new StringBuilder();
            sb.append("姓名：").append(name);
            sb.append("\n 年龄：").append(age);
            sb.append("\n 身高：").append(height);
            sb.append("\n 体重：").append(weight);
            sb.append("\n 婚否：").append(married ? "是" : "否");

            String fileName = System.currentTimeMillis()+".txt";
            String directory = null;
            //外部存储的私有空间
//            directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
            //外部存储的公共空间
//            directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            //内部存储私有空间
            directory = getFilesDir().toString();
            path = directory + File.separatorChar+fileName;
            Log.d("这里",path);
            FileUtil.saveText(path,sb.toString());
            ToastUtil.show(this, "保存成功");
        }else if(v.getId()==R.id.read_btn){
            String msg = FileUtil.openText(path);
            result.setText(msg);
        }
    }
}