<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li><a href="<c:url value='/consultant/waiting_room'/>">Waiting room</a></li>
                <li class="active"><a href="<c:url value='/consultant/settings'/>">Settings</a></li>
            </ul>
        </div>

        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Sub subject name</th>
                <th>Parent subject</th>
                <th>Description</th>
                <th colspan="2">#</th>
            </tr>
            </thead>
            <c:forEach var="subSubject" items="${subSubjects}">
                <tr>
                    <td>
                        ${subSubject.name}
                    </td>
                    <td>
                        ${subSubject.parentSubject.name}
                    </td>
                    <td>
                        ${subSubject.description}
                    </td>
                    <td class="min-width">
                        <a href="<c:url value='/consultant/settings/sub_subjects/${subSubject.id}/remove'/>" class="btn btn-default">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="<c:url value='/consultant/settings/sub_subjects/add'/>" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-plus"></span>
            Add new sub subject
        </a>

        <c:import url="../common/footer.jsp" />
    </div>

</body>

</html>
