package shipsInterface;

import javax.swing.*;
import java.awt.*;

public class WindowGame extends JFrame
{
    private boolean ifRegisterPanelOpened = false;
    private JPanel panelLogin;
    private JPanel panelGame;
    private JLabel titleL;
    private JLabel userNameL;
    private JLabel scoreL;
    private JLabel scoreValueL;

    public WindowGame()
    {
        //this.setSize(new Dimension(1920,1080));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Registration - Version 1.0");
        this.add(new RegisterPanel());
        pack();
    }
}
