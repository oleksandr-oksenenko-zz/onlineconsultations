var ButtonManipulator = {
    enableBtn: function (btn) {
        btn.removeAttr("disabled", "disabled");
        btn.toggleClass("btn-default", false);
        btn.toggleClass("btn-primary", true);
    },

    disableBtn: function (btn) {
        btn.attr("disabled", "disabled");
        btn.toggleClass("btn-primary", false);
        btn.toggleClass("btn-default", true);
    }
};

var ConsultantStatus = {
    WAITING_FOR_USERS: "WAITING_FOR_USERS",
    NOT_WAITING_FOR_USERS: "NOT_WAITING_FOR_USERS"
};

var RequestSender = {
    intervalId: -1,

    startWaitingForUsers: function() {
        $.ajax({
            url: baseUrl + "consultant/status",
            type: "POST",
            data: {
                status: ConsultantStatus.WAITING_FOR_USERS
            },
            success: function() {
                RequestSender.intervalId = setInterval(function() {
                    ChatPoller.startPollingForChat(function(chatId) {
                        if (chatId != null && chatId != -1) {
                            console.log("Redirecting to chat page: " + baseUrl + "chat");
                            console.log("Chat id: " + chatId);
                            window.location = baseUrl + "chat";
                        }
                    });
                }, 1000);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error while stopping waiting for users");
                console.log(jqXHR + ", " + textStatus + ", " + errorThrown);
            }
        });
    },

    stopWaitingForUsers: function() {
        $.ajax({
            url: baseUrl + "consultant/status",
            type: "POST",
            data: {
                status: ConsultantStatus.NOT_WAITING_FOR_USERS
            },
            success: function() {
                clearInterval(RequestSender.intervalId);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error while stopping waiting for users");
                console.log(jqXHR + ", " + textStatus + ", " + errorThrown);
            }
        })
    }
};

var startPolling = function() {
    console.log("Starting polling...");
    ButtonManipulator.disableBtn($("#startPollingBtn"));
    ButtonManipulator.enableBtn($("#stopPollingBtn"));

    RequestSender.startWaitingForUsers();
};

var stopPolling = function() {
    console.log("Stopping polling...");
    ButtonManipulator.disableBtn($("#stopPollingBtn"));
    ButtonManipulator.enableBtn($("#startPollingBtn"));

    RequestSender.stopWaitingForUsers();
};

$(window).on("load", function(){
    $("#startPollingBtn").click(startPolling);
    $("#stopPollingBtn").click(stopPolling);
});
$(window).on("beforeunload", stopPolling);
