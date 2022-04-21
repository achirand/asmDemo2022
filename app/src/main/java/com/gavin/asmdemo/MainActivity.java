package com.gavin.asmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG", "MainActivity中的onCreate");
        testPara("para");
        //openSecondActivity();
    }

    public void testPara(String para) {
        Log.i("TAG", "testPara para = " + para);
    }

    @Override
    protected void onDestroy() {
        Log.i("TAG", "onDestroy come in");
        super.onDestroy();
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
