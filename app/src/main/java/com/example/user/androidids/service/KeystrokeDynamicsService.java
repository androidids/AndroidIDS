package com.example.user.androidids.service;

/**
 * Created by Apurv Pandey on 05/08/16.
 */

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;





import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.user.androidids.R;

public class KeystrokeDynamicsService extends Activity implements OnTouchListener, OnClickListener, OnFocusChangeListener{

    private String password = "HELLOWORLD";
    private EditText mEt; // Edit Text boxes
    private Button mBSpace, mBdone, mBack, mBChange, mNum;
    private RelativeLayout mLayout, mKLayout;
    private boolean isEdit = false;
    private String mUpper = "upper", mLower = "lower";
    private int w, mWindowWidth;
    byte[] buffer;
    long lastpress;
    long lastrelease;
    int trainpress;
    long press;
    long release;
    long keyhold;
    FileOutputStream fos;
    InputStream fis;
    BufferedReader br;
    boolean start=false;
    String filename="myfile";

    String lastkey;
    String storage="";
    Vector<String> text=new Vector<String>();
    Vector<Long> p=new Vector<Long>();
    Vector<Long> r=new Vector<Long>();
    Vector<Long> p2p1=new Vector<Long>();
    Vector<Long> p2r1=new Vector<Long>();
    Vector<Long> r2r1=new Vector<Long>();
    Vector<Long> kh= new Vector<Long>();
    int noofbs=0;
    String op;

    /////////////////////////////////////////////////////
    int NUMBER_OF_INPUTS;
    int NUMBER_OF_USER_CASES;
    int NUMBER_OF_LETTERS;

    double[] MEAN;
    double[] STANDARD_DEVIATION;
    double[][] USER_CASES;
    double[][] TEST_CASE;
    double[] NEW_USE_CASE;

    final int X=3;
    final int NO_OF_HITS=5;
    final int DEVIATION_FACTOR=2;
    double[] hits;
    double[] deviation;
    //////////////////////////////////////////////


    boolean isTrainPressed=false;
    private String sL[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z", "OK", "à", "é", "è", "û", "î" };
    private String cL[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",t

            "X", "Y", "Z", "OK", "à", "é", "è", "û", "î" };
    private String nS[] = { "!", ")", "'", "#", "3", "$", "%", "&", "8", "*",
            "?", "/", "+", "-", "9", "0", "1", "4", "@", "5", "7", "(", "2",
            "\"", "6", "_", "=", "]", "[", "<", ">", "|" };
    private Button mB[] = new Button[27];
    Button mtrain,mtest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fos=openFileOutput(filename,Context.MODE_PRIVATE);
            mtrain=(Button)findViewById(R.id.train);
            setContentView(R.layout.main);
            // adjusting key regarding window sizes
            setKeys();
            setFrow();
            setSrow();
            setTrow();
            setForow();
            mEt = (EditText) findViewById(R.id.xEt);
            mEt.setOnTouchListener(this);
            mEt.setOnFocusChangeListener(this);

                    //mtrain.setOnTouchListener(this);

                    //mEt1.setOnTouchListener(this);
                    //mEt1.setOnFocusChangeListener(this);
                            mEt.setOnClickListener(this);
            //mtrain.setOnTouchListener(this);
            //mEt1.setOnClickListener(this);
            mLayout = (RelativeLayout) findViewById(R.id.xK1);
            mKLayout = (RelativeLayout) findViewById(R.id.xKeyBoard);

        } catch (Exception e) {
            Log.w(getClass().getName(), e.toString());
        }

        //a
        mB[0].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    //System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    ////disp(buttonText+keyhold);

                }
                //done();
                return true;
            }

        });

        //b
        mB[1].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    //System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //////disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //c
        mB[2].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //d
        mB[3].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //e
        mB[4].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //f
        mB[5].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //g
        mB[6].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //h
        mB[7].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        //i

        mB[8].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        //j

        mB[9].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        //k

        mB[10].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        //l
        mB[11].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        //m

        mB[12].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //n

        mB[13].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });


        //o

        mB[14].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        //p
        mB[15].setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String buttonText = (String)v.getTag();
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    press = System.currentTimeMillis();
                    text.addElement(buttonText);
                    p.addElement(press);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    release = System.currentTimeMillis();
                    keyhold=release-press;
                    System.out.println(buttonText+keyhold);
                    r.addElement(release);
                    addText(v);
                    //disp(buttonText+keyhold);

                }

                return true;
            }

        });

        


}
