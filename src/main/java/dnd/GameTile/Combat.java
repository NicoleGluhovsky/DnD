package dnd.GameTile;
import java.util.Random;

import org.w3c.dom.html.HTMLLabelElement;

import View.CLI;

import dnd.GameTile.Units.Warrior;
import dnd.UnitManagment.Bars.MagicNumbers;

public class Combat{
    private final Random random = new Random();
    private final CLI cli;
    public Combat(CLI cli){
        this.cli = cli;
    }



    public void Attack(Unit Attaker, Unit Attacked){
        int attackPower = getRandomAP(Attaker);
        int defensePoints = getRandomDP(Attacked);
        int diffrance = attackPower - defensePoints;
        cli.displayCombat(Attaker, Attacked, attackPower, defensePoints);
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

    public void AbilityAttack(Unit Attaker, Unit Attacked, int AbilityDamage){
        int defensePoints = getRandomDP(Attacked);
        int diffrance = AbilityDamage - defensePoints;
        cli.displayAbilityCombat(Attaker, Attacked, AbilityDamage, defensePoints);
        if(diffrance > 0){
            boolean resCombat = Attacked.takeHit(diffrance);
            if(resCombat){
                Attacked.death(Attaker);
            }
        }
    }

}
