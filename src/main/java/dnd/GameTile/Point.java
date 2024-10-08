package dnd.GameTile;

import View.CLIManagement.MessageCallBack;
import dnd.UnitManagment.Directions;    
public class Point implements Comparable<Point>{
    private int x;
    private int y;
    private final MessageCallBack mc;

    public Point(int x, int y, MessageCallBack mc){
        this.x = x;
        this.y = y;
        this.mc = mc;
    }

    public Point(Point other){
        this.x = other.x;
        this.y = other.y;
        this.mc = other.mc;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    private void setX(int x){
        this.x = x;
    }
    private  void setY(int y){
        this.y = y;
    }
    public void Move(Directions direction){
        switch (direction) {
            case UP -> setY(getY() - 1);
            case DOWN -> setY(getY() + 1);
            case LEFT -> setX(getX() - 1);
            case RIGHT -> setX(getX() + 1);
            case SKIP -> {}
            default -> mc.send("Error: Invalid direction.");
        }
    }

    public int xDiff(Point otherPoint){
        return this.x - otherPoint.x;
    }
    public int yDiff(Point otherPoint){
        return this.y - otherPoint.y;
    }

    public double Range(Point otherPoint){
        return Math.sqrt(Math.pow(xDiff(otherPoint), 2.0) + Math.pow(yDiff(otherPoint), 2.0));
    }
    public boolean isInRange(Point otherPoint, double range){
        return Range(otherPoint) < range;
    }

    @Override
    public int compareTo(Point other) {
        // Compare by x coordinate, then by y coordinate
        if (this.x != other.x) {
            return Integer.compare(this.x, other.x);
        } else {
            return Integer.compare(this.y, other.y);
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || ! (other instanceof Point)) {
            return false;
        }
        Point otherPoint = (Point) other;
        return x == otherPoint.x && y == otherPoint.y;
    }
    
}

