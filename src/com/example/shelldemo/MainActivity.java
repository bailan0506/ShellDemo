package com.example.shelldemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*Button bt1 = (Button) findViewById(R.id.button1);
		Button bt2 = (Button) findViewById(R.id.button2);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);*/
        String result = execCommand("pm","install","-f","/mnt/sdcard/myDB/LbsTree.apk");  
        Toast.makeText(MainActivity.this, "安装结果:"+result, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("SdCardPath")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			  //安装apk，filePath为apk文件路径，如/mnt/sdcard/ApiDemos.apk  
            String result = execCommand("pm","install","-f","/mnt/sdcard/myDB/LbsTree.apk");  
            Toast.makeText(MainActivity.this, "安装结果:"+result, Toast.LENGTH_LONG).show();  
			break;
		case R.id.button2:
			//卸载apk，packageName为包名，如com.example.android.apis  
            String result1 = execCommand("pm","uninstall", "com.yunduo.nighttools");  
            Toast.makeText(MainActivity.this, "卸载结果:"+result1, Toast.LENGTH_LONG).show(); 
			break;

		default:
			break;
		}
	}
	/*  
	* m命令可以通过adb在shell中执行，同样，我们可以通过代码来执行  
	*/    
	public static String execCommand(String ...command)  {    
	    Process process=null;    
	    InputStream errIs=null;    
	    InputStream inIs=null;    
	    String result="";    
	  
	    try {    
	        process=new ProcessBuilder().command(command).start();    
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	        int read = -1;    
	        errIs=process.getErrorStream();             
	        while((read=errIs.read())!=-1){    
	            baos.write(read);    
	        }    
	        inIs=process.getInputStream();    
	        while((read=inIs.read())!=-1){    
	            baos.write(read);    
	        }    
	        result=new String(baos.toByteArray());    
	        if(inIs!=null)    
	            inIs.close();    
	        if(errIs!=null)    
	            errIs.close();    
	        process.destroy();    
	    } catch (IOException e) {    
	        result = e.getMessage();    
	    }    
	    return result;    
	}  
}
