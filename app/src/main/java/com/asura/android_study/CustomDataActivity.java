package com.asura.android_study;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * 自定义处理某种特殊类型文件
 *
 * @author Created by Asura on 2018/6/4 9:46.
 */
public class CustomDataActivity extends AppCompatActivity {
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_data);
        tvInfo = findViewById(R.id.tv_info);
        Intent intent = getIntent();
        if (intent == null) {
            Log.d("Asura", "没收到11");
            tvInfo.setText("没收到11");
        } else {
            Log.d("Asura", "收到了14" + intent.toString());
            tvInfo.setText("收到14：" + intent.toString());
            if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_VIEW)) {
                Uri uri = intent.getData();
                Log.d("Asura", "收到了12" + uri.toString());
                tvInfo.setText(tvInfo.getText()+"\n\n"+"收到12：" + uri.toString());
//               String name= getRealPathFromUri(MainActivity.this,uri);
                String name = getFilePathFromContentUri(CustomDataActivity.this, uri);
                Log.d("Asura", "收到了13" + name);
                tvInfo.setText(tvInfo.getText()+"\n\n"+"收到13：" + name);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            Log.d("Asura", "没收到21");
            tvInfo.setText("没收到21");
        } else {
            Log.d("Asura", "收到了24" + intent.toString());
            tvInfo.setText("收到24：" + intent.toString());
            if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_VIEW)) {
                Uri uri = intent.getData();
                Log.d("Asura", "收到了22" + uri.toString());
                tvInfo.setText(tvInfo.getText()+"\n\n"+"收到22：" + uri.toString());
//               String name= getRealPathFromUri(MainActivity.this,uri);
                String name = getFilePathFromContentUri(CustomDataActivity.this, uri);
                Log.d("Asura", "收到了23" + name);
                tvInfo.setText(tvInfo.getText()+"\n\n"+"收到23：" + name);
            }
        }
    }

    public static String getFilePathFromContentUri(Context context, Uri selectedVideoUri) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = context.getContentResolver().query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
}
