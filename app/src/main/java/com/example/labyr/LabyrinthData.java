package com.example.labyr;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LabyrinthData {
  public long getWay() {
    return way;
  }
  private int way;
  public Labyrinth readData(String fileName, Context cont) {
    BufferedReader is;
    Labyrinth labyrinth = new Labyrinth();
    try {
      AssetManager am = cont.getAssets();
      is = new BufferedReader(new InputStreamReader(am.open(fileName)));
      String line = is.readLine();
      int y = 0;
      way =0;
      while (line != null) {
        List<Point> x = new ArrayList<>();
        for (int i = 0; i<line.length(); i++) {
          x.add(new Point(i, y, Byte.parseByte(String.valueOf(line.charAt(i))), 0));
          way =Integer.parseInt(String.valueOf(line.charAt(i)));
        }
        labyrinth.getLabyrinthMap().add(x);
        labyrinth.setWayLength(way);
        line = is.readLine();
        y++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return labyrinth;
  }
}
