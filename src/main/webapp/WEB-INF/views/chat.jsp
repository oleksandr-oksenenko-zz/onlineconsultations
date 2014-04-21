<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript">
        var baseUrl = '<c:url value="/"/>';
    </script>

    <script type="text/javascript" src="<c:url value="/js/common/chat.js"/>"></script>

    <sec:authorize access="hasRole('ROLE_CONSULTANT')">
        <script type="text/javascript" src="<c:url value="/js/consultant/common/chat_poller.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/consultant/chat.js"/>"></script>
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
                    id="message"> </textarea>
            </div>
            <button type="button"
                    class="btn btn-default"
                    id="btnPostMessage">
                Post a message
            </button>

            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <a href="<c:url value="/chat/end"/>" id="btnEndChat" class="btn btn-default">
                    End the chat
                </a>
            </sec:authorize>
        </form>

        <c:import url="common/footer.jsp" />
    </div>
</body>

</html>