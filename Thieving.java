import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created by Rubicon on 3/8/15.
 */
public class Thieving implements Strategy{

    public static Stalls getStall(){
        int level = Skill.THIEVING.getRealLevel();

        if(level < 20){
            return Stalls.Bakers;
        }else if(level >= 20 && level < 35){
            return Stalls.Silk;
        }else if(level >= 35 && level < 65){
            return Stalls.Fur;
        }else if(level >= 65 && level < 75){
            return Stalls.Spice;
        }else if(level >= 75){
            return Stalls.Gem;
        }

        return Stalls.Bakers;
    }

    public int getID(Stalls stall){
        if(stall == Stalls.Bakers){
            return 2561;
        }else if(stall == Stalls.Silk){
            return 2560;
        }else if(stall == Stalls.Fur){
            return 2563;
        }else if(stall == Stalls.Spice){
            return 2564;
        }else if(stall == Stalls.Gem){
            return 2562;
        }
        return 2561;

    }


    public boolean activate() {
        if(Players.getMyPlayer().getAnimation() == -1 && Inventory.isFull() == false){
            return true;
        }
        return false;
    }


    public void execute() {
        SceneObject[] stall = SceneObjects.getNearest(getID(getStall()));
       
        try{
            stall[0].interact(1);
            Launch.thievs += 1;
            Time.sleep(1500);
        }catch(ArrayIndexOutOfBoundsException e){

        }

    }







    }
