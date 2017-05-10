package com.example.logsave;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import android.os.Binder;
import android.os.Environment;
import android.util.Log;

public class LogWrapper {
	
	    private static final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/LogSaveFile/";
	    
	    private static Logger logger;
	    //현재 시간 구하기
	    private static long time = System.currentTimeMillis();
  
    	//시간을 05_10_18_10_10단위로 분류하기
	    private static SimpleDateFormat daytime = new SimpleDateFormat("MM_dd_HH_mm_ss");
    	private static String str = daytime.format(new Date(time));
    	//파일을 저장할 폴더 변수
    	private static File myDir = new File(strSDpath);
    	private static Date fileDate = null;
	    
    	private static Calendar fileCal = Calendar.getInstance();
    	private static File[] list = myDir.listFiles();
	    public static void v(String tag,String msg){
	    	
    		//폴더가 존재하는지 여부 확인
	    	if(!myDir.exists()){
    			myDir.mkdir(); //폴더 생성
    		}
	    	//폴더에 있는 파일들 가져오기
	    	
	    	for(int i=0; i<list.length;i++){
	    		
	    		//파일의 마지막 수정시간 가져온다.
	    		fileDate = new Date(list[i].lastModified());
	    		
	    		fileCal.setTime(fileDate);
	    		
	    		//현재시간에서 파일수정시간 빼기.
	    		long diffMil = time - fileCal.getTimeInMillis();
	    		
	    		// 일 단위 날짜로 계산
	    		int diffDay = (int)(diffMil/(24*60*60*1000));
	 
	    		//30일이 지난 파일들을 삭제한다.
	    		if(diffDay >= 30 && list[i].exists()){
	    		    list[i].delete();
	    		}
	    	}
	    	//파일 생성 (날짜 + 시간 + 로그 메시지 .txt)
    		File savefile = new File(strSDpath+str+"_"+msg+".txt");
    		FileWriter write;
    		//파일에 로그메시지 입력
    		try {
				write = new FileWriter(savefile,false);
				PrintWriter out=new PrintWriter(write);
				out.println(msg);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	if(logger!=null){
	    		logger.log(Level.INFO,String.format("V/%s(%d):%s\n",tag,Binder.getCallingPid(),msg));
	    	}
	    	Log.v(tag,msg);
	    }
	    public static void d(String tag,String msg){
	    	
    		//폴더가 존재하는지 여부 확인
	    	if(!myDir.exists()){
    			myDir.mkdir(); //폴더 생성
    		}
	    	//폴더에 있는 파일들 가져오기
	    	
	    	for(int i=0; i<list.length;i++){
	    		
	    		//파일의 마지막 수정시간 가져온다.
	    		fileDate = new Date(list[i].lastModified());
	    		
	    		fileCal.setTime(fileDate);
	    		
	    		//현재시간에서 파일수정시간 빼기.
	    		long diffMil = time - fileCal.getTimeInMillis();
	    		
	    		// 일 단위 날짜로 계산
	    		int diffDay = (int)(diffMil/(24*60*60*1000));
	 
	    		//30일이 지난 파일들을 삭제한다.
	    		if(diffDay >= 0 && list[i].exists()){
	    		    list[i].delete();
	    		}
	    	}
	    	//파일 생성 (날짜 + 시간 + 로그 메시지 .txt)
    		File savefile = new File(strSDpath+str+"_"+msg+".txt");
    		FileWriter write;
    		//파일에 로그메시지 입력
    		try {
				write = new FileWriter(savefile,false);
				PrintWriter out=new PrintWriter(write);
				out.println(msg);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	if(logger!=null){
	    		logger.log(Level.INFO,String.format("D/%s(%d):%s\n",tag,Binder.getCallingPid(),msg));
	    	}
	    	Log.d(tag,msg);
	    }
	    
	    
}
