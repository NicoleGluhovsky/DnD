package dnd.GameTile;

import dnd.GameTile.MoveUnit.Direction;

public class Point implements Comparable<Point>{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(Point other){
        this.x = other.x;
        this.y = other.y;
    }

    protected int getX(){
        return x;
    }

    protected int getY(){
        return y;
    }

    protected void setX(int x){
        this.x = x;
    }
    protected void setY(int y){
        this.y = y;
    }
    protected void Move(Direction direction){
        switch (direction) {
            case UP -> setY(getY() + 1);
            case DOWN -> setY(getY() - 1);
            case LEFT -> setX(getX() - 1);
            case RIGHT -> setX(getX() + 1);
        }
    }

    protected double Range(Point otherPoint){
        double xDiff = this.x - otherPoint.x;
        double yDiff = this.y - otherPoint.y;
        return Math.sqrt(Math.pow(xDiff, 2.0) + Math.pow(yDiff, 2.0));
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
    
}

