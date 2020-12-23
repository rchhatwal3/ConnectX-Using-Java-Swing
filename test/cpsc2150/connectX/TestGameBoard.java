package cpsc2150.connectX;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestGameBoard {
    private IGameBoard IGameBoardFactory(int r, int c, int num){
        return new GameBoard(r, c, num);
    }

    private String makeString(char[][] arr){
        StringBuffer expectedString = new StringBuffer();

        //making the header for the expected game board
        for (int i = 0; i < arr.length; i++){
            if (i < 10) {
                expectedString.append("| ");
                expectedString.append(i);
            }
            else {
                expectedString.append("|");
                expectedString.append(i);
            }
        }

        expectedString.append("|");
        expectedString.append("\n");

        for (int i = 0; i < arr.length; i++){
            expectedString.append("|");

            for (int j = 0; j < arr.length; j++){
                expectedString.append(arr[i][j]);
                expectedString.append(" ");
                expectedString.append("|");
            }
            expectedString.append("\n");
        }
        return expectedString.toString();
    }

    @Test
    public void testConstructor_not_extreme_inputs(){
        int expectedRows = 12;
        int expectedCols = 12;
        int expectedNumToWin = 4;

        IGameBoard game = IGameBoardFactory(12, 12, 4);

        //checking rows
        assertEquals(expectedRows, game.getNumRows());

        //checking cols
        assertEquals(expectedCols, game.getNumColumns());

        //checking amt for win
        assertEquals(expectedNumToWin, game.getNumToWin());
    }

    @Test
    public void testConstructor_maximum_inputs(){
        int expectedRows = IGameBoard.MAX_ROWS;
        int expectedCols = IGameBoard.MAX_COLS;
        int expectedNumToWin = IGameBoard.MAX_AMT_FOR_WIN;

        IGameBoard game = IGameBoardFactory(IGameBoard.MAX_ROWS, IGameBoard.MAX_COLS, IGameBoard.MAX_AMT_FOR_WIN);

        //checking rows
        assertEquals(expectedRows, game.getNumRows());

        //checking cols
        assertEquals(expectedCols, game.getNumColumns());

        //checking amt for win
        assertEquals(expectedNumToWin, game.getNumToWin());
    }

    @Test
    public void testConstructor_minimum_inputs(){
        int expectedRows = IGameBoard.MIN_ROWS;
        int expectedCols = IGameBoard.MIN_COLS;
        int expectedNumToWin = IGameBoard.MIN_AMT_FOR_WIN;

        IGameBoard game = IGameBoardFactory(IGameBoard.MIN_ROWS, IGameBoard.MIN_COLS, IGameBoard.MIN_AMT_FOR_WIN);

        //checking rows
        assertEquals(expectedRows, game.getNumRows());

        //checking cols
        assertEquals(expectedCols, game.getNumColumns());

        //checking amt for win
        assertEquals(expectedNumToWin, game.getNumToWin());
    }

    @Test
    public void testCheckIfFree_middle_of_column(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', 'O', ' ', ' '},
                {'O', 'X', 'O', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckIfFree = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('X', 1);
        game.placeToken('O', 2);
        game.placeToken('X', 0);
        game.placeToken('O', 1);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckIfFree, game.checkIfFree(0));
    }

    @Test
    public void testCheckIfFree_beginning_of_column(){
        char[][] temp = {
                {' ',' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckIfFree = true;

        IGameBoard game = IGameBoardFactory(4,4,3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckIfFree, game.checkIfFree(1));
    }

    @Test
    public void testCheckIfFree_column_already_full(){
        char[][] temp = {
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckIfFree = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckIfFree, game.checkIfFree(0));
    }

    @Test
    public void testCheckHorizWin_win_last_marker_right(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', 'X', 'X', 'X'},
                {'O', 'X', 'O', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckHorizWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('X', 1);
        game.placeToken('O', 2);
        game.placeToken('O', 3);
        game.placeToken('X', 1);
        game.placeToken('X', 2);
        game.placeToken('X', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckHorizWin, game.checkHorizWin(new BoardPosition(1,3), 'X'));
    }

    @Test
    public void testCheckHorizWin_win_last_marker_left(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'O'},
                {'X', 'X', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckHorizWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('X', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckHorizWin, game.checkHorizWin(new BoardPosition(0,0), 'X'));
    }

    @Test
    public void testCheckHorizWin_win_last_marker_middle(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'O'},
                {'X', 'X', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckHorizWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('X', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckHorizWin, game.checkHorizWin(new BoardPosition(0,1), 'X'));
    }

    @Test
    public void testCheckHorizWin_noWin_last_marker_first_in_string(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckHorizWin = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckHorizWin, game.checkHorizWin(new BoardPosition(0,0), 'X'));
    }

    @Test
    public void testVertWin_win_last_marker_top(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'X', 'O', ' ', ' '},
                {'X', 'O', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckVertWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('X', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('O', 1);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckVertWin, game.checkVertWin(new BoardPosition(2,0), 'X'));
    }

    @Test
    public void testVertWin_noWin_last_marker_first_in_string(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', 'X', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckVertWin = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 1);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckVertWin, game.checkVertWin(new BoardPosition(0,1), 'X'));
    }

    @Test
    public void testVertWin_noWin_last_marker_last_in_non_consecutive_string(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckVertWin = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckVertWin, game.checkVertWin(new BoardPosition(2,0), 'X'));
    }

    @Test
    public void testVertWin_noWin_last_marker_makes_full_board(){
        char[][] temp = {
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckVertWin = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('X', 3);
        game.placeToken('X', 3);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckVertWin, game.checkVertWin(new BoardPosition(3,3), 'O'));
    }

    @Test
    public void testDiagWin_win_last_marker_right_mainDiag(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', 'X', ' '},
                {' ', 'X', 'O', ' '},
                {'X', 'O', 'O', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(2,2), 'X'));
    }

    @Test
    public void testDiagWin_win_last_marker_middle_mainDiag(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', 'X', ' '},
                {' ', 'X', 'O', ' '},
                {'X', 'O', 'O', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(1,1), 'X'));
    }

    @Test
    public void testDiagWin_win_last_marker_left_mainDiag(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', 'X', ' '},
                {' ', 'X', 'O', ' '},
                {'X', 'O', 'O', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(0,0), 'X'));
    }

    @Test
    public void testDiagWin_noWin_last_marker_right_in_two(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', 'X', ' ', ' '},
                {'X', 'O', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(1,1), 'X'));
    }

    @Test
    public void testDiagWin_win_last_marker_left_reverseDiag(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', 'X', ' ', ' '},
                {'O', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(2,0), 'X'));
    }

    @Test
    public void testDiagWin_win_last_marker_mid_reverseDiag(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', 'X', ' ', ' '},
                {'O', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(1,1), 'X'));
    }

    @Test
    public void testDiagWin_win_last_marker_right_reverseDiag(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', 'X', ' ', ' '},
                {'O', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckDiagWin = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckDiagWin, game.checkDiagWin(new BoardPosition(0,2), 'X'));
    }

    @Test
    public void testCheckTie_tie_full_board(){
        char[][] temp = {
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckTie = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('X', 3);
        game.placeToken('X', 3);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckTie, game.checkTie());
    }

    @Test
    public void testCheckTie_nonFull_board(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckTie = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckTie, game.checkTie());
    }

    @Test
    public void testCheckTie_fullRow(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckTie = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckTie, game.checkTie());
    }

    @Test
    public void testCheckTie_fullColumn(){
        char[][] temp = {
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedCheckTie = false;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedCheckTie, game.checkTie());
    }

    @Test
    public void testWhatsAtPos_blank_board(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        char expectedWhatsAtPos = ' ';

        IGameBoard game = IGameBoardFactory(4,4,3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedWhatsAtPos, game.whatsAtPos(new BoardPosition(1,1)));
    }

    @Test
    public void testWhatsAtPos_full_column(){
        char[][] temp = {
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        char expectedWhatsAtPos = 'X';

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedWhatsAtPos, game.whatsAtPos(new BoardPosition(1,0)));
    }

    @Test
    public void testWhatsAtPos_full_row(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        char expectedWhatsAtPos = 'O';

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedWhatsAtPos, game.whatsAtPos(new BoardPosition(0,1)));
    }

    @Test
    public void testWhatsAtPos_nonFull_col_row (){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', 'X', 'O', ' '},
                {'X', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);
        char expectedWhatsAtPos = 'X';

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedWhatsAtPos, game.whatsAtPos(new BoardPosition(1,1)));
    }

    @Test
    public void testWhatsAtPos_full_board(){
        char[][] temp = {
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        char expectedWhatsAtPos = 'X';

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('X', 3);
        game.placeToken('X', 3);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedWhatsAtPos, game.whatsAtPos(new BoardPosition(3,2)));
    }

    @Test
    public void testIsPlayerAtPos_empty_board(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedIsPlayerAtPos = false;

        IGameBoard game = IGameBoardFactory(4,4,3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedIsPlayerAtPos, game.isPlayerAtPos(new BoardPosition(2,2), 'X'));
    }

    @Test
    public void testIsPlayerAtPos_full_col(){
        char[][] temp = {
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '},
                {'X', ' ', ' ', ' '},
                {'O', ' ', ' ', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedIsPlayerAtPos = true;

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedIsPlayerAtPos, game.isPlayerAtPos(new BoardPosition(3,0), 'X'));
    }

    @Test
    public void testIsPlayerAtPos_full_row(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedIsPlayerAtPos = true;

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedIsPlayerAtPos, game.isPlayerAtPos(new BoardPosition(0,3), 'O'));
    }

    @Test
    public void testIsPlayerAtPos_nonFull_row_col(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', 'X', 'O', ' '},
                {'X', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);
        boolean expectedIsPlayerAtPos = true;

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedIsPlayerAtPos, game.isPlayerAtPos(new BoardPosition(1,1), 'X'));
    }

    @Test
    public void testIsPlayerAtPos_full_board(){
        char[][] temp = {
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);
        boolean expectedIsPlayerAtPos = true;

        IGameBoard game = IGameBoardFactory(4,4,3);
        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('X', 3);
        game.placeToken('X', 3);
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());

        //checking function
        assertEquals(expectedIsPlayerAtPos, game.isPlayerAtPos(new BoardPosition(3,3), 'O'));
    }

    @Test
    public void testPlaceToken_empty_board(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'X'}
        };
        String expectedBoard = makeString(temp);

        IGameBoard game = IGameBoardFactory(4,4,3);

        //function call to test
        game.placeToken('X', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());
    }

    @Test
    public void testPlaceToken_col_not_empty(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', 'X', ' '},
                {' ', ' ', 'O', ' '},
                {'X', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);

        //function call to test
        game.placeToken('X', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());
    }

    @Test
    public void testPlaceToken_make_row_full(){
        char[][] temp = {
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);

        //function call to test
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());
    }

    @Test
    public void testPlaceToken_make_col_full(){
        char[][] temp = {
                {' ', ' ', 'O', ' '},
                {' ', ' ', 'X', ' '},
                {' ', ' ', 'O', ' '},
                {'X', 'O', 'X', ' '}
        };
        String expectedBoard = makeString(temp);

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);

        //function call to test
        game.placeToken('O', 2);

        //checking board
        assertEquals(expectedBoard, game.toString());
    }

    @Test
    public void testPlaceToken_make_board_full(){
        char[][] temp = {
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'}
        };
        String expectedBoard = makeString(temp);

        IGameBoard game = IGameBoardFactory(4,4,3);

        game.placeToken('X', 0);
        game.placeToken('O', 0);
        game.placeToken('O', 0);
        game.placeToken('X', 0);
        game.placeToken('O', 1);
        game.placeToken('X', 1);
        game.placeToken('X', 1);
        game.placeToken('O', 1);
        game.placeToken('X', 2);
        game.placeToken('O', 2);
        game.placeToken('O', 2);
        game.placeToken('X', 2);
        game.placeToken('O', 3);
        game.placeToken('X', 3);
        game.placeToken('X', 3);

        //function call to test
        game.placeToken('O', 3);

        //checking board
        assertEquals(expectedBoard, game.toString());
    }
}
