var webSocket = new WebSocket("ws://localhost:8080/events");
webSocket.onopen = function () {

    /*
    var playerAction = {};
    playerAction.player = "player1";
    playerAction.stack = 3;
    var playerActionJSON = JSON.stringify(playerAction);
    webSocket.send(playerActionJSON);
     */

};

webSocket.onmessage = function (message) {

    console.log(message);
    console.log(JSON.parse(message.data));

    var poop = JSON.parse(message.data);
    /*console.log(poop);
    console.log(poop.gameBoard);
    console.log(poop.gameBoard[0]);
    console.log(poop.gameBoard[0].card);
    console.log(poop.gameBoard[0].card.styleString);*/

    if(poop.message == "END1"){
        console.log("end1");
        document.getElementById("1win").innerText = "Player 1 Wins!";
    }
    else if(poop.message == "END2"){
        console.log("end2");
        document.getElementById("2win").innerText = "Player 2 Wins!";
    }
    else {
        for (var corn = 0; corn < 8; corn++) {
            var peanut = poop.gameBoard[corn].card.styleString;
            console.log(corn);
            console.log(peanut);
            document.getElementById(corn).className = "dropzone" + peanut;
        }
    }
};

var dragged;

//regular expression for testing dropzone
var patt = /dropzone/;

document.addEventListener("touchstart", function(event){
    // store a ref. on the dragged elem
    dragged = event.target;
// make it half transparent
    event.target.style.opacity = .9;
});

document.addEventListener("touchmove", function(event){
    // store a ref. on the dragged elem

// make it half transparent
    event.target.style.opacity = .9;
});


document.addEventListener("touchend", function(event) {
// prevent default action (open as link for some elements)
    event.preventDefault();
// move dragged elem to the selected drop target
    var changedTouch = event.changedTouches[0];
    var elem = document.elementFromPoint(changedTouch.clientX, changedTouch.clientY);

    if (dragged.className.indexOf("player") > -1 && parseInt(elem.id) > -1 && parseInt(elem.id) < 8) {

        var playerAction = {};
        playerAction.player = dragged.className;
        playerAction.stack = elem.id;
        var playerActionJSON = JSON.stringify(playerAction);
        webSocket.send(playerActionJSON);
    }
}, false);

/* events fired on the draggable target */
document.addEventListener("drag", function(event) {

}, false);

document.addEventListener("dragstart", function(event) {
// store a ref. on the dragged elem
    dragged = event.target;
// make it half transparent
    event.target.style.opacity = .9;
}, false);

document.addEventListener("dragend", function(event) {
// reset the transparency
    event.target.style.opacity = "";
}, false);

/* events fired on the drop targets */
document.addEventListener("dragover", function(event) {
// prevent default to allow drop
    event.preventDefault();
}, false);

document.addEventListener("drop", function(event) {
// prevent default action (open as link for some elements)
    event.preventDefault();
// move dragged elem to the selected drop target
    if (patt.test(event.target.className)) {

        if(dragged.className == "player1"){
            var playerAction = {};
            playerAction.player = "player1";
            playerAction.stack = event.target.id;
            var playerActionJSON = JSON.stringify(playerAction);
            webSocket.send(playerActionJSON);
        }
       else  if(dragged.className == "player2"){
            var playerAction = {};
            playerAction.player = "player2";
            playerAction.stack = event.target.id;
            var playerActionJSON = JSON.stringify(playerAction);
            webSocket.send(playerActionJSON);
        }
       else{
           console.log('Failed to drag');
        }
    }
}, false);