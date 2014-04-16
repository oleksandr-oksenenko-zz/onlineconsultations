function postMessage(baseUrl) {
	var messageBody = $("#message").val();
    var message = {
        id: -1,
        body: messageBody
    };
	$.ajax({
		url: baseUrl + '/chat/post_message',
		type: 'POST',
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

var lastMessageId = -1;
function pollForMessages(baseUrl, handler) {
    $.ajax({
        url: baseUrl + '/chat/poll_for_messages',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(lastMessageId),
        contentType: "application/json; charset=utf-8"
    })
        .done(function (data) {
            console.log("success");
            if (data.length != 0) {
                handler(data);
                lastMessageId = data[data.length - 1].id;
            }
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.log("error: " + textStatus + ", " + errorThrown);
        });
}

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