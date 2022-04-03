package com.sollisar.app;

/** Class for displaying the Game of Life in a terminal window.  
 * 
 */
public class DisplayEngineConsole implements DisplayEngineInterface {
    private String lineSeparator = new String();

    /** Constructor for DisplayEngineConsole, sets the line separator */
    public DisplayEngineConsole() {
        lineSeparator = System.getProperty("line.separator");
    }

    
    /** Draws the board on the console. O represents alive entities.
     * @param board GameBoard object to be drawn.
     */
    @Override
    public void drawGameBoard(GameBoard board) {
        int boardSize = board.getBoardSize();
        StringBuffer sb = new StringBuffer(boardSize * boardSize + boardSize);

        for (int y = 0; y < boardSize; y++) {

            for (int x = 0; x < boardSize; x++) {
                if (board.getEntity(x, y)) {
                    sb.append("O");
                }
                else {
                    sb.append("-");
                }
            }
            sb.append(lineSeparator);
        }
        System.out.println(sb);
    }

    /** Clears the drawing area, tries to handle Windows vs Un*xen. */
    @Override
    public void clearDrawingArea() {
        try {
            String os = System.getProperty("os.name");
            ProcessBuilder exec;

            if (os.contains("Windows")) {
                exec = new ProcessBuilder("cmd", "/c", "cls");
            }
            else {
                exec = new ProcessBuilder("clear");
            }
            Process clearScreen = exec.inheritIO().start();
            clearScreen.waitFor();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }    
}
