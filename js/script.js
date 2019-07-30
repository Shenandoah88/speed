var webSocket = new WebSocket("ws://localhost:8080/events");
webSocket.onopen = function () {

    webSocket.send("howdy");
};

var dragged;

/* events fired on the draggable target */
document.addEventListener("drag", function(event) {

}, false);

document.addEventListener("dragstart", function(event) {
// store a ref. on the dragged elem
    dragged = event.target;
// make it half transparent
    event.target.style.opacity = .25;
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

document.addEventListener("dragenter", function(event) {
// highlight potential drop target when the draggable element enters it
    if (event.target.className == "dropzone") {
        event.target.style.background = "limegreen";
    }

}, false);

document.addEventListener("dragleave", function(event) {
// reset background of potential drop target when the draggable element leaves it
    if (event.target.className == "dropzone") {
        event.target.style.background = "darkgreen";
    }

}, false);

//regular expression for testing dropzone
var patt = /dropzone/;

document.addEventListener("drop", function(event) {
// prevent default action (open as link for some elements)
    event.preventDefault();
// move dragged elem to the selected drop target
    if (patt.test(event.target.className)) {
        event.target.style.background = "";
        event.target.className = 'dropzone10H'
    }
}, false);
