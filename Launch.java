import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rubicon on 3/8/15.
 */
@ScriptManifest(author = "Rubicon",
        category = Category.THIEVING,
        description = "Thieving on all stalls!!",
        name = "JuicyThiev",
        servers = { "PKHonor" },
        version = 1.5)
public class Launch extends Script implements Paintable {
    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    Timer timer;
    Image thiev;
    public static int thievs;

    public static int START_EXP = Skill.THIEVING.getExperience();
    public static int getXpGained() {
        final int xpGained = Skill.THIEVING.getExperience() - START_EXP;
        return xpGained;
    }
    public boolean onExecute(){
        timer = new Timer();

        try {
            URL url = new URL("http://oi57.tinypic.com/2ldj34p.jpg");
            thiev = ImageIO.read(url);
        }catch(IOException e){
            e.printStackTrace();
        }
        strategies.add(new Thieving());

        provide(strategies);

        return true;
    }


    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawImage(thiev, 2, 340, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("1.0", 100, 389);//Version
        g.drawString("Rubicon", 95, 406);//Author
        g.drawString(timer.toString(), 115, 425);//Time Ran
        g.drawString(""+Thieving.getStall(), 75, 442);//Current Stall
         g.drawString(""+thievs, 276, 443);//Total Thievs
        g.drawString(""+getXpGained(), 265, 425);//XP Gained
        g.drawString(""+Skill.THIEVING.getRemaining(), 231, 406);//XP TTL
         g.drawString("" + Skill.THIEVING.getRealLevel(), 289, 389);//Current Level


    }

}
