package cpsc2150.connectX;
import java.util.*;
import java.lang.*;

/**
 * Ramneek Chhatwal
 * CPSC 2150 - 002
 * HW 5
 *
 * @invariant MIN_ROWS <= USERROWS < MAX_ROWS and
 *            MIN_COLS <= USERCOLS < MAX_COLS and
 *            MIN_AMT_TO_WIN <= AMT_FOR_WIN <= UserRows and
 *            MIN_AMT_TO_WIN <= AMT_FOR_WIN <= UserCols and
 *            MIN_AMT_TO_WIN <= AMT_FOR_WIN <= MAX_AMT_TO_WIN
 *
 * @Correspondence: UserRows = USERROWS and
 *                  UserCols = USERCOLS and
 *                  UserAmtToWin = AMT_FOR_WIN
 */
public class GameBoard extends AbsGameBoard{
    public final int AMT_FOR_WIN;
    public final int USERCOLS;
    public final int USERROWS;
    private char[][] board;

    /**
     * @param userRows is the amount of rows for the gameboard
     * @param userCols is the amount of columns for the gameboard
     * @param numMarkers is the amount of markers in a row for a win
     * @pre none
     * @post board array set to blank spaces
     */
    public GameBoard(int userRows, int userCols, int numMarkers){
        AMT_FOR_WIN = numMarkers;
        USERCOLS = userCols;
        USERROWS = userRows;
        board = new char[userRows][userCols];
            for (int i = 0; i < userRows; i++){
                for (int j = 0; j < userCols; j++){
                    board[i][j] = ' ';
                }
            }
    }

    public int getNumRows(){
        return USERROWS;
    }

    public int getNumColumns(){
        return USERCOLS;
    }

    public int getNumToWin(){
        return AMT_FOR_WIN;
    }

    public void placeToken(char p, int c){
        int row = 0;

        while (board[row][c] != ' '){
            row++;
        }
        board[row][c] = p;
    }

    public char whatsAtPos(BoardPosition pos) {
        int row = pos.getRow();
        int col = pos.getCol();

        return board[row][col];
    }
}
