package ch.hslu.sw07.ballgame;

import java.awt.*;

public class BallGameDemo {
    private static Canvas canvas;

    BallGameDemo(){
        canvas = new Canvas("GAME", 60, 400, Color.BLUE);
        canvas.setVisible(true);

        Circle circle = new Circle(60, 50,50, "black");
        Thread thread = new Thread(circle);
        thread.start();
    }

    public static void main(final String[] args) {
        new BallGameDemo();
    }
}
