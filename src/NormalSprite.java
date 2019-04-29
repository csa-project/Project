import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class NormalSprite extends Sprites {
    public static int price = 100;
    public static final int radius = 120;
    public static int projectileDelay = 1000;
    public static ArrayList<NormalSprite> monkeys = new ArrayList<NormalSprite>();

    private int currentDelay = 1000;
    public ArrayList<Dart> projectiles = new ArrayList<Dart>();

    public NormalSprite(int x, int y) {
        super(x, y);
    }

    public void shoot() {
        if (!BRunner.gamePhase.equals("game")) return;
        if (currentDelay >= projectileDelay) {
            for (int i = 0; i < BRunner.currentBloons.length; i ++) {
                if (BRunner.currentBloons[i].getCoordinates() == null) continue;

                int xCoordOfBloon = (BRunner.currentBloons[i].getCoordinates()[0] * BRunner.PATH_WIDTH) + (BRunner.PATH_WIDTH / 2);
                int yCoordOfBloon = (BRunner.currentBloons[i].getCoordinates()[1] * BRunner.PATH_WIDTH) + (BRunner.PATH_WIDTH / 2);

                // Using distance formula to see if bloon is within range
                if (Math.sqrt(Math.pow(xCoordOfBloon - (x + BRunner.PATH_WIDTH / 2), 2) + Math.pow(yCoordOfBloon - (y + BRunner.PATH_WIDTH / 2), 2)) < radius) {
                    // Initiate new projectile
                    projectiles.add(new Dart(x + (BRunner.PATH_WIDTH / 2), y + (BRunner.PATH_WIDTH / 2), xCoordOfBloon, yCoordOfBloon, 10));
                    currentDelay = 0;
                    return;
                }
            }
        } else {
            currentDelay += BloonsWindow.FPSDelay;
        }
    }

    public void projectileAction() {
        for (int p = 0; p < projectiles.size(); p ++) {
            projectiles.get(p).move();
            projectiles.get(p).popBloons();
            if (projectiles.get(p).reachedTarget()) {
                projectiles.remove(p);
            }
        }
    }

    public void drawProjectiles(Graphics g) {
        for (Dart p : projectiles) {
            p.draw(g);
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(127, 80, 10));
        g.fillOval(x + 5, y + 5, BRunner.PATH_WIDTH - 10, BRunner.PATH_WIDTH - 10);
        g.fillRect(x + (BRunner.PATH_WIDTH / 2) - 1, y + (BRunner.PATH_WIDTH / 2), 2, BRunner.PATH_WIDTH / 2);
        g.setColor(Color.RED);
        g.fillRect((int)(x + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 10)) / 5)), (int)(y + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 10)) / 4)), (int)((Math.sqrt(2) * (BRunner.PATH_WIDTH - 10))) / 2, 8);
        g.setColor(Color.WHITE);
        g.fillOval(x + (BRunner.PATH_WIDTH / 2) - 10, (int)(y + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
        g.fillOval(x + (BRunner.PATH_WIDTH / 2) + 10, (int)(y + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
    }

    public static void drawPreview(Graphics g, int x, int y) {
        g.setColor(new Color(127, 80, 10));
        g.fillOval(x + 5, y + 5, BRunner.PATH_WIDTH - 10, BRunner.PATH_WIDTH - 10);
        g.fillRect(x + (BRunner.PATH_WIDTH / 2) - 1, y + (BRunner.PATH_WIDTH / 2), 2, BRunner.PATH_WIDTH / 2);
        g.setColor(Color.RED);
        g.fillRect((int)(x + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 10)) / 5)), (int)(y + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 10)) / 4)), (int)((Math.sqrt(2) * (BRunner.PATH_WIDTH - 10))) / 2, 8);
        g.setColor(Color.WHITE);
        g.fillOval(x + (BRunner.PATH_WIDTH / 2) - 10, (int)(y + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
        g.fillOval(x + (BRunner.PATH_WIDTH / 2) + 10, (int)(y + (BRunner.PATH_WIDTH / 2) - ((Math.sqrt(2) * (BRunner.PATH_WIDTH - 30)) / 2)), 4, 4);
    }
}

