package com.example.permission;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds;
import com.example.permission.enity.Contact;

public class ContactAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_add){
            //创建一个联系人对象
            Contact contact = new Contact();
            contact.name = et_name.getText().toString().trim();
            contact.phone = et_phone.getText().toString().trim();
            contact.email = et_email.getText().toString().trim();
            //方式一：使用ContentResolver多次写入，每次一个字段
            addContacts(getContentResolver(),contact);
        }
    }

    private void addContacts(ContentResolver contentResolver, Contact contact) {
        ContentValues values = new ContentValues();
        //往raw_contacts添加联系人记录，并获取添加后的联系人编号
        Uri uri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId= ContentUris.parseId(uri);
        ContentValues name = new ContentValues();
        //关联联系人编号
        name.put(Contacts.Data.RAW_CONTACT_ID,rawContactId);
        //"姓名"的数据类型
        name.put(Contacts.Data.MIMETYPE,CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        //联系人姓名
        name.put(Contacts.Data.DATA2,contact.name);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,name);

        ContentValues phone = new ContentValues();
        //关联联系人编号
        phone.put(Contacts.Data.RAW_CONTACT_ID,rawContactId);
        //"电话号码"的数据类型
        phone.put(Contacts.Data.MIMETYPE,CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        //联系人电话号码
        phone.put(Contacts.Data.DATA1,contact.phone);
        //联系类型：1表示家庭，2表示工作
        phone.put(Contacts.Data.DATA2, CommonDataKinds.Phone.TYPE_MOBILE);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,phone);

        ContentValues email = new ContentValues();
        //关联联系人编号
        email.put(Contacts.Data.RAW_CONTACT_ID,rawContactId);
        //"姓名"的数据类型
        email.put(Contacts.Data.MIMETYPE,CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        //联系人姓名
        email.put(Contacts.Data.DATA1,contact.email);
        //联系类型：1表示家庭，2表示工作
        email.put(Contacts.Data.DATA2, CommonDataKinds.Email.TYPE_WORK);
        contentResolver.insert(ContactsContract.Data.CONTENT_URI,email);
    }
}