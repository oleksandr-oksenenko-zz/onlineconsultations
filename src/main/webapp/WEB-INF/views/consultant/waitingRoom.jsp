<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../common/js-include.jsp"/>
    <c:import url="../common/css-include.jsp"/>

    <script type="text/javascript" src="<c:url value='/js/common/chat_poller.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/consultant/waiting_room.js'/>"></script>
</head>

<body>
    <div class="container" style="margin: 0 auto">
        <div class="page-header">
            <ul class="nav nav-pills">
                <li class="active">
                    <a href="<c:url value='/consultant/waiting_room'/>">Waiting room</a>
                </li>
                <li>
                    <a href="<c:url value='/consultant/settings'/>">Settings</a>
                </li>
            </ul>
        </div>

        <div class="row">
            <div class="well col-md-3" style="float: none; margin: 0 auto">
                <button id="startPollingBtn" class="btn btn-block btn-primary">
                    Start waiting for users
                </button>
                <button id="stopPollingBtn" class="btn btn-block btn-default" disabled="disabled">
                    Stop waiting for users
                </button>
            </div>
        </div>

        <c:import url="../common/footer.jsp" />
    </div>

</body>

</html>