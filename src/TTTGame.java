import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;


public class TTTGame extends JPanel{
    private int boardWidth;
    private int boardHeight;
    private int tileSize = 200;
    private Player player1;
    private Player player2;
    private Player currentPLayer;
    private final Scanner scanner;

    //lijsten maken voor X en O
    private ArrayList<Integer> allPositions = new ArrayList<>();
    private ArrayList<Integer> xPositions = new ArrayList<>();
    private ArrayList<Integer> oPositions = new ArrayList<>();


    public TTTGame(int boardwidth, int boardheight) {
        this.boardWidth = boardwidth;
        this.boardHeight = boardheight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        player1 = new Player("player 1", 'X');
        player2 = new Player("player 2", 'O');
        currentPLayer = player1;
        scanner = new Scanner(System.in);
    }

    public void play(){
        while (! boardIsFull()){
            System.out.println("It's " + currentPLayer.name + "move:");
            int move = scanner.nextInt();
            if (move <= 0 || move > 9 || allPositions.contains(move)){
                System.out.println("invalid move");
                continue;
            }

            if (currentPLayer.symbol == 'X'){
                addX(move);
            }else{
                addO(move);
            }

            switchTurn();

        }
        System.out.println("game is over, board is full");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

        //alle X en O tekenen
        for (Integer x : xPositions){
            drawX(x, g);
        }

        for (Integer o : oPositions){
            drawO(o, g);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < boardHeight / tileSize; i++) {
            //lijnen tekenen
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize); //horizontale lijnen
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); //verticale lijnen
        }
    }

    // een x tekeken
    public void drawX(int i, Graphics g){
        g.setColor(Color.CYAN);

        // Calculate the x and y coordinates based on i
        int x = ((i -1) % 3) * tileSize;
        int y = ((i -1) / 3) * tileSize;

        // Draw the diagonal lines for the 'X'
        g.drawLine(x, y, x + tileSize, y + tileSize); // \ diagonal
        g.drawLine(x , y + tileSize,  x + tileSize, y); // / diagonal
    }

    // een o tekenen
    public void drawO(int i, Graphics g){
        g.setColor(Color.RED);
        int x = ((i -1) % 3) * tileSize;
        int y = ((i -1) / 3) * tileSize;

        g.drawOval(x, y , tileSize, tileSize);
    }

    public void addX(int position){
        xPositions.add(position);
        allPositions.add(position);
        repaint();
    }

    public void addO(int position){
        oPositions.add(position);
        allPositions.add(position);
        repaint();
    }

    public boolean boardIsFull(){
        return allPositions.size() >= 9;
    }

    private void switchTurn() {
        currentPLayer = (currentPLayer == player1) ? player2 : player1;
    }
}
