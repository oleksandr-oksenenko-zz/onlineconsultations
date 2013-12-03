<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css"
        href="/resources/bootstrap/3.0.0/css/bootstrap.min.css">
</head>

<body>
    <div class="container">
        <c:import url="header.jsp" />

        <c:forEach var="subject" items="${subjects}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="<c:url value='/subjects/${subject.id}'/>">${subject.name}</a>
                </div>
                <div class="panel-body">
                    <c:out value="${ subject.description }"></c:out>
                </div>
            </div>
        </c:forEach>

        <c:import url="footer.jsp" />
    </div>
</body>

</html>