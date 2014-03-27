<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">

    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/chat.js'/>"></script>

    <script type="text/javascript">
        var baseUrl = '<c:url value="/"/>';
        setInterval(function () {
            pollForMessages(baseUrl, displayMessages);
        }, 1000);
    </script>

    <sec:authorize access="hasRole('ROLE_CONSULTANT')">
        <script type="text/javascript" src="<c:url value="/js/consultant.js"/>"></script>
        <script type="text/javascript">
            setInterval(function () {
                var baseUrl = '<c:url value="/"/>';

                pollForChat(baseUrl, function (chatId) {
                    if (chatId == -1) {
                        window.location = '<c:url value="/consultant"/>';
                    }
                });
            }, 1000);
        </script>
    </sec:authorize>

</head>

<body>
    <div class="container">
        <c:import url="common/header.jsp" />
        
        <div id="messages">
        
        </div>
        
        <form role="form">
            <div class="form-group">
                <label for="message">New message (<small>you can send messages using ctrl+enter</small>)</label>
                <textarea rows="3"
                    class="form-control"
                    id="message"
                    onkeypress="onEnterPress(event);"></textarea>
            </div>
            <button 
                type="button"
                class="btn btn-default"
                onclick="postMessage('<c:url value="/"/>');"
                id="btnPostMessage">
                Post a message
            </button>

            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <a href="<c:url value="/chat/end"/>">
                    <button
                            type="button"
                            class="btn btn-default"
                            id="btnEndChat">
                        End the chat
                    </button>
                </a>
            </sec:authorize>
        </form>

        <c:import url="common/footer.jsp" />
    </div>
</body>

</html>