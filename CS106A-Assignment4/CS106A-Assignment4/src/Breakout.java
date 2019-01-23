import acm.graphics.*; // GOval, GRect, etc.
import acm.program.*; // GraphicsProgram
import acm.util.*; // RandomGenerator
import java.awt.*; // Color
import java.awt.event.*; // MouseEvent

public class Breakout extends BreakoutProgram {
    private GRect brick;
    private GRect paddle;
    private GOval ball;
    private double mouseX;
    private double vX;
    private double vY = VELOCITY_Y;
    private double ballX;
    private double ballY;
    private int nturns = NTURNS;
    private GLabel label;

    public void run() {
        // Set the window's title bar text
        setTitle("CS 106A Breakout");

        // Set the canvas size. In your code, remember to ALWAYS use getWidth()
        // and getHeight() to get the screen dimensions, not these constants!
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        buildBricks();
        createPaddle();
        createBall();
        dropBall();
        nturns--;
        label = new GLabel("Score: , " + "Turns: " + nturns);
        add(label);
    }
    /* 
     * This method will create the initial brick layout with a fixed number of rows,
     * columns, and separation between bricks, and the bricks are each centered in
     * the window. The remaining space on either side(offset) is equal.
     */
    private void buildBricks() {
        double x;
        double y;
        double xStart;

        for (int column = 0; column < NBRICK_COLUMNS; column++) {
            int pattern = 0;
            for (int row = 0; row < NBRICK_ROWS; row++) {
                xStart = 2 * ((BRICK_WIDTH * BRICK_SEP) * NBRICK_COLUMNS) / getWidth();
                x = xStart + column * (BRICK_WIDTH + BRICK_SEP);
                y = row * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET;
                brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                add(brick);

                if (pattern % 10 == 0 && pattern != 0) {
                    pattern = 0;
                }

                if (pattern < 2) {
                    brick.setColor(Color.RED);
                    brick.setFilled(true);
                }else if (pattern > 1 && pattern < 4) {
                    brick.setColor(Color.ORANGE);
                    brick.setFilled(true);
                } else if (pattern > 3 && pattern < 6) {
                    brick.setColor(Color.YELLOW);
                    brick.setFilled(true);
                } else if (pattern > 5 && pattern < 8) {
                    brick.setColor(Color.GREEN);
                    brick.setFilled(true);
                } else if (pattern > 7 && pattern < 10) {
                    brick.setColor(Color.CYAN);
                    brick.setFilled(true);
                }

                pattern++;

            }
        }
    }
    /* 
     * The purpose of this method is to generate the black rectangular paddle that
     * is represented by the GRect object and set to the designated paddle width and
     * height constants.
     */
    private void createPaddle() {
        paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
    }
    /*
     * This method's objective is to move the paddle in response to the user's movement
     * of the mouse within the window.
     */
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() - PADDLE_WIDTH / 2;
        double paddleY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        if (mouseX <= getWidth() - PADDLE_WIDTH && mouseX >= 0) {
            add(paddle, mouseX, paddleY);
        }
    }
    /*
     * This method will generate the black ball needed to play Breakout with a fixed
     * radius determined by a radius constant.
     */
    private GOval createBall() {
        ballX = getWidth() / 2 - BALL_RADIUS;
        ballY = getHeight() / 2 + BALL_RADIUS;
        GOval newBall = new GOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
        newBall.setFilled(true);
        return newBall;
    }
    /*
     * The purpose of this method is to set the ball in motion with a velocity(as a 
     * double) determined by a random generator. If the ball hits any colliders(that
     * are not the paddle, the score panel, or the sides of the window), those
     * colliders will disappear, and the score count is updated accordingly.
     */
    public void dropBall() {
        GObject collider;
        double paddleY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        ball = createBall();
        add(ball);
        waitForClick();
        boolean chance = RandomGenerator.getInstance().nextBoolean(.5);
        vX = RandomGenerator.getInstance().nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
        if (chance) {
            vX = -Math.abs(1);
        } else {
        	vY = -Math.abs(vY);
        }

        add(ball);
        while (true) {
            if (hasHitSide(ball)) {
                vX *= -Math.abs(1);
            } else if (hasHitSomethingElse(ball) != null || hasHitTop(ball)) {
                	collider = hasHitSomethingElse(ball);
                	vY *= -Math.abs(1);
                	if (collider != paddle) {
                		remove(collider);
                	}
                	if (collider == paddle) {
                		if (ball.getY()  > paddleY && ball.getY() < paddleY) {
                			vY *= -1;
                    	}
                	}
            }
            ball.move(vX, vY);
            pause(DELAY);
        }
    }
    /*
     * This method's objective is to check if the ball has hit some other collider
     * besides the bricks that were part of the initial brick layout.
     */
    private GObject hasHitSomethingElse (GOval oval) {
        double x = oval.getX();
        double y = oval.getY();
        GObject collider = null;
        double farSideX = x + BALL_RADIUS * 2;
        double farSideY = y + BALL_RADIUS * 2;
        GObject hit = getElementAt(x, y);
        GObject hit2 = getElementAt(farSideX, y);
        GObject hit3 = getElementAt(farSideX, farSideY);
        GObject hit4 = getElementAt(farSideX, y);
        if (hit != null){
            collider = hit;
        }
        if (hit2 != null){
            collider = hit2;
        }
        if (hit3 != null){
            collider = hit3;
        }
        if (hit4 != null){
            collider = hit4;
        }
        return collider;
    }
    /*
     * This method will check if the ball has hit any side of the ball(GOval) and
     * subsequently make sure that the ball stays within the window.
     * 
     */
    private boolean hasHitSide(GOval oval) {
        double leftX = oval.getX();
        double rightX = oval.getX() + BALL_RADIUS * 2;

        return rightX >= getWidth() || leftX <= 0;
    }
    /*
     * This method's objective is to ensure that when the ball hits the top of the
     * window, it will come down and not surpass the window boundaries.
     */
    private boolean hasHitTop(GOval oval) {
        double topY = oval.getY();
        return topY <= 0;
    }
}