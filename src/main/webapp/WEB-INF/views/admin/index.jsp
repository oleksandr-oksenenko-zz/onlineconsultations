<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
</head>

<c:set var="isSubjectsPage" value="${pageType eq 'subjects'}"/>
<c:set var="isSubSubjectsPage" value="${pageType eq 'subsubjects'}"/>
<c:set var="isUsersPage" value="${pageType eq 'users'}"/>

<body>
    <div class="container">
        <c:import url="common/header.jsp"/>

        <c:choose>
            <c:when test="${isSubjectsPage}">
                <c:import url="subjects.jsp"/>
            </c:when>
            <c:when test="${isSubSubjectsPage}">
                <c:import url="subsubjects.jsp"/>
            </c:when>
            <c:when test="${isUsersPage}">
                <c:import url="admin/users.jsp"/>
            </c:when>
        </c:choose>

    </div>
</body>

</html>
