package Lesson_7;

// This file is a revision and correction of file WindowsMain.java after Home work of Lesson 7 check at the beginning of Lesson 8.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PrimaryWindowRevised extends JFrame {

    SecondaryWindowRevised secondaryWindow;
    JTextField jtfFIO;

    public PrimaryWindowRevised() {

        setTitle("Primary Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(2, 1));
        jtfFIO = new JTextField("Фамилия, Имя, Отчество");
        jtfFIO.setEditable(false);
        JButton button = new JButton("Заполнить");
        add(jtfFIO);
        add(button);
        secondaryWindow = new SecondaryWindowRevised(this);

        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        secondaryWindow.setVisible(true);
                        button.setEnabled(false);
                    }
                }
        );

        setBounds(100, 100, 400, 100);
        setVisible(true);
    }

    public void setTextFieldText(String txt) {
        jtfFIO.setText(txt);
    }

}

class SecondaryWindowRevised extends JFrame {
    PrimaryWindowRevised primaryWindow;

    public SecondaryWindowRevised(final PrimaryWindowRevised primaryWindow) {
        this.primaryWindow = primaryWindow;
        setTitle("Secondary Window");
        setResizable(false);
        setLayout(new GridLayout(4, 2));
        JLabel jlSurname = new JLabel("Введите фамилию");
        JTextField jtfSurname = new JTextField();
        JLabel jlName = new JLabel("Введите имя");
        JTextField jtfName = new JTextField();
        JLabel jlFartherName = new JLabel("Введите отчество");
        JTextField jtfFartherName = new JTextField();
        JLabel jlButtonOk = new JLabel("Теперь нажмите кнопку ОК");
        JButton buttonOk = new JButton("OK");

        add(jlSurname);
        add(jtfSurname);
        add(jlName);
        add(jtfName);
        add(jlFartherName);
        add(jtfFartherName);
        add(jlButtonOk);
        add(buttonOk);

        buttonOk.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        primaryWindow.setTextFieldText(jtfSurname.getText() + " " + jtfName.getText() + " " + jtfFartherName.getText());
                        dispose();
                    }
                }
        );

        setBounds(600, 100, 400, 200);
//        setVisible(true);
    }

}

public class WindowsMainRevised {
    public static void main(String[] args) {
        new PrimaryWindowRevised();
    }
}
