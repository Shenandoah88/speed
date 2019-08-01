package jettydemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Card;
import model.GameBoardSpace;
import model.GameServerResponse;
import model.PlayerAction;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventSocket extends WebSocketAdapter
{
    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("Socket Connected: " + sess);

    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        System.out.println("Received TEXT message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        PlayerAction playerAction = null;
        GameServerResponse gameServerResponse = null;
        String response = null;

        try {
            playerAction = objectMapper.readValue(message, PlayerAction.class);
        } catch (Exception ex) {
            System.out.println("failed to map string to PlayerAction");
        }

        if (playerAction != null) {
            //TODO replace this sample object with a real one built by GameLogic.java
            gameServerResponse = new GameServerResponse();
            gameServerResponse.setPlayer("player1");
            gameServerResponse.setHandSize(9);
            List<GameBoardSpace> gameBoardSpaces = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                GameBoardSpace gameBoardSpace = new GameBoardSpace();
                gameBoardSpace.setActive(true);
                Card card = new Card();
                card.setSuit(Card.SUIT.SPADES);
                card.setRank(Card.RANK.ACE);
                gameBoardSpace.setCard(card);
                gameBoardSpaces.add(gameBoardSpace);
            }
            gameServerResponse.setGameBoard(gameBoardSpaces);

            try {
                response = objectMapper.writeValueAsString(gameServerResponse);
            } catch (Exception ex) {
                System.out.println("failed to convert gameServerResponse to JSON string");
            }
        }

        if (response != null) {
            try {
                getRemote().sendString(response);
            } catch (Exception ex) {
                System.out.println("failed to send string to remote");
            }
        }

    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}