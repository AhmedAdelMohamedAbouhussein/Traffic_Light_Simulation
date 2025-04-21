package mainprogram;
import mainGUI.Gamepanel.game2;
import javax.swing.*;

public class originalmain
{

    public originalmain()
    {
        traffic_control controller = new traffic_control();
        controller.showMenu();

        // Create window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("12th_OOP_project");

        // Create game2 with the current main instance
        game2 gamepanel1 = new game2(controller);
        window.add(gamepanel1);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamepanel1.Startgamethread();
    }

    public static void main(String[] args)
    {
        new originalmain();
    }

}
