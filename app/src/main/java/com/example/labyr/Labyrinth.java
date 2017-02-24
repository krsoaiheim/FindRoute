package com.example.labyr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 23.02.2017.
 */

public class Labyrinth {
    private List<List<Point>> labyrinthMap = new ArrayList<>();
    public Labyrinth() {
        this.labyrinthMap = new ArrayList<>();
    }
    public Labyrinth(List<List<Point>> labyrinthMap) {
        this.labyrinthMap = labyrinthMap;
    }
    public List<List<Point>> getLabyrinthMap() {
        return labyrinthMap;
    }
    public Point get(int x, int y) {
        return labyrinthMap.get(y).get(x);
    }


    public Point get(Point point) {
        return labyrinthMap.get(point.getY()).get(point.getX());
    }

    public List<Point> lookAround(Point point) {
        List<Point> list = new ArrayList<Point>();
        if (point.getY() > 0) {
            list.add(get(point.getY() - 1, point.getX()));
            System.out.println("up");
        }
        if (point.getY() < labyrinthMap.size() - 1) {
            list.add(get(point.getY()+1, point.getX()));
            System.out.println("down");
        }
        if (point.getY() > 0) {
            list.add(get(point.getY(), point.getX()-1));
            System.out.println("left");
        }
        if (point.getX() < labyrinthMap.get(0).size() - 1) {
            list.add(get(point.getY(), point.getX()+1));
            System.out.println("right");
        }
        return list;
    }
}
