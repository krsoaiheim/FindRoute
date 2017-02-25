package com.example.labyr;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
  private List<List<Point>> labyrinthMap = new ArrayList<>();
  public long getWayLength() {
    return wayLength;
  }
  public void setWayLength(long wayLength) {
    this.wayLength = wayLength;
  }
  private long wayLength;
  public Labyrinth() {
    this.labyrinthMap = new ArrayList<>();
  }
  public List<List<Point>> getLabyrinthMap() {
    return labyrinthMap;
  }
  public Point get(int x, int y) {
    return labyrinthMap.get(y).get(x);
  }
  public List<Point> lookAround(int x, int y) {
    List<Point> list = new ArrayList<>();
    if (y>0) {
      list.add(get(x, y-1));
    }
    if (y<labyrinthMap.size()-1) {
      list.add(get(x, y+1));
    }
    if (x>0) {
      list.add(get(x-1, y));
    }
    if (x<labyrinthMap.get(0).size()-1) {
      list.add(get(x+1, y));
    }
    return list;
  }
}
