function pollForChat() {
	$.ajax({
			url: baseUrl + "/chat/poll_for_chat",
			type: "post",
			data: {
			},
			dataType : "json",
			success: function (data) {
				if (data == null) {
					setTimeout(pollForChat, 1000);
				} else {
					window.location.replace(baseUrl + "/chat?action=startChat");
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#messages").html(errorThrown);
			}
		});
}

function dots() {
	var dotsCount = 0;
	
	setInterval(function() {
		if (dotsCount < 3) {
			dotsCount++;
		} else {
			dotsCount = 0;
		}
		var dotsText = "";
		for (var i = 0; i < dotsCount; i++) {
			dotsText += ".";
		}
	
		$("#dots").html( dotsText );
	}, 1000);
}

window.onload = function () {
	pollForChat();
	dots();
}

window.onbeforeunload = endSession;