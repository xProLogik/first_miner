package by.myminer.miner;

import by.myminer.miner.mines.Box;
import by.myminer.miner.mines.Coordinates;
import by.myminer.miner.mines.Game;
import by.myminer.miner.mines.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
public class Miner extends JFrame {

  private Game game;

  private JPanel panel;
  private JLabel label;

  private static final int COLS = 9;
  private static final int ROWS = 9;
  private static final int BOMBS = 10;
  private static final int IMG_SIZE = 30;

  public static void main(String[] args) {
    new Miner();
  }

  private Miner() {
    game = new Game(COLS, ROWS, BOMBS);
    game.start();
    setImage();
    initLabel();
    initPanel();
    initFrame();

  }

  private void initLabel() {
    label = new JLabel("Приветствую");
    add(label, BorderLayout.SOUTH);
  }

  private void initPanel() {
    panel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Coordinates coord : Ranges.getAllCoordinates()) {
          g.drawImage((Image) game.getBox(coord).image, coord.x * IMG_SIZE, coord.y * IMG_SIZE, IMG_SIZE, IMG_SIZE, this);
        }
      }
    };
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int x = e.getX() / IMG_SIZE;
        int y = e.getY() / IMG_SIZE;
        Coordinates coord = new Coordinates(x, y);
        if (e.getButton() == MouseEvent.BUTTON1)
          game.pressLeftButton(coord);
        if (e.getButton() == MouseEvent.BUTTON2)
          game.start();
        if (e.getButton() == MouseEvent.BUTTON3)
          game.pressRightButton(coord);
        label.setText(getMessage());
        panel.repaint();
      }
    });
    panel.setPreferredSize(new Dimension(
        Ranges.getSize().x * IMG_SIZE,
        Ranges.getSize().y * IMG_SIZE));
    add(panel);
  }

  private String getMessage() {
    switch (game.getState()) {
      case PLAYED:
        return "Подумай дважды...";
      case BOMBED:
        return "Биг Бада Бум!!!";
      case WINNER:
        return "!!!Мои Поздравления!!!";
      default:
        return "Приветствую";
    }
  }

  private void initFrame() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setIconImage(getImage("icon"));
    setTitle("Miner");
    setResizable(false);
    setVisible(true);
    pack();
    setLocationRelativeTo(null);
  }

  private void setImage() {
    for (Box box : Box.values()) {
      box.image = getImage(box.name().toLowerCase());
    }
  }

  private Image getImage(String name) {
    String filename = "img/" + name + ".png";
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(filename));
    return icon.getImage();
  }
}
