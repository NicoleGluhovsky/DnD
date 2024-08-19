package dnd.UnitManagment.Bars;

public class Bar {
    protected int current;
    protected int max;

    public Bar(int current, int max){
        this.current = current;
        this.max = max;
    }

    public int getCurrent(){
        return this.current;
    }

    public int getMax(){
        return this.max;
    }

    public void setCurrent(int current){
        this.current = current;
    }

    public void setMax(int max){
        this.max = Math.min(max, Integer.MAX_VALUE);
    }

    public void fillBar(){
        this.current = this.max;
    }
}
