package dnd.GameTile.Units;

import java.util.List;
import java.util.Random;

import Controller.GameTickSingleton;
import dnd.UnitManagment.Bars.AbilityBar;
import dnd.UnitManagment.MagicNumbers;

public class Warrior extends Player{
    private final AbilityBar abilityBar; //current will be the remaining cooldown. max will be the cooldown time. each game tick will increase current by 1.


    public Warrior(String name, int health, int AP, int DP, int abilityCooldown){
        super(name, health, AP, DP);
        this.abilityBar = new AbilityBar(abilityCooldown, abilityCooldown, abilityCooldown);
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
        else{
            mc.send(getUnitName() + " is on cooldown");
        }
    }

    @Override
    public void regainAbility(){
        abilityBar.regain(1);
    }

    private void AvengersShield(){
        Random rnd = new Random();
        List<Enemy> enemies = GameTickSingleton.getInstance().getValue().getEnemiesInRange(MagicNumbers.THREE.getValue());
        int heal = MagicNumbers.TEN.getValue() * this.getDP();
        this.Heal(heal);
        mc.send(this.getUnitName() + " used Avenger's Shield, healing for "+ heal);
        if(!enemies.isEmpty()){
            Enemy enemy = enemies.get(rnd.nextInt(enemies.size()));
            combat.AbilityAttack(this, enemy, AbilityDamage());
            System.out.println("after ability");
        }
       
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

