function postMessage() {
	var messageBody = $("#message").val();
	var message = { body : messageBody};
	$.ajax({
		url: baseUrl + '/chat/post_message',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(message),
		contentType: "application/json; charset=utf-8"
	})
	.done(function() {
		console.log("success");
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		console.log("error: " + textStatus + ", " + errorThrown);
		alert("Error happened!");
	});
}

(function pollForMessages() {
    setTimeout(
        function() {
            var lastMessageId = -1;

            $.ajax({
                url: baseUrl + '/chat/poll_for_messages',
                type: 'POST',
                dataType: 'json',
                data: {
                    // TODO: add lastMessageId as a param
                },
            })
            .done(function(data) {
                console.log("success");
                if (data.length != 0) {
                    displayMessages(data);
                }

                pollForMessages();
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                console.log("error: " + textStatus + ", " + errorThrown);
                alert("Error happened!");
            });
        }, 1000
    );
})();

(function () {
    $("#btnPostMessage").attr("disabled", false);
    $("#message").attr("disabled", false);
})();

function onEnterPress(event) {
    if (event.keycode == 13) {
        postMessage();
    }
}

function displayMessages(messages) {
    var textArea = $("#messages");
    for (i = 0; i < messages.length; i++) {
        textArea.html(textArea.html() + "<br>" + messages[i].body)
    }
}