package jettydemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.GameLogic;
import model.GameServerResponse;
import model.PlayerAction;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.rmi.Remote;
import java.util.LinkedList;
import java.util.List;

public class EventSocket extends WebSocketAdapter
{
    static List<RemoteEndpoint> remotes;

    public static void messageAllRemotes(GameServerResponse message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String response = objectMapper.writeValueAsString(message);
            for (RemoteEndpoint remoteEndpoint : remotes) {
                remoteEndpoint.sendString(response);
            }
        } catch (Exception ex) {
            System.out.println("failed to send message to all remotes");
        }
    }

    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        if (remotes == null) {
            remotes = new LinkedList<>();
        }
        remotes.add(getRemote());
        System.out.println("Socket Connected: " + sess);
        GameLogic.dealGame();
        while (!GameLogic.checkTable()) {
            System.out.println("game dead, redealing");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        GameServerResponse gameServerResponse = GameLogic.getCurrentState();
        try {
            String response = objectMapper.writeValueAsString(gameServerResponse);
            for (RemoteEndpoint remoteEndpoint : remotes) {
                remoteEndpoint.sendString(response);
            }
        } catch (Exception ex) {
            System.out.println("failed to send string to remote");
        }
    }


    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        if (remotes != null) {
            remotes.remove(getRemote());
        }
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
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
            gameServerResponse = GameLogic.playerMove(playerAction);
//            gameServerResponse = new GameServerResponse();
//            gameServerResponse.setPlayer("player1");
//            gameServerResponse.setHandSize(9);
//            List<GameBoardSpace> gameBoardSpaces = new ArrayList<>();
//            for (int i = 0; i < 8; i++) {
//                GameBoardSpace gameBoardSpace = new GameBoardSpace();
//                gameBoardSpace.setActive(true);
//                Card2 card = new Card2();
//                card.setSuit(Card2.SUIT.SPADES);
//                card.setRank(Card2.RANK.ACE);
//                gameBoardSpace.setCard2(card);
//                gameBoardSpaces.add(gameBoardSpace);
//            }
//            gameServerResponse.setGameBoard(gameBoardSpaces);

            try {
                response = objectMapper.writeValueAsString(gameServerResponse);
            } catch (Exception ex) {
                System.out.println("failed to convert gameServerResponse to JSON string");
            }
        }

        if (response != null) {
            try {
                for (RemoteEndpoint remoteEndpoint : remotes) {
                    remoteEndpoint.sendString(response);
                }
            } catch (Exception ex) {
                System.out.println("failed to send string to remote");
            }
        }

    }

}