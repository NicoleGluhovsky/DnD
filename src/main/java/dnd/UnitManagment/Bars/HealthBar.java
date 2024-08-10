package dnd.UnitManagment.Bars;

public class HealthBar extends Bar{
    public HealthBar(int current, int max){
        super(current, max);
    }

    public boolean takeDamage(int damage){
        this.current -= damage;
        return handleDeath();
    }

    public void heal(int heal){
        this.current += heal;
    }

    public boolean handleDeath(){
        if(this.current <= 0){
            this.current = 0;
            return true;
        }
        return false;
    }
}
