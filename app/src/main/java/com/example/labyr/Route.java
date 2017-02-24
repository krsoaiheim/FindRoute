package com.example.labyr;

import android.content.Context;
import java.util.List;
import java.util.Stack;

public class Route {
  private List<Point> pathPoint= new Stack<>();
  private int turn;
  private boolean pathFound = true;
  private String route;
  private long passageTime;
  public void buildRoute(Labyrinth labyrinth, int x1, int y1, int x2, int y2, Context context) {
    int magicNumber = 1000;
    labyrinth = (new DataReader()).readData("lab.txt", context);
    List<List<Point>> labyrinthMap = labyrinth.getLabyrinthMap();
    labyrinth.get(x1, y1).setN(magicNumber);
    labyrinth.get(x2, y2).setN(0);
    long startTime = System.currentTimeMillis();
    boolean stop;
    do {
      stop = true;
      for (List<Point> yPoint : labyrinthMap) {
        int y = yPoint.get(0).getY();
        for (Point xPoint : yPoint) {
          int x = xPoint.getX();
          if (xPoint.getN() == magicNumber) {
            for (Point around : labyrinth.lookAround(x, y)) {
              if ((around.getN() == 0 || around.getN()>magicNumber+1) & around.getWall() == 0) {
                stop = false;
                labyrinth.get(around.getX(), around.getY()).setN(magicNumber+1);
              }
            }
          }
        }
      }
      magicNumber++;
    } while (!stop & labyrinth.get(x2, y2).getN() == 0);
    if (labyrinth.get(x2, y2).getN() == 0) {
      pathFound = false;
    }
    else {
      pathPoint.add(new Point(x2, y2, Byte.parseByte("0"), 0));
      while (magicNumber != 1000) {
        magicNumber--;
        Point lastPointInPath = pathPoint.get(pathPoint.size()-1);
        for (Point around : labyrinth.lookAround(lastPointInPath.getX(), lastPointInPath.getY())) {
          if (around.getN() == magicNumber & around.getWall() == 0) {
            pathPoint.add(around);
            break;
          }
        }
        passageTime = System.currentTimeMillis()-startTime;
        //  Collections.reverse(pathPoint);
        turn = 0;
        route = "";
        Point point, previousPoint;
        for (int i = 2; i<pathPoint.size(); i++) {
          point = pathPoint.get(i);
          previousPoint = pathPoint.get(i-2);
          if (point.getX() != previousPoint.getX() & point.getY() != previousPoint.getY()) {
            turn++;
          }
        }
      }
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
  public int getTurn() {
    return turn;
  }
}