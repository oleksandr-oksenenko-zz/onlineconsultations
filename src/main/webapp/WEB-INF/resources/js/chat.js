function postMessage() {
	var messageBody = $("[name='message']").val();
	$("[name='message']").val("");
	$.ajax({
			url: "/chat",
			type: "post",
			data: {
				action: "postMessage",
				message: messageBody
			},
			success: function (data) {
				$("#messages").html( $("#messages").html() + "<br>" + messageBody);
			}
		});
}

function pollForConsultant() {
	$.ajax({
			url: "/chat",
			type: "post",
			data: {
				action: "pollForConsultant"
			},
			dataType: "json",
			success: function (data) {
				if (data == false) {
					setTimeout(pollForConsultant, 1000);
				} else {
					$("#messages").html("consultant in da thread");
				}
			}
		});
}

function pollForMessages() {
	var lastMessage = null;
	$.ajax({
			url: "/chat",
			type: "post",
			data: {
				action: "pollForMessages",
				lastMessage: lastMessage
			},
			success: function (data) {
				if (data == null) {
					setTimeout(pollForMessages, 1000);
				} else {
					for (var i = 0; i < data.size(); i++) {
						$("#messages").html( $("#messages").html() + "<br>" + data[i].body);
					}
				}
			}
		});
}

window.onload = function() {
	pollForConsultant();
}