import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JavaMenu extends JFrame {

    private JTextArea text;
    private JMenuItem dateTime;
    private JMenuItem writeItem;
    private JMenuItem changeColor;
    private JMenuItem exit;

    public JavaMenu() {
        super("CSC372 Module 3 Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create text area for text box
        setSize(600, 600);
        text = new JTextArea(20, 40);
        text.setLineWrap(true);

        // place text in scroll pane
        JScrollPane scroll = new JScrollPane(text);
        add(scroll);

        // main menu bar

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Buttons");

        dateTime = new JMenuItem("Print Time and Date");

        dateTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss\n").format(new Date());
                text.append("Current Date and Time: " + timestamp + "\n");
            }
        });

        writeItem = new JMenuItem("Write to log.txt");
        writeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(PrintWriter writer = new PrintWriter(new FileWriter("log.txt"))){
                    writer.write(text.getText());
                    text.append("Written to log\n");
                    writer.close();

                }catch(IOException ex){
                    text.append("Failed to write log\n");
                }
            }
        });

        changeColor = new JMenuItem("Change hue of green");
        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Random rand = new Random();
                float randomHue = 0.25f + (rand.nextFloat() * 0.15f);

                Color greenHue = Color.getHSBColor(randomHue, 1.0f, 1.0f);

                text.setBackground(greenHue);
                changeColor.setText("Changed to random hue: " + String.format("%.3f\n", randomHue));

            }
        });
        exit = new JMenuItem("Close program");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ){
                System.exit(0);
            }
        });

        menu.add(dateTime);
        menu.add(writeItem);
        menu.add(changeColor);
        menu.add(exit);

        menuBar.add(menu);
        setJMenuBar(menuBar);

    }

    public static void main(String[] args) {
        JavaMenu menu = new JavaMenu();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

}