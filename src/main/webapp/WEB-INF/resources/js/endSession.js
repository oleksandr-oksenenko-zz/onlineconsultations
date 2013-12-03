function endSession() {
	$.ajax({
		url: "/logout",
		type: "get",
		data: {},
		dataType : "json",
		success: function (data) {},
		error: function(jqXHR, textStatus, errorThrown) {
			$("#messages").html(errorThrown);
		}
	});
}