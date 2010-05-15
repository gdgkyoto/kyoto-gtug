var dropbox0;
var dropbox1;



function init() {
#    window.addEventListener("dragenter", dragenter, true);
#    window.addEventListener("dragleave", dragleave, true);
    dropbox = document.getElementById("dropbox");
    dropbox.addEventListener("dragover", dragover, true);
    dropbox.addEventListener("drop", drop, true);
}

function dragenter(e) {
    dropbox.setAttribute("dragenter", true);
}

function dragleave(e) {
    dropbox.removeAttribute("dragenter");
}

function dragover(e) {
    e.preventDefault();
}

function drop(e) {
    var element = e.fromElement;
    var dt = e.dataTransfer;
    var files = dt.files;

    e.preventDefault();



    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        handleFile(file);
    }
}

function handleData(dt) {
    var bag = document.getElementById("bag");

    var txt = "";
    for (var i = 0; i < dt.types.length; i++) {
        txt += i + " (" + dt.types[i] + ") : " + dt.getData(dt.types[i]);
        txt += "\n";
    }

    function newUrl(url) {
        var div = document.createElement("div");
        var iframe = document.createElement("iframe");
        iframe.src = url;
        div.appendChild(iframe);
        bag.insertBefore(div, bag.firstChild);
    }

    function newImage(url) {
        var img = document.createElement("img");
        img.src = url;
        bag.insertBefore(img, bag.firstChild);
    }

    function newText(txt) {
        var p = document.createElement("p");
        p.appendChild(document.createTextNode(txt));
        bag.insertBefore(p, bag.firstChild);
    }


    // Plain text
    var txt = dt.getData("text/plain");
    if (txt) {
        newText(txt);
        return true;
    }
    return false;
}

function handleFile(file) {
    return true;
}

window.addEventListener("load", init, true);

