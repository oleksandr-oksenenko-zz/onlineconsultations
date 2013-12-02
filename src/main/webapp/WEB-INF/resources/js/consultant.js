function pollForChat() {
	$.ajax({
			url: "/consultant",
			type: "post",
			data: {
				action: "pollForChat"
			},
			dataType : "json",
			success: function (data) {
				if (data == null) {
					setTimeout(pollForChat, 1000);
				} else {
					$("#messages").html("new chat arrived: " + data);
					window.location.replace("/chat?chat=" + data);
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#messages").html(errorThrown);
			}
		});
}

window.onload = function () {
	pollForChat();
}