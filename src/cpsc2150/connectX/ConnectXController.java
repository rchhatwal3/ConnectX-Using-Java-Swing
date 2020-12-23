package cpsc2150.connectX;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.*;
import java.lang.*;

/**
 *Ramneek Chhatwal
 * CPSC 2150 - 002
 * HW 5
 *
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameBoard curGame;


    //The screen that provides our view
    private ConnectXView screen;

    private boolean win;
    private boolean tie;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    private ArrayList<Character> possibleMarkers = new ArrayList<>();

    private ArrayList<Character> actualMarkers = new ArrayList<>();

    private int playerTurns;

    private int whosePlayerIndex;

   int numPlayers;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

        win = false;
        tie = false;

        //initializing List of possible markers
        possibleMarkers.add('X');
        possibleMarkers.add('O');
        possibleMarkers.add('A');
        possibleMarkers.add('K');
        possibleMarkers.add('M');
        possibleMarkers.add('Q');
        possibleMarkers.add('T');
        possibleMarkers.add('V');
        possibleMarkers.add('S');
        possibleMarkers.add('Z');

        //initializing List of actual markers
        for (int i = 0; i < numPlayers; i++){
            actualMarkers.add(possibleMarkers.get(i));
        }

        playerTurns = 0;
        whosePlayerIndex = 1;

    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
      screen.setMessage("");
      if (win || tie){
          newGame();
      }

      if (whosePlayerIndex == numPlayers){
          screen.setMessage("It is " + actualMarkers.get(0) + "\'s turn. ");
      }
      else {
          screen.setMessage("It is " + actualMarkers.get(whosePlayerIndex) + "\'s turn. ");
      }

      if (curGame.checkIfFree(col)) {
          curGame.placeToken(actualMarkers.get(playerTurns), col);
          int lastRow = lastRowPlaced(col);
          screen.setMarker(lastRow, col, actualMarkers.get(playerTurns));

          win = curGame.checkForWin(col);
          tie = curGame.checkTie();

          if (win) {
              screen.setMessage("Player " + actualMarkers.get(playerTurns) + " won!\nClick anywhere to play another game");
          }
          if (tie) {
              screen.setMessage("Game ended in a tie!\nClick anywhere to play another game");
          }

          if (playerTurns == numPlayers - 1) {
              playerTurns = 0;
          }
          else {
              playerTurns++;
          }

          if (whosePlayerIndex == numPlayers - 1){
              whosePlayerIndex = 0;
          }
          else {
              whosePlayerIndex++;
          }
      }
      else {
          screen.setMessage("Column is full!");
      }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }

    /**
     * This method will find the row of the last marker that was placed
     */
    private int lastRowPlaced(int col){
        int row = curGame.getNumRows() - 1;
        for (int i = 0; i < curGame.getNumRows(); i++){
            BoardPosition board = new BoardPosition(i, col);
            if (curGame.whatsAtPos(board) == ' '){
                row--;
            }
        }
        return row;
    }
}
