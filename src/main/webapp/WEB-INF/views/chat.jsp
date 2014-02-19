<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css"
        href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">

    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/chat.js'/>"></script>

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
                    onkeypress="javascript:onEnterPress(event);"></textarea>
            </div>
            <button 
                type="button"
                class="btn btn-default" 
                onclick="javascript:postMessage();" 
                id="btnPostMessage">
                Post a message
            </button>
            <button 
                type="button" 
                class="btn btn-default"
                onclick="javascript:endChat();" 
                id="btnEndChat">
                End the chat
            </button>
        </form>

        <c:import url="common/footer.jsp" />
    </div>
</body>

</html>