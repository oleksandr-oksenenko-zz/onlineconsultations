var ChatPoller = {
    intervalId: -1,

    startPollingForChat: function(callback) {
        ChatPoller.intervalId = setInterval(function() {
            $.ajax({
                url: baseUrl + "chat/poll_for_chat",
                type: "POST",
                dataType: "json",
                data: {},
                success: function (chatInfo) {
                    callback(chatInfo.chatId);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error occurred while polling for chat");
                    console.log("Error: textStatus = " + textStatus + ", error thrown = " + errorThrown);
                }
            });
        }, 1000);
    },

    stopPollingForChat: function() {
        clearInterval(ChatPoller.intervalId);
    }
};

