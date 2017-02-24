package com.example.labyr;

public class Point {
  private int x;
  private int y;
  private byte wall;
  private int n;
  public Point(int x, int y, byte w, int n) {
    this.x = x;
    this.y = y;
    this.wall = w;
    this.n = n;
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public int getN() {
    return n;
  }
  public void setN(int n) {
    this.n = n;
  }
  public byte getWall() {
    return wall;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Point point2D = (Point) o;
    return x == point2D.x && y == point2D.y;
  }
}
