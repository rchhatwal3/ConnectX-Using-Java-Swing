package cpsc2150.connectX;

/**
 * Ramneek Chhatwal
 * CPSC 2150 - 002
 * HW 5
 *
 * @Initialization Ensures: UserRows x UserCols grid of blank spaces
 *
 * @Defines: UserRows: Z+ - represents the rows of the char array
 *           UserCols: Z+ - represents the columns of the char array
 *           UserAmtToWin: Z+ - represents the amount of markers in a row to win
 *
 * @Constraints:  MIN_ROWS <= UserRows < MAX_ROWS and
 *                MIN_COLS <= UserCols < MAX_COLS and
 *                MIN_AMT_TO_WIN <= UserAmtToWin <= UserRows and
 *                MIN_AMT_TO_WIN <= UserAmtToWin <= UserCols and
 *                MIN_AMT_TO_WIN <= UserAmtToWin <= MAX_AMT_TO_WIN
 */
public interface IGameBoard {
    int MAX_ROWS = 100;
    int MIN_ROWS = 3;
    int MAX_COLS = 100;
    int MIN_COLS = 3;
    int MAX_AMT_FOR_WIN = 25;
    int MIN_AMT_FOR_WIN = 3;

    /**
     * @return int representing number of rows in the char array
     * @pre none
     * @post getNumRows = UserRows
     */
    public int getNumRows();

    /**
     * @return int representing number of rows in the char array
     * @pre none
     * @post getNumColumns = UserCols
     */
    public int getNumColumns();

    /**
     * @return int representing number of markers in a row to result in a win
     * @pre none
     * @post getNumToWin() = UserAmtToWin
     */
    public int getNumToWin();
    /**
     * @param c is column that user wants to place marker in
     * @return true if column is free to have marker placed in
     * @pre none
     * @post checkIfFree = true iff whatsAtPos(board) == ' '
     */
    public default boolean checkIfFree(int c){
        BoardPosition board = new BoardPosition(getNumRows() - 1, c);
        if (whatsAtPos(board) == ' '){
            return true;
        }
        return false;
    }

    /**
     * @param c is column user placed marker
     * @return true if move caused a win; false if otherwise
     * @pre none
     * @post checkForWin = true iff checkHorizWin() || checkVertWin() || checkDiagWin() === true
     */
    public default boolean checkForWin(int c){
        char player;
        int row = getNumRows() - 1;

        for (int i = 0; i < getNumRows(); i++){
            BoardPosition board = new BoardPosition(i, c);
            if (whatsAtPos(board) == ' '){
                row--;
            }
        }
        BoardPosition board = new BoardPosition(row, c);
        player = whatsAtPos(board);

        //checking different win conditions
        if (checkHorizWin(board, player)){
            return true;
        }
        else if (checkVertWin(board, player)){
            return true;
        }
        else if (checkDiagWin(board, player)){
            return true;
        }
        return false;
    }

    /**
     * @param p is char of player marker -> i.e. 'X' for player 1, 'O' for player 2, etc.
     * @param c is the column that user wants to place marker
     * @pre none
     * @post marker is placed where user wants
     */
    public void placeToken(char p, int c);

    /**
     * @param pos is most recent position that was placed on board
     * @param p is char of player marker -> 'X' for player 1, 'O' for player 2
     * @return true if last marker caused a win in the row that it is in
     * @pre pos to be valid
     * @post checkHorizWin  = true iff board[row][i] == p for i in AMT_TO_WIN times
     */
    public default boolean checkHorizWin(BoardPosition pos, char p){
        int counter = 0;
        int includesMarker = 0;
        int lastPosCol = pos.getCol();
        int leftmostCheck = lastPosCol - getNumToWin();
        int rightmostCheck = lastPosCol + getNumToWin();
        int col;

        if (leftmostCheck < 0){
            leftmostCheck = 0;
        }

        if (rightmostCheck > getNumColumns()){
            rightmostCheck = getNumColumns();
        }

        for (int i = leftmostCheck; i < rightmostCheck; i++){
            BoardPosition board = new BoardPosition(pos.getRow(), i);
            if (whatsAtPos(board) == p){
                counter++;
            }
            else {
                counter = 0;
            }

            if (i == pos.getCol()){
                includesMarker = 1;
            }

            if (counter >= getNumToWin() && includesMarker == 1){
                return true;
            }
        }
        return false;
    }

    /**
     * @param pos is most recent position that was placed on board
     * @param p is char of player marker -> 'X' for player 1, 'O' for player 2
     * @return true if last marker caused a win in the row that it is in
     * @pre pos to be valid
     * @post checkVertWin = true iff board[i][col] == p for i in AMT_TO_WIN times
     */
    public default boolean checkVertWin(BoardPosition pos, char p){
        int counter = 0;
        int includesMarker = 0;
        int lastPosRow = pos.getRow();
        int bottommostCheck = lastPosRow - getNumToWin() - 1;
        int topmostCheck = lastPosRow + getNumToWin() - 1;
        int row;

        if (bottommostCheck < 0){
            bottommostCheck = 0;
        }

        if (topmostCheck > getNumRows()){
            topmostCheck = getNumRows();
        }

        for (int i = bottommostCheck; i < topmostCheck; i++){
            BoardPosition board = new BoardPosition(i, pos.getCol());
            if (whatsAtPos(board) == p){
                counter++;
            }
            else {
                counter = 0;
            }

            if (i == pos.getRow()){
                includesMarker = 1;
            }

            if (counter == getNumToWin() && includesMarker == 1){
                return true;
            }
        }
        return false;
    }

    /**
     * @param pos is most recent position that was placed on board
     * @param p is char of player marker -> 'X' for player 1, 'O' for player 2
     * @return true if last marker caused a win in the row that it is in
     * @pre pos to be valid
     * @post checkDiagWin = true iff board[i][j] == p for i and j in AMT_TO_WIN times
     */
    public default boolean checkDiagWin(BoardPosition pos, char p){
        int count = 0;
        int row = pos.getRow();
        int col = pos.getCol();
        int rCount = row;
        int cCount = col;

        int leftmostCheck = col - getNumToWin();
        int rightmostCheck = col + getNumToWin();

        int bottommostCheck = row - getNumToWin();
        int topmostCheck = row + getNumToWin();


        if (bottommostCheck < 0){
            bottommostCheck = 0;
        }
        if (topmostCheck > getNumRows()){
            topmostCheck = getNumRows();
        }
        if (leftmostCheck < 0){
            leftmostCheck = 0;
        }
        if (rightmostCheck > getNumColumns()){
            rightmostCheck = getNumColumns();
        }

        while (whatsAtPos(new BoardPosition(rCount, cCount)) == p){
            count++;
            rCount--;
            cCount--;
            if (rCount < bottommostCheck || cCount < leftmostCheck) break;
        }

        rCount = row;
        cCount = col;

        while (whatsAtPos(new BoardPosition(rCount, cCount)) == p){
            count++;
            rCount++;
            cCount++;
            if (rCount > topmostCheck - 1 || cCount > rightmostCheck - 1) break;
        }

        if (count - 1 >= getNumToWin()){
            return true;
        }

        count = 0;
        rCount = row;
        cCount = col;

        while (whatsAtPos(new BoardPosition(rCount, cCount)) == p){
            count++;
            rCount++;
            cCount--;
            if (rCount > topmostCheck - 1 || cCount < leftmostCheck) break;
        }

        rCount = row;
        cCount = col;

        while (whatsAtPos(new BoardPosition(rCount, cCount)) == p){
            count++;
            rCount--;
            cCount++;
            if (rCount < bottommostCheck || cCount > rightmostCheck - 1) break;
        }
        if (count - 1 >= getNumToWin()){
            return true;
        }

        return false;
    }

    /**
     * @param pos is row,column position to check what value is held
     * @return char of which marker is in the position
     * @pre board only holding 'X', 'O', or ' '
     * @post whatsAtPos = char at BoardPosition pos
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @param pos is row, column position to check if player is located
     * @param player is the char for the marker of the player
     * @return true if player is at the position given . false if not
     * @pre player is either 'X' or 'O'
     * @post isPlayerAtPosition = true iff whatsAtPos(pos) == player
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        if (whatsAtPos(pos) == player){
            return true;
        }
        return false;
    }

    /**
     * @return true if game resulted in tie, false if no tie
     * @pre board is not full
     * @post checkTie = true iff whatsAtPos(board) != ' ' for all rows and cols
     */
    public default boolean checkTie() {
        for (int i = getNumRows() - 1; i >= 0; i--) {
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition board = new BoardPosition(i, j);
                if (whatsAtPos(board) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}