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
            list.add(get(point.getX(), point.getY() - 1));
            System.out.println("up");
        }
        if (point.getY() < labyrinthMap.size() - 1) {
            list.add(get(point.getX(), point.getY() + 1));
            System.out.println("down");
        }
        if (point.getY() > 0) {
            list.add(get(point.getX() - 1, point.getY()));
            System.out.println("left");
        }
        System.out.println(labyrinthMap.get(0).size() - 1);
        if (point.getX() < labyrinthMap.get(0).size() - 1) {
            list.add(get(point.getX() + 1, point.getY()));
            System.out.println("right");
        }
        return list;
    }
}
