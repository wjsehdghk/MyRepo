package com.example.logsave;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG="MAINACTIVITY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
	
		TextView text = (TextView)findViewById(R.id.text);
		
		///LogWrapper.v(TAG,"jeon3");
		
		///LogWrapper.d(TAG, "jeon4");

		
		/**
		 * 저장된 파일 읽기
		 */
		
			try{
				StringBuffer data = new StringBuffer();
				String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/LogSaveFile/";
				File f= new File(path,"01_01_22_29_20_jeon3.txt");
				
				BufferedReader buffer = new BufferedReader(new FileReader(f));
				
				String str = buffer.readLine();
				while(str!=null){
					data.append(str+"\n");
					str = buffer.readLine();
					
				}
				text.setText(data);
				buffer.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		
			/**
			 * 외부 저장소에 파일 저장.
			 **/
			/*String data = "test external";
			try{
				File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				File f = new File(path,"external.txt");
				FileWriter write = new FileWriter(f,false);
				PrintWriter out=new PrintWriter(write);
				out.println(data);
				out.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}	*/
			
			
			/**
			 * 
			 * 내부 저장소에 파일 저장
			 **/
			
		/*
		try { 
            FileOutputStream fos = openFileOutput
                    ("myggumtest.txt", // 파일명 지정
                            Context.MODE_APPEND);// 저장모드
            PrintWriter out = new PrintWriter(fos);
            out.println("log data");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
	}
	boolean checkExternalStorage(){
		
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)){
			Log.d("test", "외부메모리 읽기 쓰기 모두 가능");
			return true;
		}
		else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			Log.d("test","외부메모리 읽기만 가능");
			return false;
		
		}
		else {
			
			Log.d("test", "외부 메모리 읽기쓰기 모두 안됨");
			return false;
		}
	}
	
	
	
	
	
}
