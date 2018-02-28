package com.skyia.tablelayouttest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class AddTabsActivity extends AppCompatActivity {
    private Switch mSwitchTop,mSwitchShishang;
    private SharedPreferences sp;
    private boolean shishangCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tabs);
        sp = getSharedPreferences("type",MODE_PRIVATE);
        mSwitchTop = (Switch) findViewById(R.id.top_switch);
        mSwitchTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(AddTabsActivity.this,"checked",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddTabsActivity.this,"unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSwitchShishang = (Switch) findViewById(R.id.shishang_switch);
        shishangCheck = sp.getBoolean("shishang",true);
        if (shishangCheck){
            mSwitchShishang.setChecked(true);
        }else {
            mSwitchShishang.setChecked(false);
        }
        mSwitchShishang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                if (isChecked){
                    editor.putBoolean("shishang",true);
                    editor.commit();
                }else {
                    editor.putBoolean("shishang",false);
                    editor.commit();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
