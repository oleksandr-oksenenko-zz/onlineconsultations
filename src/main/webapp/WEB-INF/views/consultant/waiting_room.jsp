<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/consultant.js'/>"></script>
    <script type="text/javascript">
        var intervalId = -1;

        var enableBtn = function(btn) {
            btn.removeAttr("disabled", "disabled");
            btn.toggleClass("btn-default", false);
            btn.toggleClass("btn-primary", true);
        }
        var disableBtn = function(btn) {
            btn.attr("disabled", "disabled");
            btn.toggleClass("btn-primary", false);
            btn.toggleClass("btn-default", true);
        }

        var startPolling = function() {
            var startPollingBtn = $("#startPollingBtn");
            var stopPollingBtn = $("#stopPollingBtn");

            disableBtn(startPollingBtn);
            enableBtn(stopPollingBtn);

            intervalId = setInterval(function () {
                var baseUrl = '<c:url value="/"/>';

                pollForChat(baseUrl, function (chatId) {
                    if (chatId != -1) {
                        window.location = '<c:url value="/chat"/>';
                    }
                });
            }, 1000)
        };

        var stopPolling = function() {
            var startPollingBtn = $("#startPollingBtn");
            var stopPollingBtn = $("#stopPollingBtn");

            disableBtn(stopPollingBtn);
            enableBtn(startPollingBtn);

            clearInterval(intervalId);
            intervalId = -1;
        };
    </script>
</head>

<body>
    <div class="container" style="margin: 0 auto">
        <div class="page-header">
            <ul class="nav nav-pills">
                <li class="active"><a href="<c:url value='/consultant/waiting_room'/>">Waiting room</a></li>
                <li><a href="<c:url value='/consultant/settings'/>">Settings</a></li>
            </ul>
        </div>

        <div class="row">
            <div class="well col-md-3" style="float: none; margin: 0 auto">
                <a href="javascript:startPolling();"
                    id="startPollingBtn"
                    class="btn btn-block btn-primary"
                >
                    Start waiting for users
                </a>
                <a href="javascript:stopPolling();"
                    id="stopPollingBtn"
                    class="btn btn-block btn-default"
                    disabled="disabled"
                >
                    Stop waiting for users
                </a>
            </div>
        </div>

        <c:import url="../common/footer.jsp" />
    </div>

</body>

</html>