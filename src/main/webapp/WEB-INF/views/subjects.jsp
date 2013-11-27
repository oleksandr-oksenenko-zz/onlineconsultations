<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head></head>

<body>
    <c:forEach var="subject" items="${subjects}">
        <a href="<c:url value='/subjects/${subject.id}' />">${subject.name}</a>
    </c:forEach>
</body>

</html>