package com.example.anidea;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends Activity {

    EditText IPText;
    Button shengwen, jiangwen;
    Button fengxiang, moshi;

    Button lianjiebtn;
    private Context mContext;
    private boolean isConnecting = false;
    private boolean onflag = false;
    private Thread mThreadClient = null;
    private Socket mSocketClient = null;
    static BufferedReader mBufferedReaderServer = null;
    static PrintWriter mPrintWriterServer = null;
    static InputStream mBufferedReaderClient = null;
    static PrintWriter mPrintWriterClient = null;
    private String recvMessageClient = "";
    private String recvMessageServer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //	Log.v(tag, "log---------->onCreate!");
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        mContext = this;
        IPText = (EditText) findViewById(R.id.iped);

        //按键获取
        shengwen = (Button) findViewById(R.id.button1);
        jiangwen = (Button) findViewById(R.id.button2);

        fengxiang = (Button) findViewById(R.id.button3);
        moshi = (Button) findViewById(R.id.button4);

        lianjiebtn = (Button) findViewById(R.id.lianjiebtn);


        shengwen.setOnClickListener(new shengwen());
        jiangwen.setOnClickListener(new jiangwen());

        fengxiang.setOnClickListener(new fengxiang());
        moshi.setOnClickListener(new moshi());

        lianjiebtn.setOnClickListener(new lianjie());


        IPText.setText("192.168.0.108:8080");
        //  lianjie();

    }


    class lianjie implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO 自动生成的方法存根
            lianjie();
        }


    }

    class shengwen implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d("socket", "升温-onClick，: isConnecting" + isConnecting
                    + "| mSocketClient：" + (mSocketClient != null) + "是否还在连接：" + mSocketClient.isConnected());

            sendMessge("A1");
 /*            if (isConnecting && mSocketClient != null) {
                String output = "A1";
                Log.d("socket", "A1");
              try {
                    mPrintWriterClient.print(output);// 发送数据
                    mPrintWriterClient.flush();


                } catch (Exception e) {
                    Toast.makeText(mContext, "发送消息时发送异常" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                    Log.d("socket", "发送消息时发送异常：" + e.getMessage());
                }

            }
            if (mSocketClient == null) {
                Toast.makeText(mContext, "未连接", Toast.LENGTH_SHORT).show();
            }
*/
        }
    }

    private void sendMessge(String msgToSend) {
        if (mSocketClient !=null && mSocketClient.isConnected()){
            try {
                PrintWriter printWriter = new PrintWriter(mSocketClient.getOutputStream(), true);
                printWriter.print(msgToSend);// 发送数据
                printWriter.flush();
            } catch (Exception e) {
                Toast.makeText(mContext, "发送消息时发送异常" , Toast.LENGTH_SHORT).show();

                e.printStackTrace();
                Log.d("socket", "发送消息时发送异常：" + e.getMessage());
            }
        }else {
            Toast.makeText(mContext, "未连接或者连接失效！", Toast.LENGTH_SHORT).show();
        }
    }

    class jiangwen implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            if (isConnecting && mSocketClient != null) {
                String output = "A2";
                try {
                    mPrintWriterClient.print(output);// 发送数据
                    mPrintWriterClient.flush();
                } catch (Exception e) {
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

    class fengxiang implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            if (isConnecting && mSocketClient != null) {
                String output = "A3";
                try {
                    mPrintWriterClient.print(output);// 发送数据
                    mPrintWriterClient.flush();
                } catch (Exception e) {
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

    class moshi implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            if (isConnecting && mSocketClient != null) {
                String output = "A4";
                try {
                    mPrintWriterClient.print(output);// 发送数据
                    mPrintWriterClient.flush();
                } catch (Exception e) {
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
        if (isConnecting) {
            //beerlianjiexianshi.setText("通信状态:已连接");
            isConnecting = false;
            try {
                if (mSocketClient != null) {
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
        } else {
            IPText.setEnabled(false);
            mThreadClient = new Thread(mRunnable);
            mThreadClient.start();
            //	beerlianjiexianshi.setText("通信状态:已连接");
        }
    }


    //线程:监听服务器发来的消息
    private Runnable mRunnable = new Runnable() {
        public void run() {
            //String msgText="192.168.11.254:8080";
            String msgText = IPText.getText().toString();
            if (msgText.length() <= 0) {
                //Toast.makeText(mContext, "IP不能为空！", Toast.LENGTH_SHORT).show();
                recvMessageClient = "IP can't be empty!\n";//消息换行
                Message msg = new Message();
                msg.what = 1;
                mHandler.sendMessage(msg);
                return;
            }
            int start = msgText.indexOf(":");
            if ((start == -1) || (start + 1 >= msgText.length())) {
                recvMessageClient = "IP address is error!\n";//消息换行
                Message msg = new Message();
                msg.what = 1;
                mHandler.sendMessage(msg);
                return;
            }
            String sIP = msgText.substring(0, start);
            String sPort = msgText.substring(start + 1);
            int port = Integer.parseInt(sPort);

            Log.d("gjz", "IP:" + sIP + ":" + port);

            mSocketClient = null;
            try {
                Message msg = new Message();
                msg.what = 1;
                //连接服务器
                mSocketClient = new Socket(sIP, port);    //portnum

                if (mSocketClient != null && mSocketClient.isConnected()) {
                    //Toast.makeText(MainActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
                    Log.d("socket", "连接成功");
                    msg.what = 1000;
                    isConnecting = true;
                } else {
                    //Toast.makeText(MainActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
                    Log.d("socket", "连接失败");
                    msg.what = 1001;
                    isConnecting = false;
                    mHandler.sendMessage(msg);

                    return;
                }

                //取得输入、输出流
                mBufferedReaderClient = mSocketClient.getInputStream();
                mPrintWriterClient = new PrintWriter(mSocketClient.getOutputStream(), true);
                recvMessageClient = "connected to server!\n";//消息换行
                mHandler.sendMessage(msg);
                //break;
            } catch (Exception e) {
                recvMessageClient = "connecting IP is error:" + e.toString() + e.getMessage() + "\n";//消息换行
                Message msg = new Message();
                //msg.what = 1;
                msg.what = 1003;
                mHandler.sendMessage(msg);
                // Toast.makeText(MainActivity.this,"连接失败，系统发送异常。",Toast.LENGTH_SHORT).show();
                Log.d("socket", "连接失败，，系统发送异常" + e.getMessage());

                return;
            }

            byte[] buffer = new byte[1024];
            int count = 0;
            while (isConnecting) {
                try {

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

                } catch (Exception e) {

                }
            }
        }
    };

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1001:
                    Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                    break;
                case 1003:
                    Toast.makeText(MainActivity.this, "连接失败，系统发送异常", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    };

    public void onDestroy() {
        super.onDestroy();
        if (isConnecting) {
            isConnecting = false;
            try {
                if (mSocketClient != null) {
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MainActivity.this.finish();
        }
        return false;

    }
}
