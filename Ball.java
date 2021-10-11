import java.awt.*;

public class Ball {
    // Setting instance variables and values if they are fixed
    private int radius = 12;
    private int positionX;
    private int positionY;
    private Color color = Color.white;
    private int velocityX = -1;
    private int velocityY = -1;

    public Ball(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Draws Ball
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawOval(positionX, positionY, radius*2, radius*2);
        g.setColor(color);
        g.fillOval(positionX, positionY, radius * 2,radius * 2);
    }
    // Updates position by adding x velocity to x position, and y velocity to y position
    public void update(){
        this.positionX = this.positionX + velocityX;
        this.positionY = this.positionY + velocityY;
    }

    public int getX(){
        return positionX;
    }

    public int getY(){
        return positionY;
    }


    public void reverseX(){
        velocityX = -velocityX;
    }

    public void reverseY(){
        velocityY = -velocityY;
    }

    public int getRadius(){
        return radius;
    }



}
