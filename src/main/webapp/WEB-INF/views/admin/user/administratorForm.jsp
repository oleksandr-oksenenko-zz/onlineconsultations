<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form:form method="POST" commandName="administrator">
                <input type="hidden" name="administrator">
                <c:if test="${mode eq 'add'}">
                    <div class="form-group">
                        <form:label path="username">Username</form:label>
                        <form:input path="username" cssClass="form-control"/>
                        <form:errors path="username" cssClass="text-danger"/>
                    </div>
                </c:if>

                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:password path="password" cssClass="form-control"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>

                <button type="submit" class="btn btn-default">Save</button>
                <a href="<c:url value="/admin/users"/>" class="btn btn-default">Cancel</a>
            </form:form>
        </div>
    </div>

</div>
</body>

</html>