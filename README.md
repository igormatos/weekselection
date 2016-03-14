# weekselection
![](https://github.com/liuhaizhu/weekselection/blob/master/art/1.gif)
How to use
=====
Gradle Dependency
```gradle
 dependencies {
    ...
    compile 'weekselection.com.library:library:1.0.0'
}
```

Usage
=====
Declare a weekselection inside your layout XML file:
```xml
    <weekselection.com.library.util.WeekPickLayout
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/weekPickLayout"
        app:wpl_AnimDuration="500"
        app:wpl_TextColor="@android:color/black"
        app:wpl_TextSize="14sp"
        app:wpl_CheckedBgColor="@android:color/black"
        android:layout_width="match_parent"
        android:layout_height="60dp"/>
```
Set default value
```java
final WeekPickLayout weekPickLayout= (WeekPickLayout) findViewById(R.id.weekPickLayout);
		// 初始传值 格式 如下 中间用“、”隔开
		//set default value(String) like this""二、四、五、六""
		weekPickLayout.setVaules("二、四、五、六");
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,weekPickLayout.getValues().toString(),Toast.LENGTH_LONG).show();
			}
		});
		```
