var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ec-serverinfo-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/topic/serverinfo', function (serverinfo) {
            addServers(JSON.parse(serverinfo.body).servers);
        });
		sendName();
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {});
}

function addServers(serverinfo) {
    console.log(serverinfo);
    for(let i = 0; i < serverinfo.length; i++){
        addServer(serverinfo[i]);
    }
}

function addServer(server){
    var serverDiv = document.createElement("div");
    serverDiv.className = "server";

    var headerDiv = document.createElement("div");
    headerDiv.className = "serverheader";

    headerDiv.insertAdjacentHTML("beforeend", '<img src="' + server.icon + '">');
    headerDiv.insertAdjacentHTML("beforeend", '<span class="servertitle">' + server.name.toUpperCase() + '</span>');

    var descDiv = document.createElement("div");
    descDiv.className = "serverdesc";

    descDiv.insertAdjacentHTML("beforeend", '<span class="serverip infoh">Adresse:</span>');
    descDiv.insertAdjacentHTML("beforeend", '<span class="serverip infod">' + 'epicclan.eu' + '</span>');

    descDiv.insertAdjacentHTML("beforeend", '<span class="serverstatus infoh">Status:</span>');
    descDiv.insertAdjacentHTML("beforeend", '<span class="serverstatus infod">' + server.status + '</span>');

    serverDiv.appendChild(headerDiv);
    serverDiv.appendChild(descDiv);

    document.getElementById("serverinfo").appendChild(serverDiv);
}

$(function () {
	connect();
});