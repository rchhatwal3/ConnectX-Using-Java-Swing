package cpsc2150.connectX;
import java.util.*;

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
public class GameBoardMem extends AbsGameBoard{
    public final int USERROWS;
    public final int USERCOLS;
    public final int AMT_FOR_WIN;
    private Map<Character, List<BoardPosition>> board;

    /**
     * @param userRows is the amount of rows in the gameboard
     * @param userCols is the amount of columns in the gameboard
     * @param amtToWin is the amount of markers in a row for a win
     */
    public GameBoardMem(int userRows, int userCols, int amtToWin){
        USERROWS = userRows;
        USERCOLS = userCols;
        AMT_FOR_WIN = amtToWin;
        board = new HashMap<Character, List<BoardPosition>>();
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
        //checking if there is a list for character key and adding if not
        if (!board.containsKey(p)){
            List<BoardPosition> tempList = new ArrayList<>();
            board.put(p, tempList);
        }
        //list to add to map with position
        int row = USERROWS - 1;
        BoardPosition pos;

        for (int i = 0; i < getNumRows(); i++){
            pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) == ' '){
                row--;
            }
        }

        pos = new BoardPosition(++row, c);
        board.get(p).add(pos);
    }

    public char whatsAtPos(BoardPosition pos) {
      for (Map.Entry<Character, List<BoardPosition>> e : board.entrySet()){
            if (isPlayerAtPos(pos, e.getKey())){
                return e.getKey();
            }
      }
        return ' ';

    }

    /**
     * @param pos is row, column position to check if player is located
     * @param player is the char for the marker of the player
     * @return boolean if position is in list in key-value pair
     * @pre map is initialized and key-value pair exists
     * @post isPlayerAtPos = board.get(player).contains(pos)
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (board.containsKey(player)) {
            if (board.get(player).contains(pos)) {
                return true;
            }
        }
        return false;
    }
}
