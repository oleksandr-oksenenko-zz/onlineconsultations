$(window).load(function () {
        ChatPoller.startPollingForChat(function (chatInfo) {
            if (chatInfo.isAnonymInChat == true) {
                EventHandlersManager.addListeners();
                UIDisabler.enableUI();

                MessagePoller.startPollingForMessages(MessageDisplayer.displayMessages);

                ChatPoller.stopPollingForChat();

                ChatPoller.startPollingForChat(function (chatInfo) {
                    if (chatInfo.isAnonymInChat == false) {
                        MessagePoller.stopPollingForMessages();
                        UIDisabler.disableUI();
                        EventHandlersManager.removeListeners();

                        ChatPoller.stopPollingForChat();

                        $("#chatEndModalWindow").modal();

                        $("#btnPostMessage").removeAttr("disabled", "disabled");
                    }
                });
            }
        });
    }
);
