(function poll() {
	setTimeout(
		function pollForChat() {
			$.ajax({
				url: baseUrl + '/chat/poll_for_chat',
				type: 'POST',
				dataType: 'json',
				data: {},
			})
			.done(function(data) {
				console.log("success");
				if (data.chatId == -1) {
					poll();
				} else {
					window.location = baseUrl + '/chat/start';
				}
			})
			.fail(function(jqXHR, textStatus, errorThrown) {
				console.log("error: " + textStatus + ", " + errorThrown);
				alert("Error happened!");
			});
		}, 1000);
})();
