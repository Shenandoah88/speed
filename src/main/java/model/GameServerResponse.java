package model;

import java.util.List;

public class GameServerResponse {

    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    private String player;
    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }

    private int handSize;
    public int getHandSize() {
        return handSize;
    }
    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    private List<GameBoardSpace> gameBoard;
    public List<GameBoardSpace> getGameBoard() {
        return gameBoard;
    }
    public void setGameBoard(List<GameBoardSpace> gameBoard) {
        this.gameBoard = gameBoard;
    }
}
