$(window).on("load", function() {
    ChatPoller.startPollingForChat(function(chatInfo) {
        if (chatInfo.isConsultantInChat == false) {
            stopAllPolling();

            window.location = baseUrl + 'chat/end';
        }
    });
});