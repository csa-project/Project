import java.awt.Graphics;

public abstract class Sprites {
    protected int x;
    protected int y;

    public Sprites(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void shoot();
    public abstract void draw(Graphics g);
}

