
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Panel  extends javax.swing.JPanel {
    Sudoku game;
    private Timer timer;
    private JButton nbtn = new JButton("New game");
    private static JTextField[][] boxes;
    private JPasswordField pass = new JPasswordField();
    private JLabel label = new JLabel("      Timer : 00 : 00 : 00");
    private JLabel passsLabel = new JLabel("           Your password :");
    private JPanel[][] paneles;
    private JPanel center, bPanel, levelPanel;
    private JButton nBtn, cBtn, eBtn, hardBtn, midBtn, easyBtn, slove, about,instructions;
    private int[][] temp = new int[9][9];
    private int[][] grid = new int[9][9];
    private int counter = 0;
    public static String yourName;
    public JTextField newtextfield() {
        JTextField j = new JTextField("");
        j.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        j.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        j.setHorizontalAlignment(JTextField.CENTER);
        /*-------------------mouse lisner----------------*/
        j.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.decode("#001a14")));
                    ((JTextField) e.getSource()).setBackground(Color.decode("#c2f0f0"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    ((JTextField) e.getSource()).setBackground(Color.white);
                }
            }
        });
        /*------------------------------------------------*/

        j.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setForeground(Color.decode("#0c4"));
                } else {
                    ((JTextField) e.getSource()).setForeground(Color.black);
                }
            }
        });
        return j;
    }

    public Panel() {
        initComponents();
        /*------------------------main Panel  -------------------------------------*/
        center = new JPanel();  //main panel
        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
        center.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(center);  //add main panel to frame 

        boxes = new JTextField[9][9];
        paneles = new JPanel[3][3];
        passsLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        passsLabel.setForeground(Color.black);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneles[i][j] = new JPanel();
                paneles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                paneles[i][j].setLayout(new GridLayout(3, 3));
                center.add(paneles[i][j]);
            }
        }
        /*------------------------text fildes in boxes-------------------------------------*/

        for (int n = 0; n < 9; n++) {
            for (int i = 0; i < 9; i++) {
                boxes[n][i] = newtextfield();
                int fm = (n + 1) / 3;
                if ((n + 1) % 3 > 0) {
                    fm++;
                }
                int cm = (i + 1) / 3;
                if ((i + 1) % 3 > 0) {
                    cm++;
                }
                paneles[fm - 1][cm - 1].add(boxes[n][i]);   //add box to panel 
            }
        }
        /*------------------------Panel for buttons -------------------------------------*/

        bPanel = new JPanel();
        bPanel.setBackground(Color.decode("#9fdfbf"));//decode("#AABFFF") is for blueish colour
        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4, true));
        bPanel.setLayout(new GridLayout(4, 3, 0, 20));

        /*------------------------Panel  for timer  -------------------------------------*/
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                label.setText(TimeFormat(counter));
                counter++;

            }
        };

        /*------------------------Panel for new game button -------------------------------------*/
        nBtn = new JButton("New Game");
        nbtn.setSize(20, 50);
        timer = new Timer(1000, action);
        nBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                counter = 0;
                timer.start();
                restgame();
                Sudoku.newGame();

            }
        });

        /*------------------------Panel for check answer button -------------------------------------*/
        cBtn = new JButton("Check Answer +20s");

        cBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!boxes[i][j].isEditable()) {
                            continue;
                        } else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
                            boxes[i][j].setBackground(Color.decode("#C0DCD9"));
                        } else if (boxes[i][j].getText().isEmpty()) {
                            boxes[i][j].setBackground(Color.WHITE);
                            continue;
                        } else {
                            boxes[i][j].setBackground(Color.red);
                        }
                    }
                }
                counter += 20;
            }
        });
        /*------------------------Panel for new Exit button -------------------------------------*/
        eBtn = new JButton("Exit");

        eBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*------------------------Panel for new Hard level button -------------------------------------*/
        easyBtn = new JButton("Easy");

        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(2);
                Sudoku.newGame();
            }
        });
        /*------------------------Panel for new medium level button -------------------------------------*/
        midBtn = new JButton("Medium");

        midBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(3);
                Sudoku.newGame();

            }
        });
        /*------------------------Panel for new Hard button -------------------------------------*/
        hardBtn = new JButton("Hard");

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(4);
                Sudoku.newGame();
            }
        });
        /*------------------------Panel for getting answers-------------------------------------*/

        slove = new JButton("Get Answers!! Requires Password");

        slove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (pass.getText().equals(yourName)) {
                    timer.stop();
                    counter = 0;
                    label.setText(TimeFormat(counter));
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            boxes[i][j].setText(String.valueOf(grid[i][j]));
                        }
                    }
                    pass.setText("");
                } else {
                    JOptionPane.showMessageDialog(center, "ooppss!!! Your password is incorrect");
                    pass.setText("");
                }
            }
        });
        /*------------------------Panel for new about button -------------------------------------*/
        about = new JButton("About");

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(center, "Modern Sudoku\n" +
                        "The modern Sudoku was most likely designed anonymously by Howard Garns, a 74-year-old retired architect and freelance puzzle constructor from " +
                        "\nConnersville, Indiana, and first published in 1979 by Dell Magazines as Number Place (the earliest known examples of modern Sudoku)." +
                        "\nGarns's name was always present on the list of contributors in issues of Dell Pencil Puzzles and Word Games that included Number Place," +
                        "\nand was always absent from issues that did not. He died in 1989 before getting a chance to see his creation as a worldwide phenomenon." +
                        "\n"+"\n"+"By taking the reference I have also developed this game.\n" +"Name : Raj Anand" +
                        "\n\n" +"In this game there are certain rules are also included:---->\n" +
                        "Rule 1: If you want to check the answer any time of a particular box then you must click on 'Show Answer' but it will cost you 20 seconds of your time." +
                        "\n" +
                        "Rule 2: If you want to see answer of whole sudoku then you must have to enter your name  that you have given at starting as your password." +
                        "\n" +
                        "Rule 3: After entering the password, you must click 'For answers!!!Password Required' button to get all boxes filled." +
                        "\n" +
                        "Rule 4: After getting all answers your timer will stop and when you again start then timer will also start." +
                        "\n" +
                        "Rule 5: You can start new game any time by clicking on 'New Game'." +
                        "\n" +
                        "Rule 6: You can select the difficulty level as easy , medium , hard." +
                        "\n" +
                        "Rule 7: Strictly enter single digit number only in each box.");
            }

        });
        /*------------------------Panel for new about button -------------------------------------*/
        pass.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ((JPasswordField) e.getSource()).setText("");
            }
        });
        /*-------------Button for the instructions about the game and understandings of buttons----------------------------*/
        instructions = new JButton("Instructions");

        instructions.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(center, "Sudoku Rules\n" +
                        "Sudoku rules are simple and straightforward. It is precisely their simplicity that makes finding the solution and solving these puzzles a true challenge.\n" +
                        "\n" +
                        "To play Sudoku, the player only needs to be familiar with the numbers from 1 to 9 and be able to think logically." +
                        "\n The goal of this game is clear: to fill and complete the grid using the numbers from 1 to 9. The challenging part lays on the restrictions imposed on the player to be able to fill the grid.\n" +
                        "\n" +
                        "Rule 1 - Each row must contain the numbers from 1 to 9, without repetitions\n" +
                        "The player must focus on filling each row of the grid while ensuring there are no duplicated numbers. The placement order of the digits is irrelevant.\n" +
                        " \n" +
                        "Every puzzle, regardless of the difficulty level, begins with allocated numbers on the grid. The player should use these numbers as clues to find which digits are missing in each row.\n" +
                        "\n" +
                        "Rule 2 - Each column must contain the numbers from 1 to 9, without repetitions\n" +
                        "The Sudoku rules for the columns on the grid are exactly the same as for the rows. The player must also fill these with the numbers from 1 to 9, making sure each digit occurs only once per column.\n" +
                        " \n" +
                        "The numbers allocated at the beginning of the puzzle work as clues to find which digits are missing in each column and their position.\n" +
                        "\n" +
                        "Rule 3 - The digits can only occur once per block (nonet)\n" +
                        "A regular 9 x 9 grid is divided into 9 smaller blocks of 3 x 3, also known as nonets. The numbers from 1 to 9 can only occur once per nonet.\n" +
                        " \n" +
                        "In practice, this means that the process of filling the rows and columns without duplicated digits finds inside each block another restriction to the numbers’ positioning.\n" +
                        "\n" +
                        "Rule 4 - The sum of every single row, column and nonet must equal 45\n" +
                        "To find out which numbers are missing from each row, column or block or if there are any duplicates, the player can simply count or flex their math skills and sum the numbers. " +
                        "\nWhen the digits occur only once," +
                        "\n the total of each row, column and group must be of 45.\n" +
                        "\n" +
                        "1+2+3+4+5+6+7+8+9= 45\n" +
                        "\n" +
                        "Other details to take into consideration\n" +
                        "1. Each puzzle has a unique solution\n" +
                        "Each Sudoku puzzle has only one possible solution that can only be achieved by following the Sudoku rules correctly.\n" +
                        "Multiple solutions only occur when the puzzle is poorly designed or, the most frequent reason, when the player makes a mistake in its resolution and a duplicate is hidden somewhere on the grid.\n" +
                        "\n" +
                        "2. Guessing is not allowed\n" +
                        "Trying to guess the solution for each cell is not allowed under Sudoku rules. These are logical number puzzles.\n" +
                        "The numbers allocated at the beginning of the game are the only clues the player needs to solve the grid.\n" +
                        "\n" +
                        "3. Notes and techniques\n" +
                        "Writing down the numbers that are candidates to each cell is allowed by Sudoku rules and even encouraged. These help the player keep track of their progress and keep their reasoning organized and clear.\n" +
                        "As the difficulty level of these puzzles increases, these notes also become essential to be able to apply the advanced solving techniques required to complete the grid.");
            }
        });

        /*------------------------add button Panel and butons to frame and panel -------------------------------------*/
        bPanel.add(easyBtn);
        bPanel.add(midBtn);
        bPanel.add(hardBtn);
        bPanel.add(nBtn);
        bPanel.add(instructions);
        bPanel.add(cBtn);
        bPanel.add(about);
        bPanel.add(passsLabel);
        bPanel.add(pass);
        bPanel.add(slove);
        bPanel.add(label);
        bPanel.add(eBtn);
        add(bPanel, "South");   //add button panel to frame 

    }

    public void setarray(int[][] grid, int[][] temp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.temp[i][j] = temp[i][j];
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public void setTextLable() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.temp[i][j] != 0) {
                    boxes[i][j].setText(String.valueOf(this.temp[i][j]));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.decode("#C0DCC0"));
                } else {
                    boxes[i][j].setText("");
                }
            }
        }
    }

    public static void restgame() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boxes[i][j].setForeground(Color.black);
                boxes[i][j].setEditable(true);
                boxes[i][j].setBackground(Color.WHITE);
            }
        }
    }

    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count - hours * 3600) / 60;
        int seconds = count - minutes * 60;

        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}