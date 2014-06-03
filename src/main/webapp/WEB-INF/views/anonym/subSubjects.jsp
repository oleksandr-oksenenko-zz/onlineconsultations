<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../common/js-include.jsp"/>
    <c:import url="../common/css-include.jsp"/>
</head>

<body>
<div class="container">
    <c:import url="../common/header.jsp" />

    <c:forEach var="subSubject" items="${ subSubjects }">
    <div class="panel panel-default">
        <div class="panel-heading">
            <c:out value="${ subSubject.name }" />
        </div>
        <div class="panel-body">
            <p>${ subSubject.description }</p>
        </div>

        <div class="panel-footer">
            <c:set var="subSubjectUsers" value="${ users.get(subSubject) }"/>
                <c:choose>
                    <c:when test="${not empty subSubjectUsers}">
                        <c:forEach var="user" items="${subSubjectUsers}">
                            <p>
                                <a href="<c:url value='/chat/start/${user.id}'/>">
                                    Start a consultation with
                                    ${ user.firstName } ${ user.lastName }
                                    (${ user.rating} <span class="glyphicon glyphicon-star"></span>)
                                </a>
                            </p>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="text-warning">There are no available consultants at the moment.</p>
                    </c:otherwise>
                </c:choose>
        </div>
    </div>
    </c:forEach>
    <c:import url="../common/footer.jsp" />
</div>
</body>

</html>