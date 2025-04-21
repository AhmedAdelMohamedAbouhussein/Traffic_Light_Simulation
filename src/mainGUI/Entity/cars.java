package mainGUI.Entity;

import mainprogram.traffic_control;

import mainGUI.Gamepanel.game2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class cars extends entities
{
    traffic_control traffic;
    game2 gp;


    public final int screenxstartingpoint;
    public final int screenystartingpoint;

    public cars(game2 gp, traffic_control traffic) {
        this.gp = gp;
        this.traffic = traffic; // Initialize traffic


        // Screen setup
        screenxstartingpoint = gp.screenWidth / 2 ;
        screenystartingpoint = gp.screenHeight / 2 - (gp.tileseize / 2);

        setdefaultvalues();
        playerimage();
        direction = true;
    }

    public void setdefaultvalues()
    {
        //in java the upper left corner is x:0 y:0;
        worldxstartpoint = gp.tileseize * (gp.maxworldcol / 2);
        worldystartpoint = gp.tileseize * (gp.maxworldrow / 2) - gp.tileseize/2;
        speed = 1;
    }

    public void playerimage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/cars/NPC_cars (16 x 16) (1).png")));
            //up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/cars/NPC_cars (16 x 16) (1).png")));
            //up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/cars/NPC_cars (16 x 16) (1).png")));
            side1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/cars/NPC_cars (16 x 16) (1)-1.png")));
            //side2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/cars/NPC_cars (16 x 16) (1).png")));
            //side3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/cars/NPC_cars (16 x 16) (1).png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int count = 0;
    public void update()
    {
        for(int i = 0; i < 100; i++)
        {
            if(traffic.simulation[i] != null)
            {
                count = i;
                break;
            }
        }

        if (traffic.simulation[traffic.intersectionnum].isGreen() && worldystartpoint > 216)
        {
            worldystartpoint -= speed;
        }
        else if(worldystartpoint == 216)
        {
            worldxstartpoint += speed;
        }

    }

    public void draw(Graphics2D g2)
    {
        int screenX = worldxstartpoint - (gp.worldwidth / 2) + (gp.screenWidth / 2);
        int screenY = worldystartpoint - (gp.worldheight / 2) + (gp.screenHeight / 2);

        if (worldystartpoint > 216)
        {
            BufferedImage image = up1;
            g2.drawImage(image, screenX, screenY, gp.tileseize, gp.tileseize, null);
        }
        else if(worldystartpoint == 216)
        {
            BufferedImage image = side1;
            g2.drawImage(image, screenX, screenY, gp.tileseize, gp.tileseize, null);

        }
    }
}