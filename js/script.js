var webSocket = new WebSocket("ws://localhost:8080/events");
webSocket.onopen = function () {

    var playerAction = {};
    playerAction.player = "player1";
    playerAction.stack = 3;
    var playerActionJSON = JSON.stringify(playerAction);
    webSocket.send(playerActionJSON);

};

webSocket.onmessage = function (message) {
    console.log(message);
    console.log(JSON.parse(message.data));

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
    dragged = event.target;
// make it half transparent
    event.target.style.opacity = .9;
});

document.addEventListener("touchend", function(event) {
// prevent default action (open as link for some elements)
    event.preventDefault();
// move dragged elem to the selected drop target
    if (patt.test(event.target.className)) {
        event.target.style.background = "";
        event.target.className = 'dropzone10H';
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
        event.target.style.background = "";
        event.target.className = 'dropzone10H';
    }
}, false);