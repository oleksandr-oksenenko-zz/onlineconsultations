<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head></head>

<body>
    <table>
        <tr><td>Name: </td><td>${subject.name}</td></tr>
        <tr><td>Description: </td><td>${subject.description}</td></tr>
    </table>
    <c:forEach var="subSubject" items="${ subject.subSubjects }">
        <p>${ subSubject.name }</p>
    </c:forEach>
</body>

</html>