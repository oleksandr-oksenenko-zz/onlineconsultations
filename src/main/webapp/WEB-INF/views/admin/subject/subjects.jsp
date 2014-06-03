<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../../common/js-include.jsp"/>
    <c:import url="../../common/css-include.jsp"/>
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

    <table class="table table-bordered table-condensed table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>#</th>
        </tr>
        </thead>
        <c:forEach var="subject" items="${subjects}">
            <tr>
                <td>${subject.name}</td>
                <td>${subject.description}</td>
                <td class="min-width">
                    <a href="<c:url value="/admin/subjects/${subject.id}/edit"/>" class="btn btn-default">Edit</a>
                    <a href="<c:url value="/admin/subjects/${subject.id}/remove"/>" class="btn btn-danger">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value='/admin/subjects/add'/>" class="btn btn-default pull-right">
        <span class="glyphicon glyphicon-plus"></span>
        Add new subject
    </a>

</div>
</body>
</html>