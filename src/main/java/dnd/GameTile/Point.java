package dnd.GameTile;

public class Point{
    private int x;
    private int y;

    protected Point(int x, int y){
        this.x = x;
        this.y = y;
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

    protected double Range(Point otherPoint){
        double xDiff = this.x - otherPoint.x;
        double yDiff = this.y - otherPoint.y;
        return Math.sqrt(Math.pow(xDiff, 2.0) + Math.pow(yDiff, 2.0));
    }
}

