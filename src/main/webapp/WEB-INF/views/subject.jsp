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
        <c:forEach var="subSubjectUser" items="${ subSubject.subSubjectUsers }">
            <a href="<c:url value='/chat?action=start&sub_subject=${ subSubject.id }&consultant=${ subSubjectUser.id }' />">
                Start a consultation with ${ subSubjectUser.username }
            </a>
        </c:forEach>
    </c:forEach>
</body>

</html>