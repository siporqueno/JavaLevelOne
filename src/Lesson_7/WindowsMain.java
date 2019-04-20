package Lesson_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PrimaryWindow extends JFrame {
    private static JTextField jtfFIO;

    public PrimaryWindow() {
        setTitle("Primary Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(2, 1));
        jtfFIO = new JTextField("Фамилия, Имя, Отчество");
        jtfFIO.setEditable(false);
        JButton button = new JButton("Заполнить");
        add(jtfFIO);
        add(button);

        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SecondaryWindow();
                    }
                }
        );

        setBounds(100, 100, 400, 100);
        setVisible(true);
    }

    public static void setTextFieldText(String txt) {
        jtfFIO.setText(txt);
    }

}

class SecondaryWindow extends JFrame {
    public SecondaryWindow() {
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
                        PrimaryWindow.setTextFieldText(jtfSurname.getText() + " " + jtfName.getText() + " " + jtfFartherName.getText());
                        dispose();
                    }
                }
        );

        setBounds(600, 100, 400, 200);
        setVisible(true);
    }

}

public class WindowsMain {
    public static void main(String[] args) {
        new PrimaryWindow();
    }
}