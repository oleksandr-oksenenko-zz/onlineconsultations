function endChat() {
	$.ajax({
		url: baseUrl + "/chat",
		type: "post",
		data: {
			endChat: ""
		},
		dataType: "json",
		success: function (data) {
			window.location.replace(baseUrl + "/consultant");
		},
		error: function (jqXHR, errorStatus, errorText) {
			console.log("error: " + errorStatus + " " + errorText);
		}
	});
}

window.onload = function() {
	enableControls();
	pollForMessages();
}

window.onbeforeunload = function() {
	endChat();
	endSession();
};