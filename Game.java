
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {
    // Setting instance variables and values if they are fixed
    private Bouncer bouncer;
    private Ball ball;
    private int width = 800;
    private int height= 800;
    private Block[][] blocklist = new Block [8][5];
    private int score = 0;


    // Constructor, creates and puts all objects needed for game in correct positions
    public Game(){
        this.setPreferredSize(new Dimension(width, height));
        this.addKeyListener(this);
        this.setFocusable(true);
        // Creating bouncer object
        bouncer = new Bouncer(300,750);
        // Creating ball object
        ball = new Ball(300,725);
        // Creating array of blocks
        for (int i=0;i< blocklist.length;i++){
            for (int j=0;j< blocklist[i].length;j++){
                Color color = Color.blue;
                if (j==0){
                    color = Color.red;
                }
                if (j==1){
                    color = Color.orange;
                }
                if (j==2){
                    color = Color.yellow;
                }
                if (j==3){
                    color = Color.green;
                }
                blocklist[i][j] = new Block(i * 100, 100 + (j*30), color, true);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Moves bouncer when a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // If key pressed is left arrow, move bouncer left
        if (code == 37){
            bouncer.moveLeft();
        }
        // If key pressed is right arrow move bouncer right
        if (code == 39){
            bouncer.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // Checks if there is a collision between the ball and the wall, and reverses the velocity of the ball if there is
    public void checkCollisionWall(){
        // If the ball is outside of the X bounds of the game, reverse its X velocity
        if (ball.getX() + (ball.getRadius()*2)>= 800 || ball.getX() <=0){
            ball.reverseX();
        }
        // If the ball is outside the Y bounds of the game, reverse its Y velocity
        if (ball.getY() <=0) {
            ball.reverseY();
        }
    }

    // Checks if there is a collision between the bouncer and the ball, and reverses the ball's Y velocity if there is
    public void checkCollisionBouncer(){
        if (bouncer.isCollision(ball)){
            ball.reverseY();
        }
    }

    // Checks if there is a collision between the ball and a block, and if there is reverses the velocity of the ball and sets the block to be dead
    public void checkCollisionBlock(){
        for (int i=0;i< blocklist.length;i++){
            for (int j=0;j<blocklist[i].length;j++){
                Block curr = blocklist[i][j];
                // If the block is alive and there is a collision with the ball
                if (curr.isAlive() && curr.isCollision(ball)){
                    // Increment score and set block to be dead
                    curr.setDead();
                    score = score+100;
                    // If the ball hits the block on the side, reverse the ball's X velocity
                    if ((ball.getX() + (ball.getRadius()*2)) == curr.getX() || ball.getX() == (curr.getX() + curr.getWidth())){
                        ball.reverseX();
                    }
                    // Otherwise, the ball has hit the top or bottom, so reverse it's Y velocity
                    else{
                        ball.reverseY();
                    }
                }
            }

        }
    }

    // Draws shapes in the graphics window
    public void drawShapes(Graphics g){
        bouncer.draw(g);
        ball.draw(g);
        // Iterates through blocklist and draws each block
        for (int i=0;i< blocklist.length;i++) {
            for (int j = 0; j < blocklist[i].length; j++) {
                blocklist[i][j].draw(g);
            }
        }
    }

    // Updates game by updating position of ball and checking if there are any collisions
    public void update(){
        ball.update();
        checkCollisionBouncer();
        checkCollisionWall();
        checkCollisionBlock();
    }

    // Run's the game, each iteration checks if there is a winner or loser, and then repaints the game and updates its values
    public void run() throws InterruptedException {
        while (true){
            checkWinner();
            checkLoser();
            repaint();
            update();
            Thread.sleep(5);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        this.drawShapes(g);
        g.setColor(Color.cyan);
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.drawString("SCORE: " + score,50, 65);
    }

    // Ends game and prints score if all blocks are dead, as that is how you win
    public void checkWinner(){
        int count = 0;
        for (int i=0;i<blocklist.length;i++){
            for (int j=0;j<blocklist[i].length;j++){
                if (blocklist[i][j].isAlive()){
                    count++;
                }
            }
        }
        if (count ==0){
            System.out.println("WINNER WINNER CHICKEN DINNER: All blocks have been destroyed");
            System.out.println("FINAL SCORE: " + score);
            System.exit(0);
        }
    }

    // If the ball's Y position is below the lower Y bound of the game, the player has lost because there is no way for the ball to hit any more blocks
    // Prints score and exits game
    public void checkLoser(){
        if (ball.getY() > 800){
            System.out.println("GAME OVER: Ball has fallen below bouncer");
            System.out.println("FINAL SCORE: " + score);
            System.exit(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Breakout Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game mainInstance = new Game();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
        mainInstance.run();
    }

}
