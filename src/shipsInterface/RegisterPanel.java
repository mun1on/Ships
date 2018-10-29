package shipsInterface;

import database.Driver;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class RegisterPanel extends JPanel implements ActionListener
{

    private String name, surname, user, pass;
    private JLabel firstName, secondName, userName, userPassword, logo;
    private JTextField firstN, surN, userN;
    private JPasswordField userP;
    private JButton register, goToLogin, cancel;

    private static final int SIZE_FONT_L_TF = 18;
    private static final int SIZE_FONT_BUTTON = 14;
    private static final Color COLOR_TF = new Color(170,190,255);

    public RegisterPanel()
    {
        setUpPanel(this);
        this.setVisible(true);
    }

    public void setUpPanel(JPanel panel)
    {
        panel.setSize(new Dimension(1920, 1080));
        panel.setBackground(new Color(100,200,255));
        panel.setFont(new Font("serif", Font.BOLD, SIZE_FONT_L_TF));

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        logo = new JLabel(new ImageIcon("ship.png"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 15, 0);
//        c.weightx = 0.5;
        panel.add(logo, c);

        //first name
        firstName = new JLabel("First Name");
        firstName.setFont(new Font("serif", Font.BOLD, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth=1;
        c.insets = new Insets(0, 67, 0, 0);
//        c.weightx = 0.0;
        panel.add(firstName, c);

        firstN = new JTextField();
        firstN.setBackground(COLOR_TF);
        firstN.setPreferredSize(new Dimension(150,25));
        firstN.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 0, 25);
//        c.weightx = 0.0;
        panel.add(firstN, c);

        //second name
        secondName = new JLabel("Second Name");
        secondName.setFont(new Font("serif", Font.BOLD, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,50,0,0);
//        c.weightx = 0.0;
        panel.add(secondName, c);

        surN = new JTextField();
        surN.setBackground(COLOR_TF);
        surN.setPreferredSize(new Dimension(150,25));
        surN.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5, 10, 0, 25);
//        c.weightx = 0.0;
        panel.add(surN, c);

        //user login
        userName = new JLabel("User Name");
        userName.setFont(new Font("serif", Font.BOLD, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5,65,0,0);
//        c.weightx = 0.0;
        panel.add(userName, c);

        userN = new JTextField();
        userN.setBackground(COLOR_TF);
        userN.setPreferredSize(new Dimension(150,25));
        userN.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5, 10, 0, 25);
//        c.weightx = 0.0;
        panel.add(userN, c);

        // user password
        userPassword = new JLabel("Password");
        userPassword.setFont(new Font("serif", Font.BOLD, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5,77,0,0);
//        c.weightx = 0.0;
        panel.add(userPassword, c);

        userP = new JPasswordField();
        userP.setBackground(COLOR_TF);
        userP.setPreferredSize(new Dimension(150,25));
        userP.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_L_TF));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5, 10, 0, 25);
//        c.weightx = 0.0;
        panel.add(userP, c);

        //register
        register = new JButton("Register");
        register.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_BUTTON));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(20,50,0,0);
//        c.weightx = 0.0;
        panel.add(register, c);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstN.getText().length()>2 && surN.getText().length()>2 && userN.getText().length()>2 && !userP.getText().isEmpty())
                {
                    name = firstN.getText();
                    surname = surN.getText();
                    user = userN.getText();
                    pass = userP.getText();
//mySql
                    try
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shipsdb?useSSL=false", "root", "root");
                        if(!new Driver().checkIfExist(con, "login", user))
                        {
                            con.close();
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shipsdb?useSSL=false", "root", "root");
                            System.out.println("THIS IS HAPPENING!!!");


                            new Driver().addUser(conn, name, surname, user, pass);
                        }
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                    /** TODO
                     * Remove this JPanel and Open LogIn JPanel
                     * Add (email)...
                     * Check (if email exist go to login)...
                     *
                     */


                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Fill in all fields!","ERROR!",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //cancel
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_BUTTON));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(20,15,0,25);
//        c.weightx = 0;
        panel.add(cancel,c);

        //have an account? - LogIn
        JLabel haveAcc = new JLabel("Have an Account?");
        haveAcc.setFont(new Font("serif", Font.BOLD, SIZE_FONT_BUTTON));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(5,55,0,0);
        panel.add(haveAcc,c);

        //goToLogin
        goToLogin = new JButton("LogIn");
        goToLogin.setFont(new Font("serif", Font.PLAIN, SIZE_FONT_BUTTON));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(5,15,0,25);
//        c.weightx = 0.0;
        panel.add(goToLogin, c);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

}
