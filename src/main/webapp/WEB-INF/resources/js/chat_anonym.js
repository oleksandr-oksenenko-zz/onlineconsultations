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
					enableControls();
					pollForMessages();
				}
			}
		});
}

function endChat() {
	$.ajax({
		url: "/chat",
		type: "post",
		data: {
			action: "endChat"
		},
		dataType: "json",
		success: function (data) {
			window.location.replace("/");
		},
		error: function (jqXHR, errorStatus, errorText) {
			console.log("error: " + errorStatus + " " + errorText);
		}
	});
}

window.onload = function() {
	pollForConsultant();
}

window.onbeforeunload = endChat;