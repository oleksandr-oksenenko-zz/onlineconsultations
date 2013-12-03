function postMessage() {
	var messageBody = $("#message").val();
	$("#message").val("");
	$.ajax({
			url: "/chat",
			type: "post",
			data: {
				action: "postMessage",
				message: messageBody
			},
			dataType: "json",
			success: function (data) {
			},
			error: function (jqXHR, error, errorText) {
				console.log("error: " + error + " " + errorText);
				alert(errorText);
			}
		});
}

var lastMessageId = -1;
function pollForMessages() {
	$.ajax({
			url: "/chat",
			type: "post",
			data: {
				action: "pollForMessages",
				lastMessage: lastMessageId
			},
			dataType: "json",
			success: function (data) {
				if (data != null && data.length != 0) {
					for (var i = 0; i < data.length; i++) {
						addMessageToPage(data[i]);
					}
					lastMessageId = data[data.length - 1].id;
				}
				setTimeout(pollForMessages, 1000);
			},
			error: function (error, error, errorText) {
				alert(errorText);
			}
		});
}

function addMessageToPage(message) {
	var text = "";
	text += "<br>";
	if (message.author != null) {
		text += message.author.firstname + " " + message.author.lastname;
	} else {
		text += "anonym";
	}
	var date = new Date(message.timestamp);
	text += " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
	text += ": " + message.body;
	$("#messages").html($("#messages").html() + text);
}

function enableControls() {
	$("#message").prop("disabled", false);
	$("#btnPostMessage").prop("disabled", false);
	$("#btnEndChat").prop("disabled", false);
}