package com.example.shopping01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping01.Util.FileUtil;
import com.example.shopping01.Util.ToastUtil;

import java.io.File;

public class ImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private String path;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);
        image = findViewById(R.id.image);
        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.read_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_btn) {
            //从指定路径获取位图对象
            String fileName = System.currentTimeMillis() + ".jpeg";
            //获取当前App的私有下载目录
            path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + fileName;
            //从指定资源文件中获取位图对象
            Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.p);
            //把位图对象保存为图片文件
            FileUtil.saveImage(path, b1);
            ToastUtil.show(this, "保存成功");
        } else if (v.getId() == R.id.read_btn) {
            //方式1
//            Bitmap b2 = FileUtil.openImage(path);
//            image.setImageBitmap(b2);
            //方式2
            Bitmap b2 = BitmapFactory.decodeFile(path);
            image.setImageBitmap(b2);
            //方式3
//            image.setImageURI(Uri.parse(path));

        }
    }
}