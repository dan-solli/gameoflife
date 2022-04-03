package com.sollisar.app;

/**
 * @author Dan Solli
 * @author dan.solli@gmail.com
 * @version 1.0
 */
public class Main {
    
    /** The Game of Life, as interpreted by @author
     * @param args[] Args to this application are ignored.
     */
    public static void main(final String args[]) {
        final int boardSize = 300;

        RuleEngine rules = new RuleEngine(true);
        GameBoard board = new GameBoard(boardSize);
//        DisplayEngineConsole display = new DisplayEngineConsole();
        DisplayEngineSwing display = new DisplayEngineSwing(boardSize);

        board.randomizeBoard(25);

        // Main game loop
        boolean running = true;

        while (running) {

            display.clearDrawingArea();
            display.drawGameBoard(board);

            try {
                Thread.sleep(40); // 25 FPS, best case
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            board.tick(rules);
        }
    }
}
