import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 600;
        int boardHeight = 600;

        JFrame frame =new JFrame("TicTacToe");
        TTTGame game = new TTTGame(boardWidth, boardHeight);

        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.play();
    }
}