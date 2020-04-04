package com.example.wifikongzhi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	EditText IPText;
	Button shengwen,jiangwen;
	Button fengxiang,moshi;

	Button lianjiebtn;
	private Context mContext;
	private boolean isConnecting = false;
	private boolean onflag = false;
	private Thread mThreadClient = null;
	private Socket mSocketClient = null;
	static BufferedReader mBufferedReaderServer	= null;
	static PrintWriter mPrintWriterServer = null;
	static InputStream mBufferedReaderClient	= null;
	static PrintWriter mPrintWriterClient = null;
	private  String recvMessageClient = "";
	private  String recvMessageServer = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//	Log.v(tag, "log---------->onCreate!");
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);
		mContext = this;
		IPText=(EditText)findViewById(R.id.iped);

		//按键获取
		shengwen=(Button)findViewById(R.id.button1);
		jiangwen=(Button)findViewById(R.id.button2);

		fengxiang=(Button)findViewById(R.id.button3);
		moshi=(Button)findViewById(R.id.button4);

		lianjiebtn=(Button)findViewById(R.id.lianjiebtn);



		shengwen.setOnClickListener(new shengwen());
		jiangwen.setOnClickListener(new jiangwen());

		fengxiang.setOnClickListener(new fengxiang());
		moshi.setOnClickListener(new moshi());

		lianjiebtn.setOnClickListener(new lianjie());


		IPText.setText("192.168.4.1:8080");
		//  lianjie();

	}




	class lianjie implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			lianjie();
		}


	}

	class shengwen implements OnClickListener
	{



		@Override
		public void onClick(View v) {
			if (isConnecting && mSocketClient != null) {
				String output = "A1";
				try {
					mPrintWriterClient.print(output);// 发送数据
					mPrintWriterClient.flush();
				}

				catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(mContext, "还未连接" + e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
			if (mSocketClient == null) {
				Toast.makeText(mContext, "未连接", Toast.LENGTH_SHORT).show();
			}

		}


	}

	class jiangwen implements OnClickListener
	{



		@Override
		public void onClick(View v) {
			if (isConnecting && mSocketClient != null) {
				String output = "A2";
				try {
					mPrintWriterClient.print(output);// 发送数据
					mPrintWriterClient.flush();
				}

				catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(mContext, "还未连接" + e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
			if (mSocketClient == null) {
				Toast.makeText(mContext, "未连接", Toast.LENGTH_SHORT).show();
			}

		}


	}

	class fengxiang implements OnClickListener
	{



		@Override
		public void onClick(View v) {
			if (isConnecting && mSocketClient != null) {
				String output = "A3";
				try {
					mPrintWriterClient.print(output);// 发送数据
					mPrintWriterClient.flush();
				}

				catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(mContext, "还未连接" + e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
			if (mSocketClient == null) {
				Toast.makeText(mContext, "未连接", Toast.LENGTH_SHORT).show();
			}

		}


	}

	class moshi implements OnClickListener
	{



		@Override
		public void onClick(View v) {
			if (isConnecting && mSocketClient != null) {
				String output = "A4";
				try {
					mPrintWriterClient.print(output);// 发送数据
					mPrintWriterClient.flush();
				}

				catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(mContext, "还未连接" + e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
			if (mSocketClient == null) {
				Toast.makeText(mContext, "未连接", Toast.LENGTH_SHORT).show();
			}

		}


	}

	private void lianjie() {
		if (isConnecting)
		{
			//beerlianjiexianshi.setText("通信状态:已连接");
			isConnecting = false;
			try {
				if(mSocketClient!=null)
				{
					mSocketClient.close();
					mSocketClient = null;

					mPrintWriterClient.close();
					mPrintWriterClient = null;

				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			mThreadClient.interrupt();
			IPText.setEnabled(true);
		}
		else
		{
			isConnecting = true;
			IPText.setEnabled(false);
			mThreadClient = new Thread(mRunnable);
			mThreadClient.start();
			//	beerlianjiexianshi.setText("通信状态:已连接");
		}
	}




	//线程:监听服务器发来的消息
	private Runnable	mRunnable	= new Runnable()
	{
		public void run()
		{
			//String msgText="192.168.11.254:8080";
			String msgText =IPText.getText().toString();
			if(msgText.length()<=0)
			{
				//Toast.makeText(mContext, "IP不能为空！", Toast.LENGTH_SHORT).show();
				recvMessageClient = "IP can't be empty!\n";//消息换行
				Message msg = new Message();
				msg.what = 1;
				mHandler.sendMessage(msg);
				return;
			}
			int start = msgText.indexOf(":");
			if( (start == -1) ||(start+1 >= msgText.length()) )
			{
				recvMessageClient = "IP address is error!\n";//消息换行
				Message msg = new Message();
				msg.what = 1;
				mHandler.sendMessage(msg);
				return;
			}
			String sIP = msgText.substring(0, start);
			String sPort = msgText.substring(start+1);
			int port = Integer.parseInt(sPort);

			Log.d("gjz", "IP:"+ sIP + ":" + port);

			try
			{
				//连接服务器
				mSocketClient = new Socket(sIP, port);	//portnum
				//取得输入、输出流
				mBufferedReaderClient = mSocketClient.getInputStream();

				mPrintWriterClient = new PrintWriter(mSocketClient.getOutputStream(), true);

				recvMessageClient = "connected to server!\n";//消息换行
				Message msg = new Message();
				msg.what = 1;
				mHandler.sendMessage(msg);
				//break;
			}
			catch (Exception e)
			{
				recvMessageClient = "connecting IP is error:" + e.toString() + e.getMessage() + "\n";//消息换行
				Message msg = new Message();
				msg.what = 1;
				mHandler.sendMessage(msg);
				return;
			}

			byte[] buffer = new byte[1024];
			int count = 0;
			while (isConnecting)
			{
				try
				{

					mBufferedReaderClient.read(buffer);
					Message message = new Message(); // 通知界面
					message.what = 2;
					message.obj = buffer;
					mHandler.sendMessage(message);

//
//					if(mBufferedReaderClient.read()==1)
//					{
//						Message msg = new Message(); // 通知界面
//						msg.what = 3;
//						msg.obj = buffer;
//						mHandler.sendMessage(msg);
//						
//					}				

				}
				catch (Exception e)
				{

				}
			}
		}
	};

	Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			switch (msg.what)
			{



			}
		}





	};

	public void onDestroy() {
		super.onDestroy();
		if (isConnecting)
		{
			isConnecting = false;
			try {
				if(mSocketClient!=null)
				{
					mSocketClient.close();
					mSocketClient = null;

					mPrintWriterClient.close();
					mPrintWriterClient = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mThreadClient.interrupt();
		}

	}




	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK )
		{
			Main.this.finish();


		}
		return false;

	}

}
