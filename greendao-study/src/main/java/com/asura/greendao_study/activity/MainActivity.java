package com.asura.greendao_study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.asura.greendao_study.dao.UserDao;
import com.asura.greendao_study.R;
import com.asura.greendao_study.adapter.UserAdapter;
import com.asura.greendao_study.model.User;
import com.asura.greendao_study.utils.GreenDaoUtils;
import com.asura.greendao_study.widgt.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Liuxd on 2016/9/7 18:02.
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_insert)
    Button mBtnInsert;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_update)
    Button mBtnUpdate;
    @BindView(R.id.edit_name)
    EditText mEditName;
    @BindView(R.id.edit_id)
    EditText mEditId;
    @BindView(R.id.edit_age)
    EditText mEditAge;
    @BindView(R.id.radio_boy)
    RadioButton mRadioBoy;
    @BindView(R.id.radio_girl)
    RadioButton mRadioGirl;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private UserDao dao;
    private UserAdapter mUserAdapter;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dao = GreenDaoUtils.getSingleTon().getmDaoSession().getUserDao();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_boy:
                        Toast.makeText(MainActivity.this, "男", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_girl:
                        Toast.makeText(MainActivity.this, "女", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        users = dao.loadAll();
        mUserAdapter = new UserAdapter(this, users);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(this, 20f));
        mRecyclerView.setAdapter(mUserAdapter);
    }

    /**
     * 刷新数据
     */
    private void notifyData() {
        users.clear();
        users = dao.loadAll();
        mUserAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_insert, R.id.btn_search, R.id.btn_delete, R.id.btn_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                User insertData = new User(null, mEditName.getText().toString(), Integer.valueOf(mEditAge.getText().toString()), mRadioBoy.isChecked(), 1);
                dao.insert(insertData);
                break;
            case R.id.btn_search:
                List<User> users = dao.loadAll();
                for (int i = 0; i < users.size(); i++) {
                    Log.i("lxd", "结果：" + users.get(i).toString());
                }
                break;
            case R.id.btn_delete:
                String s = mEditId.getText().toString();
                long l = Long.parseLong(s);
                dao.deleteByKey(l);
                break;
            case R.id.btn_update:
                List<User> userss = dao.loadAll();
                User user = new User(userss.get(0).getId(), mEditName.getText().toString(), 22, true, 2);
                dao.update(user);
                break;
        }
        notifyData();
    }
}
