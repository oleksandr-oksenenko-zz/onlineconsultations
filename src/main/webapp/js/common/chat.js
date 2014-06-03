var MessagePoller = {
    intervalId: -1,
    lastMessageId: -1,

    startPollingForMessages: function(messageHandler) {
        MessagePoller.intervalId = setInterval(function() {
            $.ajax({
                url: baseUrl + "api/chat/messages",
                type: "GET",
                dataType: "json",
                data: {
                    lastMessageId: MessagePoller.lastMessageId
                },
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
    },

    stopPollingForMessages: function() {
        clearInterval(MessagePoller.intervalId);
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
            url: baseUrl + "api/chat/messages",
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

var UIDisabler = {
    sendMessageButton: $("#btnPostMessage"),
    endMessageButton: $("#btnEndChat"),
    messageTextArea: $("#message"),

    enableUI: function () {
        UIDisabler.sendMessageButton.removeAttr("disabled", "disabled");
        UIDisabler.endMessageButton.removeAttr("disabled", "disabled");
        UIDisabler.messageTextArea.removeAttr("disabled", "disabled");
    },

    disableUI: function () {
        UIDisabler.sendMessageButton.attr("disabled", "disabled");
        UIDisabler.endMessageButton.attr("disabled", "disabled");
        UIDisabler.messageTextArea.attr("disabled", "disabled");
    }
};

var EventHandlersManager = {
    messageTextArea: $("#message"),
    postMessageBtn: $("#btnPostMessage"),
    closeChatEndModalWindowBtn: $("#btnCloseChatEndModalWindow"),

    messageKeyPressListener: function (e) {
        if (e.ctrlKey && e.keyCode == 10) {
            MessageSender.sendMessage(readMessage());
        }
    },
    postMessageBtnClickListener: function (e) {
        MessageSender.sendMessage(readMessage());
    },
    closeChatEndModalWindowBtnClickListener: function () {
        EventHandlersManager.closeChatEndModalWindowBtn.hide();
    },

    addListeners: function() {
        EventHandlersManager.messageTextArea.bind(
            "keypress",
            EventHandlersManager.messageKeyPressListener
        );

        EventHandlersManager.postMessageBtn.bind(
            "click",
            EventHandlersManager.postMessageBtnClickListener
        );

        EventHandlersManager.closeChatEndModalWindowBtn.bind(
            "click",
            EventHandlersManager.closeChatEndModalWindowBtnClickListener
        );
    },

    removeListeners: function() {
        EventHandlersManager.messageTextArea.unbind(
            "keypress",
            EventHandlersManager.messageKeyPressListener
        );

        EventHandlersManager.postMessageBtn.unbind(
            "click",
            EventHandlersManager.postMessageBtnClickListener
        );

        EventHandlersManager.closeChatEndModalWindowBtn.unbind(
            "click",
            EventHandlersManager.closeChatEndModalWindowBtnClickListener
        );
    }
};

function readMessage() {
    var message = $("#message");
    var messageBody = message.val();
    message.val("");
    return messageBody;
}

