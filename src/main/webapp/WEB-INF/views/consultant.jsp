<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <link rel="stylesheet" type="text/css"
        href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/consultant.js'/>"></script>
</head>

<body>
    <div class="container" style="margin: 10% auto">
        <h3 align="center">Waiting for user<span id="dots"></span></h3>
    </div>

    <c:import url="common/footer.jsp" />
</body>

</html>