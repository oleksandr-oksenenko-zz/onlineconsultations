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
            <li><a href="<c:url value='/admin/subjects'/>">Subjects</a></li>
            <li><a href="<c:url value='/admin/sub_subjects'/>">Sub subjects</a></li>
            <li class="active"><a href="<c:url value='/admin/users'/>">Users</a></li>
        </ul>
    </div>

    <table class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>Username</th>
                <th>Role</th>
                <th colspan="2">#</th>
            </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    ${user.username}
                </td>
                <td>
                    ${user.userRole}
                </td>
                <td class="min-width">
                    <a href="<c:url value="/admin/users/${user.id}/edit"/>" class="btn btn-default">Edit</a>
                    <a href="<c:url value="/admin/users/${user.id}/remove"/>" class="btn btn-danger">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="row">
        <a href="<c:url value='/admin/users/add_consultant'/>" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-plus"></span>
            Add new consultant
        </a>
    </div><br>
    <div class="row">
        <a href="<c:url value='/admin/users/add_admin'/>" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-plus"></span>
            Add new administrator
        </a>
    </div>

</div>
</body>
</html>

