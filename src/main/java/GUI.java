import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private JPanel boardPanel;
    private JPanel controlPanel;
    private JPanel arrowPanel;
    private JTextField scoreLabel;
    private JTextField score;
    private JButton newGame;
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JButton[] cells;

    private Logic controller = new Logic();
//
    public GUI() {
        boardPanel = new JPanel(new GridLayout(4, 4));
        boardPanel.setPreferredSize(new Dimension(500, 500));

        cells = new JButton[16];

        for(int i = 0; i < 16; i++) {
            cells[i] = new JButton();
            cells[i].setFont(new Font("Serief", Font.BOLD, 35));
            boardPanel.add(cells[i]);
        }

        this.getContentPane().add(BorderLayout.WEST, boardPanel);

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(200, 500));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        scoreLabel = new JTextField("Счет");
        scoreLabel.setFont(new Font("Serief", Font.BOLD, 35));
        scoreLabel.setPreferredSize(new Dimension(200, 50));
        scoreLabel.setEditable(false);
        controlPanel.add(scoreLabel);

        score = new JTextField("0");
        score.setPreferredSize(new Dimension(200, 200));
        score.setFont(new Font("Serief", Font.BOLD, 30));
        score.setEditable(false);
        controlPanel.add(score);

        JPanel newButtonPanel = new JPanel();
        newGame = new JButton("Новая игра");
        newGame.setName("newGame");
        newGame.setPreferredSize(new Dimension(200, 70));
        newGame.addActionListener(new NewGameListener());
        newButtonPanel.add(newGame);
        controlPanel.add(newButtonPanel);


        arrowPanel = new JPanel(new GridLayout(2, 2));
        arrowPanel.setPreferredSize(new Dimension(100, 150));
        DirectionListener dListener = new DirectionListener();
        up = new JButton("Вверх");
        up.setName("up");
        up.addActionListener(dListener);
        down = new JButton("Вниз");
        down.setName("down");
        down.addActionListener(dListener);
        left = new JButton("Влево");
        left.setName("left");
        left.addActionListener(dListener);
        right = new JButton("Вправо");
        right.setName("right");
        right.addActionListener(dListener);

        arrowPanel.add(up);
        arrowPanel.add(down);
        arrowPanel.add(left);
        arrowPanel.add(right);


        controlPanel.add(arrowPanel);

        this.getContentPane().add(BorderLayout.EAST, controlPanel);

        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void refreshUI() {
        int[][] board = controller.getBoard();
        for(int i = 0; i < 16; i++) {
            if(board[i / 4][i % 4] == 0) {
                cells[i].setText("");
            } else {
                int value = board[i / 4][i % 4];
                cells[i].setText(Integer.toString(value));
            }
        }

        score.setText(Integer.toString(controller.getScore()));
    }

    private void gameOver() {
        cells[0].setText("*");
        cells[1].setText("*");
        cells[2].setText("*");
        cells[3].setText("*");
        cells[4].setText("G");
        cells[5].setText("A");
        cells[6].setText("M");
        cells[7].setText("E");
        cells[8].setText("O");
        cells[9].setText("V");
        cells[10].setText("E");
        cells[11].setText("R");
        cells[12].setText("*");
        cells[13].setText("*");
        cells[14].setText("*");
        cells[15].setText("*");
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.pack();
        gui.setVisible(true);
    }



    class DirectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(up)) {
                controller.move(1);
            } else if(e.getSource().equals(down)) {
                controller.move(2);
            } else if(e.getSource().equals(left)) {
                controller.move(3);
            } else if(e.getSource().equals(right)) {
                controller.move(4);
            }

            if(!controller.isOver()) {
                refreshUI();
            } else {
                gameOver();
            }
        }
    }

    class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.newGame();
            refreshUI();
        }
    }
}
