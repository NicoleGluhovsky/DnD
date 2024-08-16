package dnd.GameTile;

import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;
import dnd.UnitManagment.Bars.HealthBar;

public abstract class Unit extends Tile{
    private final String Name;
    private final HealthBar Health;
    private int AttackPoints;
    private int DefensePoints;
    public Combat combat = new Combat();


    public Unit(char tileChar, String name, int health, int AP, int DP){
        super(tileChar);
        this.Name = name;
        this.Health = new HealthBar(health);
        this.AttackPoints = AP;
        this.DefensePoints = DP;

    }

    protected void gameTick(){}; //each unit has a different game tick
    //protected abstract void obliterate(Unit victim);
    protected abstract void death(Unit killer);  //each unit has a different death

    public abstract void accept(Unit Visitor);

    public abstract void kill(Player Visited);
    public abstract void kill(Enemy Visited);



    protected HealthBar getHealth(){
        return Health;
    }

    protected int getAP(){
        return AttackPoints;
    }

    protected void setAP(int AP){
        this.AttackPoints = AP;
    }

    protected int getDP(){
        return DefensePoints;
    }

    protected void setDP(int DP){
        this.DefensePoints = DP;
    }

    public boolean takeHit(int damage)
    {
        return this.Health.takeDamage(damage);
    } 
    public String getUnitName(){
        return Name;
    }

    @Override
    public void AttackTile(Unit unit){
        combat.Attack(unit, this);
    }

    

    
}

