var MessagePoller = {
    intervalId: -1,
    lastMessageId: -1,

    startPollingForMessages: function(messageHandler) {
        intervalId = setInterval(function() {
            $.ajax({
                url: baseUrl + "chat/poll_for_messages",
                type: "POST",
                dataType: "json",
                data: JSON.stringify(lastMessageId),
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    if (data.length > 0) {
                        MessagePoller.lastMessageId = data[data.length - 1].id;
                        messageHandler(data);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert("Error while retrieving messages.");
                    console.log("Text status: " + textStatus + ", error thrown: " + errorThrown);
                }
            });
        }, 1000);
    }
};

var MessageSender = {
    sendMessage: function(messageBody) {
        var message = {
            id: -1,
            body: messageBody
        };
        $.ajax({
            url: baseUrl + "chat/post_message",
            type: "POST",
            data: JSON.stringify(message),
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log("Message successfully sent");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error while sending a message.");
                console.log("Text status: " + textStatus + ", error thrown: " + errorThrown);
            }
        });
    }
};

var MessageDisplayer = {
    displayMessages: function(messages) {
        var textArea = $("#messages");
        for (var i = 0; i < messages.length; i++) {
            textArea.html(textArea.html() + "<br/>" + messages[i].body);
        }
    }
};

$(window).on("load", function() {
    $("#messages").click(function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            var messageBody = $("#messages").val();
            MessageSender.sendMessage(messageBody);
        }
    });
    MessagePoller.startPollingForMessages(MessageDisplayer.displayMessages);
});