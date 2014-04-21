function pollForChat(handler) {
    $.ajax({
        url: baseUrl + 'chat/poll_for_chat',
        type: 'POST',
        dataType: 'json',
        data: {}
    })
        .done(function (data) {
            console.log("success: " + data.chatId);
            handler(data.chatId);
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.log("error: " + textStatus + ", " + errorThrown);
            handler(-1);
        });
}

var intervalId = -1;


