var webSocket = new WebSocket("ws://localhost:8080/events");
webSocket.onopen = function () {

    webSocket.send("howdy");
};