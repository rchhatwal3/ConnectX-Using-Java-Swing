package cpsc2150.connectX;
import java.util.*;
import java.lang.*;

/**
 * Ramneek Chhatwal
 * CPSC 2150 - 002
 * HW 5
 *
 * @invariant 0 <= row < MAX_ROWS
 * @invariant 0 <= col < MAX_COLS
 */
public class BoardPosition {
    private final int row;
    private final int col;

    /**
     * @param r - value for row position of cell in board.
     * @param c - value for column in board
     * @pre 0 <= r <= MAX_ROWS and 0 <= c <= MAX_COLS
     * @post sets values for row and col
     */
    public BoardPosition(int r, int c){
        row = r;
        col = c;
    }

    /**
     * @return int of the final value for x position (row) of the marker of the board
     * @pre none
     * @post getRow = row
     */
    public int getRow(){
        return row;
    }

    /**
     * @return int of the final value for the y position (col) of the marker on the board
     * @pre none
     * @post getCol = col
     */
    public int getCol(){
        return col;
    }

    /**
     * @param obj of class Object that will be an instance of the type that is calling function
     * @return true if equal. false if otherwise
     * @pre object exists
     * @post equals = thisRow == objRow && thisCol && objCol
     */
    @Override
    public boolean equals(Object obj) {
        int thisRow = this.getRow();
        int thisCol = this.getCol();

        if (!(obj instanceof BoardPosition)){
            return false;
        }

        BoardPosition tempObj = (BoardPosition)obj;

        int objRow = tempObj.getRow();
        int objCol = tempObj.getCol();

        if (thisRow == objRow && thisCol == objCol){
            return true;
        }
        return false;
    }

    /**
     * @return string representation of "row, column"
     * @pre object using function is of type BoardPosition
     * @post toString = "row, col"
     */
    @Override
    public String toString() {
        StringBuffer position = new StringBuffer();

        position.append(row);
        position.append(",");
        position.append(col);

        return position.toString();
    }
}
