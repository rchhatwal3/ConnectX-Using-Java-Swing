package cpsc2150.connectX;

/**
 * Ramneek Chhatwal
 * CPSC 2150 - 002
 * HW 5
 */
public abstract class AbsGameBoard implements IGameBoard {
    /**
     * @return string representation of the gameboard
     * @pre IGameBoard constructor is called
     * @post toString() = (StringBuffer)game.toString()
     */
    @Override
    public String toString(){
        StringBuffer game = new StringBuffer();

        //making the header for the gameboard
        for (int i = 0; i < getNumColumns(); i++){
            if (i < 10) {
                game.append("| ");
                game.append(i);
            }
            else {
                game.append("|");
                game.append(i);
            }
        }

        game.append("|");
        game.append("\n");

        for (int i = getNumRows() - 1; i >= 0; i--){
            game.append("|");

            for (int j = 0; j < getNumColumns(); j++){
                BoardPosition board = new BoardPosition(i,j);
                game.append(whatsAtPos(board));
                game.append(" ");
                game.append("|");
            }
            game.append("\n");
        }
        return game.toString();
    }
}
