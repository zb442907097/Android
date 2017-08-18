package com.example.administrator.bullseye;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView  tvTarget;
    TextView tvSorce;
    TextView tvIndex;
    SeekBar sbBulsseye;
    Button btnOk;
    Button btnHelp;
    Button btnReplay;
    int randomSorce;
    int finalSorce;
    int index =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        randomOfSorce();
        setListener();
    }

    private  void setListener(){
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                int currentSorce  =sbBulsseye.getProgress();
                int sorce =100-Math.abs(currentSorce-randomSorce);
                finalSorce+=sorce;
                setViewText();
                randomOfSorce();
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                randomOfSorce();
                finalSorce=0;
                index =0;
                setViewText();
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Help")
                        .setMessage("这是帮助")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog =builder.create();
                dialog.show();

            }
        });
    }
    private void setViewText() {
        tvSorce.setText("分数" + finalSorce);
        tvIndex.setText("局数:"+index);
        sbBulsseye.setProgress(0);
    }

    public void randomOfSorce(){
        Random random = new Random();
        randomSorce =random.nextInt(99)+1;
        tvTarget.setText("小样把进度条拖到:"+randomSorce);
    }
    private void findView() {
        tvTarget =(TextView) findViewById(R.id.tv_target);
        tvSorce =(TextView) findViewById(R.id.tv_sorce);
        tvIndex  =(TextView) findViewById(R.id.tv_index);
        sbBulsseye =(SeekBar) findViewById(R.id.sb_bulsseye);
        btnHelp =(Button) findViewById(R.id.btn_help);
        btnOk =(Button) findViewById(R.id.btn_ok);
        btnReplay =(Button) findViewById(R.id.btn_replay);
    }
}
