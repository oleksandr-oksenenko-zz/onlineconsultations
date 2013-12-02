<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <script type="text/javascript" src="/resources/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="/js/chat.js"></script>
</head>

<body>
    <div id="messages">
        
    </div>
    <input type="text" name="message"/>
    <input type="button" onclick="javascript:postMessage()"/>
</body>

</html>