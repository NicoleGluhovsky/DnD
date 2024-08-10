package dnd.GameTile;
import java.util.Random; 
import dnd.GameTile.Units.HeroicUnit;
import dnd.GameTile.Units.Warrior;
import dnd.GameTile.Units.Enemy;

public class Combat{
    private Random random = new Random(); 


    public void Attack(Unit Attaker, Unit Attacked){
        int attackPower = getRandomAP(Attaker);
        int defensePoints = getRandomDP(Attacked);
        int diffrance = attackPower - defensePoints;
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
