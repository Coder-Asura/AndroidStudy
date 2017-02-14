package com.asura.promote;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asura.promote.adapter.MailAppAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQQ(MainActivity.this);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMailAppSend();
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMailAppReceive();
            }
        });
        TextView tip0 = (TextView) findViewById(R.id.textView);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(new Rect(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()));
        tip0.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 打开QQ
     *
     * @param context
     */
    public static void openQQ(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity"));
            //打开自带邮箱App
//            intent.setComponent(new ComponentName("com.android.email", "com.android.email.activity.EmailActivity"));
            if (!(context instanceof Activity)) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "未安装QQ", Toast.LENGTH_SHORT).show();
        }
    }

    public void openMailAppSend() {
        Uri uri = Uri.parse("mailto:");
//        Uri uri = Uri.parse("http:");//选择浏览器App
        Intent intent = new Intent(Intent.ACTION_VIEW, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(uri);
        startActivity(Intent.createChooser(intent, "请选择邮箱App"));
    }

    public void openMailAppReceive() {
        PackageManager pm = getPackageManager();
        Uri uri = Uri.parse("mailto:");
        Intent intent = new Intent(Intent.ACTION_VIEW, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(uri);
        // 通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        // 调用系统排序 ， 根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
//        final ArrayList<String> stringArrayList = new ArrayList<>();
        final ArrayList<Intent> intents = new ArrayList<>();
        for (ResolveInfo reInfo : resolveInfos) {
            String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
//            String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
//            Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
            Intent appIntent = pm.getLaunchIntentForPackage(pkgName);
            intents.add(appIntent);
//            stringArrayList.add(appLabel);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(new MailAppAdapter(this, resolveInfos), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = intents.get(which);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
//        builder.setItems(stringArrayList.toArray(new String[stringArrayList.size()])
//                , new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = intents.get(which);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                });
        builder.show();

    }
}
