package dnd.UnitManagment.Bars;

public class AbilityBar extends Bar{
    private int abilityCost;

    public AbilityBar(int current, int max, int abilityCost){
        super(current, max);
        this.abilityCost = abilityCost;
    }

    public void setAbilityCost(int abilityCost){
        this.abilityCost = abilityCost;
    }

    public int getAbilityCost(){
        return this.abilityCost;
    }

    public boolean castAbility(){
        if(this.current >= this.abilityCost){
            this.current -= this.abilityCost;
            return true;
        }
        return false;
    }

    public void regain(int m){
        this.current = Math.min(max, current + m);
    }
}
