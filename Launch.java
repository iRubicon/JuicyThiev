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
        version = 1.0)
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
            URL url = new URL("http://services.runescape.com/m=rswikiimages/en/2012/1/Thieving_big-13011312.png");
            thiev = ImageIO.read(url);
        }catch(IOException e){
            e.printStackTrace();
        }
        strategies.add(new Thieving());

        provide(strategies);

        return true;
    }


    public void paint(Graphics g) {
        
        g.setColor(Color.DARK_GRAY);
        g.drawRect(7, 344, 502, 130);
        g.fillRect(7, 344, 502, 130);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.drawString("Juicy Thiev", 17, 366);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("Version 1.0", 30, 384);
        g.drawString("Author: Rubicon", 30, 404);
        g.drawString("Time ran: " + timer.toString(), 30, 424);
        g.drawString("Stall: " + Thieving.getStall(), 150, 384);
         g.drawString("Total Thievs: " + thievs, 150, 404);
        g.drawString("XP gained: " + getXpGained(), 150, 424);
        g.drawString("XP TTL: " + Skill.THIEVING.getRemaining(), 150, 444);
         g.drawString("Current Level: " + Skill.THIEVING.getRealLevel(), 150, 464);

        g.drawImage(thiev, 390, 360, null);

    }

}
