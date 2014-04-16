<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
</head>

<body>
<div class="container">
    <div class="page-header">
        <ul class="nav nav-pills">
            <li class="active"><a href="<c:url value='/admin/subjects'/>">Subjects</a></li>
            <li><a href="<c:url value='/admin/sub_subjects'/>">Sub subjects</a></li>
            <li><a href="<c:url value='/admin/users'/>">Users</a></li>
        </ul>
    </div>

    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <p><c:out value="message"/></p>
        </div>
    </c:if>

    <c:forEach var="subject" items="${subjects}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <c:out value="${ subject.name }" />
                <span class="pull-right">
                    <a href="<c:url value='subjects/${subject.id}/edit'/>">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </a>
                    <a href="<c:url value='subjects/${subject.id}/remove'/>">
                        <span class="glyphicon glyphicon-remove"></span>
                    </a>
                </span>
            </div>
            <div class="panel-body">
                <c:out value="${ subject.description }"/>
            </div>
        </div>
    </c:forEach>
    <a href="<c:url value="/admin/subjects/add"/>" class="pull-right">
        <button class="btn btn-default">
            <span class="glyphicon glyphicon-plus"></span>
            Add new subject
        </button>
    </a>

</div>
</body>
</html>