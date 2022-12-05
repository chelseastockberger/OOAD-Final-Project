package Main;

import Entities.Player;

public interface Command {

    public void execute();

}

class ControlCommand {
    Command slot;
    public void setCommand(PlayerAttackCommand command){
        slot = command;
    }
    public void doCommand(){
        slot.execute();
    }
}


class PlayerAttackCommand implements Command {
    Screen s;
    Player p;
    public PlayerAttackCommand(Screen s, Player p){ this.s = s; this.p = p;}
    public void execute(){

        p.doAttack();

    }

}

class PlayerBlockCommand implements Command {

    Screen s;
    Player p;
    public PlayerBlockCommand(Screen s, Player p){ this.s = s; this.p = p;}
    public void execute(){

        p.doBlock();

    }

}

class NoCommand implements Command {

    public NoCommand(){ }
    public void execute(){



    }

}
