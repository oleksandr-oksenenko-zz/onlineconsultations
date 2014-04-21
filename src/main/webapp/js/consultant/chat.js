$(window).on("load", function () {
    ChatPoller.startPollingForChat(function(chatId) {
        if (chatId != null && chatId == -1) {
            window.location = baseUrl + "consultant";
        }
    });
});
