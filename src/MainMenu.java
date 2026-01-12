import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("2D Игра - Главное меню");
        menuFrame.setSize(400, 300);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.setBounds(120, 80, 150, 50);

        JButton exitButton = new JButton("Выход");
        exitButton.setBounds(120, 150, 150, 50);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose(); // Закрываем меню
                Game.main(null);    // Запускаем игру
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Выход из игры
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(newGameButton);
        panel.add(exitButton);
        menuFrame.add(panel);

        menuFrame.setVisible(true);
    }
}
