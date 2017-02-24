package com.example.labyr;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Route {
    List<Point> pathPoint = new Stack<>();
    private Labyrinth labyrinth;
    private int routeLength;
    private boolean pathFound = true;
    private String route;
    private long passageTime;

    public void buildRoute(Labyrinth labyrinth, int x1, int y1, int x2, int y2, Context context) {

        int magicNumber = 1000;
        labyrinth=(new DataReader()).readData("lab.txt",context);
        List<List<Point>> labyrinthMap = labyrinth.getLabyrinthMap();
        labyrinth.get(x1,y1).setN(magicNumber);
        labyrinth.get(x2,y2).setN(0);
        System.out.println(labyrinth.get(x1,y1).getN());
        System.out.println(labyrinth.get(x2,y2).getN());
        long startTime = System
                .currentTimeMillis();
        boolean stop;
        do {
            labyrinth.get(x2,y2).setN(0);
            System.out.println(x2+" "+y2);
            stop = true;
            for (List<Point> y :labyrinthMap) {
                for (Point xPoint: y) {
                    if (xPoint.getN() == magicNumber) {
                      //  System.out.println(xPoint.getX()+" "+xPoint.getY());
                        for (Point around: labyrinth.lookAround(xPoint)){
                            if (around.getN()==0||around.getN()>magicNumber+1){
                                stop=false;
                              // System.out.println(around.getX()+" "+around.getY());
                                labyrinth.get(around.getX(), around.getY()).setN(magicNumber+1);
                            }
                        }
                    }
                }
            }
            magicNumber++;
        } while (!stop & labyrinth.get(x2,y2).getN() == 0);
        if (labyrinth.get(x2,y2).getN() == 0) {
            pathFound = false;
        }
        else {
            pathPoint.add(new Point(x2,y2,Byte.parseByte("0"),0));

            while (magicNumber != 1000) {
                magicNumber--;
                Point lastPointInPath= pathPoint.get(pathPoint.size()-1);
                for(Point around:labyrinth.lookAround(lastPointInPath.getX(), lastPointInPath.getY())){
                    if (around.getN()==magicNumber){pathPoint.add(around);
                       }
            }
            passageTime = System.currentTimeMillis() - startTime;
            Collections.reverse(pathPoint);
            routeLength = 0;
            route = "";
                Point point,previousPoint;

                for(int i=1; i<pathPoint.size(); i++){
                    point=pathPoint.get(i);
                    previousPoint=pathPoint.get(i-1);

                if (point.getX() == previousPoint.getX()) {
                    if (point.getY() > previousPoint.getY()) {
                        route += "1";
                        routeLength++;
                    }
                    else {
                        route += "3";
                        routeLength++;
                    }
                }
                else {
                    if (point.getX() > previousPoint.getX()) {
                        route += "2";
                    }
                    else {
                        route += "0";
                    }
                }
            }}
        }
    }


    public boolean isPathFound() {
        return pathFound;
    }



    public List<Point> getPathPoint() {
        return pathPoint;
    }



    public String getRoute() {
        return route;
    }

    public long getTime() {
        return passageTime;
    }

    public int getRouteLength() {
        return routeLength;
    }
}