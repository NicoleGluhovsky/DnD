package dnd.UnitManagment.Bars;

public class HealthBar extends Bar{
    public HealthBar(int health){
        super(health, health);
    }

    public boolean takeDamage(int damage){
        this.current = Math.max(0,current - damage);
        return current == 0;
    }

    public void heal(int heal){
        this.current = Math.min(current+heal, max);
    }

}
