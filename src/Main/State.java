package Main;

import Entities.Enemy;

public abstract class State {

    Screen s;

   public State(Screen s){
       this.s = s;
   }
   public State(){
   }


    public abstract void doUpdate();
    public abstract void showDialogue();
    public abstract void inputHandle(InputHandler i, int c);


}

