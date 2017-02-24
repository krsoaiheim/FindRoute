package com.example.labyr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText startX, endX, startY, endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startX = (EditText) findViewById(R.id.editText);
        endX = (EditText) findViewById(R.id.editText3);
        startY = (EditText) findViewById(R.id.editText2);
        endY = (EditText) findViewById(R.id.editText4);
        final Button button = (Button) findViewById(R.id.button);
        final int[] param = new int[4];
     final   Labyrinth labyrinth= (new DataReader()).readData("lab.txt", getApplicationContext
                ());
       final List<List<Point>> labyrinthMap =labyrinth.getLabyrinthMap();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startX.getText().toString().isEmpty() || endX.getText().toString().isEmpty() ||
                        startY.getText().toString().isEmpty() || endY.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Не все значения введены", Toast.LENGTH_SHORT).show();
                }
                else {
                    param[0] = Integer.parseInt(startX.getText().toString())-1;
                    param[1] = Integer.parseInt(startY.getText().toString())-1;
                    param[2] = Integer.parseInt(endX.getText().toString())-1;
                    param[3] = Integer.parseInt(endY.getText().toString())-1;
                }
                   // Route lab = new Route();

                    Route route =new Route();

                    LinearLayout v = (LinearLayout) findViewById(R.id.results_layout);
                    if (!(param[0] < labyrinthMap.size() & param[2] < labyrinthMap.size() & param[1] < labyrinthMap.get(0).size() &
                            param[3] < labyrinthMap.get(0).size())) {
                        Toast.makeText(getApplicationContext(), "Неверные координаты", Toast.LENGTH_SHORT).show();
                    }
                    else if (labyrinth.get(param[2],param[3]).getWall()==1|| labyrinth.get
                        (param[0],param[1]).getWall()==1){
                        Toast.makeText(getApplicationContext(), "Там стены", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        button.setClickable(false);
                        route.buildRoute(labyrinth, param[0], param[1], param[2], param[3], getApplicationContext());
                    if (route.isPathFound()) {
                        Toast.makeText(getApplicationContext(), "Маршрут не найден", Toast.LENGTH_SHORT).show();
                        button.setClickable(true);
                    } else{
                        v.setVisibility(View.VISIBLE);
                        // tv = (TextView) findViewById(R.id.route);
                       // tv.setText(route.getRoute());
                        TextView tv = (TextView) findViewById(R.id.time);
                        tv.setText(String.valueOf(route.getTime()));
                        tv = (TextView) findViewById(R.id.rnumber);
                        tv.setText(String.valueOf(route.getRouteLength()));
                        tv = (TextView) findViewById(R.id.array);
                        String labText = "";
                        for (List<Point> y :labyrinthMap) {
                            for (Point xPoint: y) {
                                List<Point> list=route.getPathPoint();
                                int wall= xPoint.getWall();
                                if (list.contains(xPoint)) {
                                        labText += "<font color=#999999>" + wall+
                                                "</font>";
                                                             }
                                else {
                                    labText += "<font color=#ffffff>" +wall+ "</font>";
                                }
                            }
                            labText += "<br>";
                        }
                        tv.setText(Html.fromHtml(labText));
                        button.setClickable(true);
                    }}
                }

        });
    }




}