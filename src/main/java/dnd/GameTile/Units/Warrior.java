package dnd.GameTile.Units;

import Controller.GameTickSingleton;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.Bars.MagicNumbers;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private final AbilityBar abilityBar; //current will be the remaining cooldown. max will be the cooldown time. each game tick will increase current by 1.


    public Warrior(String name, int health, int AP, int DP, int abilityCooldown){
        super(name, health, AP, DP);
        this.abilityBar = new AbilityBar(MagicNumbers.ZERO.getValue(), abilityCooldown, abilityCooldown);
    }

    @Override
    public void levelUP(){
        super.levelUP();
        abilityBar.setCurrent(MagicNumbers.ZERO.getValue());
        super.getHealth().setMax(super.getHealth().getMax() + MagicNumbers.FIVE.getValue() * super.GetLevel());
        super.setAP(super.getAP() + MagicNumbers.TWO.getValue() * super.GetLevel());
        super.setDP(super.getDP() + MagicNumbers.ONE.getValue() * super.GetLevel());
    }

    @Override
    public void castAbility(){
        if(abilityBar.getCurrent() == abilityBar.getMax()){
            abilityBar.setCurrent(MagicNumbers.ZERO.getValue());
            AvengersShield();
        }
    }

    @Override
    public void regainAbility(){
        abilityBar.setCurrent(abilityBar.getCurrent() + 1);
    }

    private void AvengersShield(){
        Random rnd = new Random();
        List<Enemy> enemies = GameTickSingleton.getInstance().getValue().getEnemiesInRange(MagicNumbers.THREE.getValue());
        Enemy enemy = enemies.get(rnd.nextInt(enemies.size()));
        int heal = MagicNumbers.TEN.getValue() * this.getDP();
        this.Heal(heal);
        mc.send(this.getUnitName() + " used Avenger's Shield, healing for "+ heal);
        combat.AbilityAttack(this, enemy, AbilityDamage());
    }
    
    @Override
    public int AbilityDamage(){
        return (int)(this.getHealth().getMax() * 0.10);
    }

    @Override
    public String toString(){
        return super.toString() + "\tAbility Cooldown: " + abilityBar.getCurrent() + "/" + abilityBar.getMax();
    }

}

