$(window).load(function () {
        ChatPoller.startPollingForChat(function (chatInfo) {
            if (chatInfo.isConsultantInChat == true) {
                EventHandlersManager.addListeners();
                UIDisabler.enableUI();

                MessagePoller.startPollingForMessages(MessageDisplayer.displayMessages);

                ChatPoller.stopPollingForChat();

                ChatPoller.startPollingForChat(function (chatInfo) {
                    if (chatInfo.isConsultantInChat == false) {
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
