import java.awt.*;

public class Bouncer {
    // Setting instance variables and values if they are fixed
    private int positionX;
    private int positionY;
    private Color color = Color.cyan;
    private int width = 200;
    private int height = 20;

    public Bouncer(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Draws bouncer
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawRect(positionX, positionY, width,height);
        g.setColor(color);
        g.fillRect(positionX, positionY, width, height);
    }

    // If moving the bouncer left will not move it outside the X bounds of the game, move it left
    public void moveLeft(){
        if (! (positionX - 20 < 0)){
            positionX = positionX - 20;
        }

    }
    // If moving the bouncer right will not move it outside the X bounds of the game, move it right
    public void moveRight(){
        if (!(positionX + width + 20 > 800)){
            positionX = positionX + 20;
        }
    }

    // Checks if there is a collision between this bouncer and a parameter object of type Ball
    public boolean isCollision(Ball ball){
        if (ball.getX() >= positionX && ball.getX() <= (positionX + width) && ball.getY() + (ball.getRadius()*2)== positionY){
            return true;
        }
        return false;
    }
}
