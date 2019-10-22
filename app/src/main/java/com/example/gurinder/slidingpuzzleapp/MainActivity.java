package com.example.gurinder.slidingpuzzleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView moves, outputResult;
    int count = 0;

    int[] imagesArrayMain = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,
            R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
            R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,
            R.drawable.img14,R.drawable.img15,R.drawable.img_empty};

    int[] imagesArrayAdv = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,
            R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
            R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,
            R.drawable.img14,R.drawable.img15,R.drawable.img_empty};



    private ImageView[] imgArray = new ImageView[17];

    Button newPuzzle, solvedPuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moves = (TextView) findViewById(R.id.movesSoFar);
        outputResult = (TextView) findViewById(R.id.outputResult);

        newPuzzle = (Button) findViewById(R.id.newPuzzle);
        solvedPuzzle = (Button) findViewById(R.id.solvePuzzle);

        for(int i = 1; i <=imagesArrayAdv.length; i++) {

            String imageId = "img" + i;
            int x = i - 1;
            int resId = getResources().getIdentifier(imageId, "id", getPackageName());
            imgArray[i] = findViewById(resId);
        }

        if(savedInstanceState!=null)
        {

            count=Integer.parseInt(savedInstanceState.getString("count"));
            moves.setText("moves so far : " + count);
            outputResult.setText(savedInstanceState.getString("result"));
            newPuzzle.setText(savedInstanceState.getString("newPuzzle"));
            solvedPuzzle.setText(savedInstanceState.getString("solvedPuzzle"));

            retainData(savedInstanceState.getIntArray("imgArray"));
        }else {
            shuffleArray();
        }



        newPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleArray();
                count = 0;
                moves.setText("Moves So far : "+count);
                outputResult.setText("");
            }
        });

        solvedPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solvedArray();
                boolean result= checkWin(imgArray);
                if(result)
                {
                    outputResult.setText("Solved Puzzle!! Moves Tried:" + count + " Click New Puzzle " +
                            "to start new game");
                }
            }
        });


    }


    public void shuffleArray(){

        for(int i = 0; i < imagesArrayAdv.length; i++){
            int random = (int)(Math.random() * 100) % 16;
            int random2 = (int)(Math.random() * 100) % 16;
            int temp = imagesArrayAdv[random];
            imagesArrayAdv[random] = imagesArrayAdv[random2];
            imagesArrayAdv[random2] = temp;
        }

        for(int i = 1; i <= imagesArrayAdv.length; i++){

            int x = i - 1;
            imgArray[i].setImageDrawable(getDrawable(imagesArrayAdv[x]));
            imgArray[i].setTag(imagesArrayAdv[x]);
            imgArray[i].setOnClickListener(this);

        }

    }

    public void solvedArray(){
        int[] imageArraySolved = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,
                R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
                R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,
                R.drawable.img14,R.drawable.img15,R.drawable.img_empty};


        for(int i = 1; i <=imageArraySolved.length; i++){

            String imageId = "img" + i;
            int x = i - 1;
            int resId = getResources().getIdentifier(imageId,"id",getPackageName());
            imgArray[i] = findViewById(resId);
            imgArray[i].setImageDrawable(getDrawable(imageArraySolved[x]));
            imgArray[i].setTag(imageArraySolved[x]);
            imgArray[i].setClickable(false);

            boolean result= checkWin(imgArray);
            if(result)
            {
                outputResult.setText("Congrats you won the game!! No. of moves: " + count);
            }

        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.img1:
                if(changeData(imgArray[5],imgArray[1]) || changeData(imgArray[2],imgArray[1])){
                    count++;
                }
                else {showToast();}
                break;

            case R.id.img2:
                if(changeData(imgArray[6],imgArray[2]) || changeData(imgArray[3],imgArray[2]) ||
                changeData(imgArray[1],imgArray[2])){count++;}
                else {showToast();}
                break;

            case R.id.img3:
                if(changeData(imgArray[7],imgArray[3]) || changeData(imgArray[4],imgArray[3]) ||
                changeData(imgArray[2],imgArray[3])){count++;}
                else {showToast();}
                break;

            case R.id.img4:
                if(changeData(imgArray[8],imgArray[4]) || changeData(imgArray[3],imgArray[4])){
                    count++;
                }
                else {showToast();}
                break;
            case R.id.img5:
                if(changeData(imgArray[9],imgArray[5]) || changeData(imgArray[6],imgArray[5]) ||
                changeData(imgArray[1],imgArray[5])){count++;}
                else {showToast();}
                break;

            case R.id.img6:
                if(changeData(imgArray[10],imgArray[6]) || changeData(imgArray[7],imgArray[6]) ||
                changeData(imgArray[5],imgArray[6]) || changeData(imgArray[2],imgArray[6])){count++;}
                else {showToast();}

                break;
            case R.id.img7:
                if(changeData(imgArray[11],imgArray[7]) || changeData(imgArray[8],imgArray[7]) ||
                changeData(imgArray[6],imgArray[7]) || changeData(imgArray[3],imgArray[7])){count++;}
                else {showToast();}

                break;
            case R.id.img8:
                if(changeData(imgArray[12],imgArray[8]) || changeData(imgArray[7],imgArray[8]) ||
                changeData(imgArray[4],imgArray[8])){count++;}
                else {showToast();}
                break;

            case R.id.img9:
                if(changeData(imgArray[13],imgArray[9]) || changeData(imgArray[10],imgArray[9]) ||
                changeData(imgArray[5],imgArray[9])){count++;}
                else {showToast();}

                break;
            case R.id.img10:
                if(changeData(imgArray[14],imgArray[10])|| changeData(imgArray[11],imgArray[10])||
                changeData(imgArray[9],imgArray[10])|| changeData(imgArray[6],imgArray[10])){count++;}
                else {showToast();}

                break;
            case R.id.img11:
                if(changeData(imgArray[15],imgArray[11]) || changeData(imgArray[12],imgArray[11]) ||
                changeData(imgArray[10],imgArray[11]) || changeData(imgArray[7],imgArray[11])){count++;}
                else {showToast();}
                break;

            case R.id.img12:
                if(changeData(imgArray[16],imgArray[12]) || changeData(imgArray[11],imgArray[12]) ||
                changeData(imgArray[8],imgArray[12])){count++;}
                else {showToast();}


                break;
            case R.id.img13:
                if(changeData(imgArray[14],imgArray[13]) || changeData(imgArray[9],imgArray[13])){
                    count++;
                }
                else {showToast();}
                break;

            case R.id.img14:
                if(changeData(imgArray[15],imgArray[14]) || changeData(imgArray[13],imgArray[14]) ||
                changeData(imgArray[10],imgArray[14])){count++;
                }
                else {
                    showToast();
                }
                break;
            case R.id.img15:
                if(changeData(imgArray[16],imgArray[15]) || changeData(imgArray[14],imgArray[15]) ||
                        changeData(imgArray[11],imgArray[15])){count++;
                }
                else {
                    showToast();
                }
                break;

            case R.id.img16:
                if(changeData(imgArray[15],imgArray[16]) || changeData(imgArray[12],imgArray[16])){
                }
                else {
                    showToast();
                }
                break;

        }
        moves.setText("Moves So far : "+count);
    }

    public void showToast(){
        Toast.makeText(this, "Illegal Move", Toast.LENGTH_SHORT).show();
    }

    public boolean changeData(ImageView btnArray,ImageView btnView)
    {
        if(btnArray.getTag().equals(R.drawable.img_empty))
        {
            int c=(int)btnView.getTag();
            btnArray.setImageDrawable(getDrawable(c));
            btnArray.setTag(c);
            btnView.setImageDrawable(getDrawable(R.drawable.img_empty));
            btnView.setTag(R.drawable.img_empty);
            return true;
        }
        return false;
    }

    public boolean checkWin(ImageView[] btnArray)
    {

        for(int i=1;i<=imagesArrayMain.length;++i)
        {
            int x=i-1;
            if(!btnArray[i].getTag().equals(imagesArrayMain[x]))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        int[] x=new int[16];
        outState.putString("count",String.valueOf(count));
        outState.putString("result",outputResult.getText().toString());
        outState.putString("newPuzzle", newPuzzle.getText().toString());
        outState.putString("solvedPuzzle", solvedPuzzle.getText().toString());
        for(int i = 1; i < imgArray.length; ++i)
        {
            int j = i - 1;
           x[j] = (int)imgArray[i].getTag();
        }
        outState.putIntArray("imgArray",x);

    }

    public void  retainData(int[] imagesArrayAdv)
    {
        for(int i = 1; i <17; i++) {

            int x = i - 1;

            imgArray[i].setImageDrawable(getDrawable(imagesArrayAdv[x]));
           imgArray[i].setTag(imagesArrayAdv[x]);
           imgArray[i].setOnClickListener(this);
        }

    }


}
