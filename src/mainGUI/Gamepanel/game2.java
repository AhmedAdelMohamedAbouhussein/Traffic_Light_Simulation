package mainGUI.Gamepanel;

import mainGUI.Entity.cars;
import mainGUI.Tile_Manager.tilemanager;

import mainprogram.traffic_control;

import javax.swing.*; //JFrame
import java.awt.*; //Graphics
import java.util.ArrayList;


public class game2 extends JPanel implements Runnable//class inherits all Jpanel class
{
    //this is a subclass of JPanel
    //Japanel works as a game screen

    //screen Settings
    public final int originaltilesize = 48; // 16x16 pixels default size of playable charecter or any npcs
    public final int scale = 1;
    public final int tileseize = originaltilesize * scale; //48x48 pixels
    //public because we want to access it from another package

    //screen size:
    public final int maxScreencol = 17;
    public final int maxScreenrow = 15;
    public int screenWidth = tileseize * maxScreencol; //16 48 pixel columns
    public int screenHeight = tileseize * maxScreenrow;//12 48 pixel roows

    //world size
    public final int maxworldcol = 17;
    public final int maxworldrow = 15;
    public final int worldwidth = tileseize * maxworldcol;
    public final int worldheight = tileseize * maxworldrow;

    Thread gameThread; //creates game clock will its on the program stays on
    int fps = 60;


    public traffic_control trafficMain;

    public ArrayList<cars> player;

    tilemanager tilem;

    //constructor for gamepane
    public game2(traffic_control trafficMain)
    {
        this.trafficMain = trafficMain;

        //set the size of this class Jpane
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black); //background color

        //if set to true, all the drawings from this component will be done in an offscreen painting buffer
        this.setDoubleBuffered(true); //improves the game's rendering preformance

        //this.addKeyListener(keys); // to recognice key input
        this.setFocusable(true); //the gamepanel can focus to recieve key input

         player = new ArrayList<>();
         //player.add(new cars (this,trafficMain));
         //player.add(new cars (this,trafficMain));

        int numberofcars = 300;

        for (int i = 0; i < numberofcars; i++) {
            cars newCar = new cars(this, trafficMain);
            newCar.worldxstartpoint = tileseize * (maxworldcol / 2) + tileseize / 2; // Staggered x positions
            newCar.worldystartpoint = tileseize * (maxworldrow / 2)  +  tileseize * (2 * i + 1);
            player.add(newCar);
        }

        tilem = new tilemanager(this, trafficMain);
    }

  ; // public to access it in tilemanager


    public void Startgamethread()
    {
        gameThread = new Thread(this);//this means this class (game2)
        gameThread.start();
    }
    public void run() //implement runnable
    {
        //when you call the Thread (gameThread) the method run it automatically called

        //* Delta / Accumulator method
        double drawinterval = (double) 1000000000 /fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currenttime;

        //*display fps
        long timer = 0;
        int drawcount = 0;

        //game loop/ core of the game
        while(gameThread != null)
        {
            //* Delta / Accumulator method
            currenttime = System.nanoTime();
            delta += (currenttime - lastTime) / drawinterval;
            timer += currenttime - lastTime; //fps
            lastTime = currenttime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawcount++; //fps
            }

            if(timer >= 1000000000) //fps
            {
                //System.out.println("FPS: " + drawcount);
                drawcount = 0;
                timer = 0;
            }
        }
    }

    public void update()
    {
        for(cars player : player)
        {
            player.update(); // call the method inside the cars class
        }

        tilem.updatetile();


    }
    public void paintComponent(Graphics g) //2
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //Graphics 2D class extends the Graphics class to provide more sophisticated control over shapes/coordinates/text
        tilem.draw(g2);

        for(cars player : player)
        {
            player.draw(g2); // call the method inside the cars class
        }
        g2.dispose();
    }
}