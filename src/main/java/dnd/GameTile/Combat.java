package dnd.GameTile;
import java.util.Random;

import View.CLI;
import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Warrior;

public class Combat{
    private final Random random = new Random();
    private final CLI cli = new CLI();



    public void Attack(Unit Attaker, Unit Attacked){
        int attackPower = getRandomAP(Attaker);
        int defensePoints = getRandomDP(Attacked);
        int diffrance = attackPower - defensePoints;
        cli.displatCombat(Attaker, Attacked, attackPower, defensePoints);
        if(diffrance > 0){
            boolean resCombat = Attacked.takeHit(diffrance);
            if(resCombat){
                Attacked.death(Attaker);
            }
        }
    }
    private int getRandomAP(Unit unit){
        return random.nextInt(unit.getAP());
    }
    private int getRandomDP(Unit unit){
        return random.nextInt(unit.getDP());
    }

    public void AbilityAttack(Unit Attaker, Unit Attacked, int AblityDamege){
        int defensePoints = getRandomDP(Attacked);
        int diffrance = AblityDamege - defensePoints;
        if(diffrance > 0){
            boolean resCombat = Attacked.takeHit(diffrance);
            if(resCombat){
                Attacked.death(Attaker);
            }
        }
    }

    public void AvengersShield(Warrior Attaker, Enemy Attacked){
        int attackPower = Attaker.AbilityDamage();
        boolean resCombat = Attacked.takeHit(attackPower);
        if(resCombat){
            Attacked.death(Attaker);
        }
    }



}
