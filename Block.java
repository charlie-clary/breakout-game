import java.awt.*;

public class Block {
    // Setting instance variables and values if they are fixed
    private int positionX;
    private int positionY;
    private Color color;
    private int height = 30;
    private int width = 100;
    private boolean alive;


    public Block(int positionX, int positionY, Color color, boolean alive){
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        this.alive = alive;
    }

    // Checks if block is alive (in other words, visible)
    public boolean isAlive(){
        if (alive){
            return true;
        }
        return false;
    }

    // Checks collision between this block and parameter object of type Ball
    public boolean isCollision(Ball ball){
        if (ball.getX() >= positionX && ball.getX() <= (positionX + width) && ball.getY() + (ball.getRadius()*2) > positionY && ball.getY() < positionY + height){
            return true;
        }
        return false;
    }

    // Sets block to be dead (not visible)
    public void setDead(){
        alive = false;
    }

    // Draws block
    public void draw(Graphics g){
        if (alive){
            g.setColor(Color.black);
            g.drawRect(positionX, positionY, width,height);
            g.setColor(color);
            g.fillRect(positionX, positionY, width, height);
        }

    }
    public int getX(){
        return positionX;
    }
    public int getWidth(){
        return width;
    }
}
