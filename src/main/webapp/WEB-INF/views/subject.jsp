<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
</head>

<body>
    <div class="container">
        <c:import url="common/header.jsp" />

        <c:forEach var="subSubject" items="${ subject.subSubjects }">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <c:out value="${ subSubject.name }" />
                </div>
                <div class="panel-body">
                    <c:out value="${ subject.description }"></c:out>
                </div>

                <c:set var="users" value="${ subSubject.subSubjectUsers }" />
                <c:set var="isChanges" value="${ users.retainAll(onlineConsultants) }"/>
                
                <ul class="list-group">
                    <c:choose>
                        <c:when test="${ not empty users }">
                            <c:forEach var="user" items="${ users }">
                                <li class="list-group-item">
                                    <a href="<c:url value='/chat/start/${ user.id }'/>">
                                        Start a consultation with ${ user.firstName } ${ user.lastName } 
                                    </a>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li class="list-group-item">
                                <p class="text-warning">There are no avaiable consultants at the moment.</p>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </c:forEach>

        <c:import url="common/footer.jsp" />
    </div>
</body>

</html>