$(window).on("load", function(){
    ChatPoller.startPollingForChat(function(chatInfo) {
        console.log(chatInfo);
        if (chatInfo.isAnonymInChat == false) {
            stopAllPolling();

            window.location = baseUrl + 'chat/end';
        }
    });
});