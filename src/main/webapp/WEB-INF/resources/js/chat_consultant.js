function endChat() {
	$.ajax({
		url: "/chat",
		type: "post",
		data: {
			action: "endChat"
		},
		dataType: "json",
		success: function (data) {
			window.location.replace("/consultant");
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