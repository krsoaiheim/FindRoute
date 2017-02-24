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
        return labyrinthMap.get(x).get(y);
    }


    public Point get(Point point) {
        return labyrinthMap.get(point.getX()).get(point.getY());
    }
    public List<Point> lookAround(int x, int y) {
        List<Point> list = new ArrayList<Point>();
        list.add(get(x, y - 1));
        list.add(get(x, y + 1));
        list.add(get(x - 1, y));
        list.add(get(x + 1, y));
        return list;
    }
    public List<Point> lookAround(Point point) {
        List<Point> list = new ArrayList<Point>();
        if (point.getY() > 0) {
            list.add(get(point.getX(), point.getY() - 1));
        }
        if (point.getY() < labyrinthMap.size() - 1) {
            list.add(get(point.getX(), point.getY() + 1));
        }
        if (point.getY() > 1) {
            list.add(get(point.getX() - 1, point.getY()));
        }
        if (point.getX() < labyrinthMap.size() - 1) {
            list.add(get(point.getX() + 1, point.getY()));
        }
        return list;
    }
}
