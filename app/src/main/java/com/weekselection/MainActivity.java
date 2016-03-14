package com.weekselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import weekselection.com.library.util.WeekPickLayout;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final WeekPickLayout weekPickLayout= (WeekPickLayout) findViewById(R.id.weekPickLayout);
		// 初始传值 格式 如下 中间用“、”隔开
		weekPickLayout.setVaules("二、四、五、六");
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,weekPickLayout.getValues().toString(),Toast.LENGTH_LONG).show();
			}
		});
	}
}
