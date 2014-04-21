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
    startWaitingForUsers: function() {
        $.ajax({
            url: baseUrl + "consultant/status",
            type: "POST",
            data: {
                status: ConsultantStatus.WAITING_FOR_USERS
            },
            success: function() {
                intervalId = setInterval(function () {
                    pollForChat(function (chatId) {
                        if (chatId != -1) {
                            window.location = baseUrl + "/chat";
                        }
                    })
                }, 1000);
            },
            error: function(a, b, c) {
                alert("Error while stopping waiting for users");
                console.log(a + ", " + b + ", " + c);
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
                clearInterval(intervalId);
            },
            error: function(a, b, c) {
                alert("Error while stopping waiting for users");
                console.log(a + ", " + b + ", " + c);
            }
        })
    }
};

var startPolling = function() {
    ButtonManipulator.disableBtn($("#startPollingBtn"));
    ButtonManipulator.enableBtn($("#stopPollingBtn"));

    RequestSender.startWaitingForUsers();
};

var stopPolling = function() {
    ButtonManipulator.disableBtn($("#stopPollingBtn"));
    ButtonManipulator.enableBtn($("#startPollingBtn"));

    RequestSender.stopWaitingForUsers();
};

$(window).on("load", function(){
    $("#startPollingBtn").click(startPolling);
    $("#stopPollingBtn").click(stopPolling);
});
$(window).on("beforeunload", stopPolling);
