package mainGUI.Tile_Manager;

import mainGUI.Gamepanel.game2;
import mainprogram.traffic_control;
import mainGUI.Entity.cars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class tilemanager extends Tiles
{
    game2 gp;
    Tiles[] tile; // create tiles class as an array

    int[][] maptilenumber;

    public traffic_control trafficMain;
    cars cars;

    public tilemanager(game2 gp, traffic_control trafficMain)
    {
        this.gp = gp;
        this.trafficMain = trafficMain;

        tile = new Tiles[20];
        maptilenumber = new int[gp.maxworldrow][gp.maxworldcol];

        System.out.println(trafficMain.intersection[trafficMain.intersectionnum].getName());

        tileimage();
        if(trafficMain.intersection[trafficMain.intersectionnum].getName().equals("4-way-intersection"))
        {
            loadmap("/map/map.txt");
        }
        else if(trafficMain.intersection[trafficMain.intersectionnum].getName().equals("T Junction intersection"))
        {
           loadmap("/map/map2.txt");
        }

    }

    public void tileimage()
    {
        try
        {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-4.png")));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-3.png")));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-2.png")));

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-1.png")));

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/traffic lights/New Piskel traffic-2.png")));

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/traffic lights/New Piskel traffic-3.png")));

            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/traffic lights/New Piskel traffic-4.png")));

            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-1 2.png")));

            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-2 1.png")));

            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-4 3.png")));

            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-middle.png")));

            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-side.png")));

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road-side2.png")));

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road edges-13.png")));

            tile[14] = new Tiles();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road edges-14.png")));

            tile[15] = new Tiles();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/icons/road/road edges-15.png")));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadmap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (row < gp.maxworldrow) {
                String line = br.readLine(); // Read a line from the file

                if (line != null) {
                    // Split the line into numbers separated by spaces
                    String[] numbers = line.trim().split("\\s+");

                    while (col < gp.maxworldcol) {
                        int x = Integer.parseInt(numbers[col]); // Convert string to int
                        maptilenumber[row][col] = x; // Assign to map array
                        col++;
                    }
                    if (col == gp.maxworldcol) {
                        col = 0;
                        row++;
                    }
                } else {
                    // If there's no more content, stop reading
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2)
    {
        for (int worldRow = 0; worldRow < gp.maxworldrow; worldRow++)
        {
            for (int worldCol = 0; worldCol < gp.maxworldcol; worldCol++)
            {
                int tileNum = maptilenumber[worldRow][worldCol]; // Get tile type
                int screenX = worldCol * gp.tileseize;
                int screenY = worldRow * gp.tileseize;

                // Render tile at fixed position
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileseize, gp.tileseize, null);
            }
        }
    }

    public void updatetile()
    {

        int trafficLightX = 10 * gp.tileseize;
        int trafficLightY = 3 * gp.tileseize;

        int tileNum;
        if (trafficMain.simulation[trafficMain.intersectionnum].isGreen()) {
            tileNum = 6; // Green traffic light index
        } else if (trafficMain.simulation[trafficMain.intersectionnum].isYellow()) {
            tileNum = 5; // Yellow traffic light index
        } else {
            tileNum = 4; // Red traffic light index
        }

        // Update the map to reflect the current traffic light state
        maptilenumber[6][10] = tileNum;
    }
}
