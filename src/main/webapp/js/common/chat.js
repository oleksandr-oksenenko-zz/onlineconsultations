var MessagePoller = {
    intervalId: -1,
    lastMessageId: -1,

    startPollingForMessages: function(messageHandler) {
        MessagePoller.intervalId = setInterval(function() {
            $.ajax({
                url: baseUrl + "chat/poll_for_messages",
                type: "POST",
                dataType: "json",
                data: JSON.stringify(MessagePoller.lastMessageId),
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    if (data.length > 0) {
                        MessagePoller.lastMessageId = data[data.length - 1].id;
                        messageHandler(data);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
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
            body: messageBody,
            author: "anonym"
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
    messagePattern: "<div class='panel panel-default condensed-panel'>" +
        "<div class='panel-heading condensed-panel-element'>%author%</div>" +
        "<div class='panel-body condensed-panel-element'>%messageBody%</div>" +
        "</div>"
    ,

    displayMessages: function(messages) {
        var textArea = $("#messages");
        var tmp = "";
        for (var i = 0; i < messages.length; i++) {
            var author = messages[i].author;
            var messageBody = messages[i].body;
            tmp += MessageDisplayer.messagePattern
                .replace("%author%", author)
                .replace("%messageBody%", messageBody);
        }
        textArea.html(textArea.html() + tmp);
    }
};

function readMessage() {
    var message = $("#message");
    var messageBody = message.val();
    message.val("");
    return messageBody;
}

function initEventHandlers() {
    $("#message").keypress(function (e) {
        if (e.ctrlKey && e.keyCode == 10) {
            MessageSender.sendMessage(readMessage());
        }
    });
    $("#btnPostMessage").click(function () {
        MessageSender.sendMessage(readMessage());
    });
}
$(window).on("load", function() {
    initEventHandlers();

    MessagePoller.startPollingForMessages(MessageDisplayer.displayMessages);
});