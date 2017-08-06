package com.example.mac.myapplicationtest2;

import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private String answer;
    private EditText inputEdit;
    private TextView logText;
    private boolean isWinner;
    private int counter;
    private String[] items ={"2","3","4","5","6","7"};
    private int dig = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEdit = (EditText) findViewById(R.id.inputText);
        logText = (TextView)findViewById(R.id.TextView);
        answer = createAnswer(3);
        Log.d("brad", "answer is " + answer);
    }
    private void initGame(){
        answer = createAnswer(dig);
        Log.d("brad", "answer is " + answer);
        isWinner = false;
        counter = 0;
        logText.setText("");
    }

    private String createAnswer(int num){
        Set<Integer> set = new HashSet<>();
        while (set.size()<num){
            set.add((int)(Math.random()*10));
        }
        StringBuilder sb = new StringBuilder();
        for (Integer d : set){
            sb.append(d);
        }
        return sb.toString();
    }

    private String checkAB(String a, String g){
        int A, B; A = B = 0;
        for (int i=0; i<g.length(); i++){
            if (g.charAt(i) == a.charAt(i)){
                A++;
            }else if (a.indexOf(g.charAt(i))>=0){
                B++;
            }
        }
        return A + "A" + B + "B";
    }

    public void doGuess(View view){
        // TODO doGuess
        counter++;
        String guess = inputEdit.getText().toString();
        String result = checkAB(answer, guess);
        logText.append(counter + ". " + guess + " : " + result + "\n");
        inputEdit.setText("");

        if (result.equals("3A0B")){
            // Winner
            isWinner = true;
            showDialog();
        }else if (counter == 10){
            // Loser
            isWinner = false;
            showDialog();
        }

    }

    private void showDialog(){

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(isWinner?"Winner":"Loser:" + answer);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                initGame();
            }
        } );
        dialog = builder.create();
        dialog.show();

    }
    public void doReset(View view){
        // TODO doReset
    }
    public void doSeting(View view){
        // TODO doSetting
    }
    public void doExit(View view){
        // TODO doExit
    }

}
