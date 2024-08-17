package Input;

import dnd.GameTile.Units.Enemy;
import dnd.GameTile.Units.Player;

import View.CLIManagement.MessageCallBack;

public class EnemyTurn extends Turn{
protected Enemy enemy;

    public EnemyTurn(Player player, Enemy enemy, MessageCallBack mc){
        super(player, mc);
        this.enemy = enemy;
    }

    public void play(){
        enemy.checkRange(player, moveUnit);
    }
}
