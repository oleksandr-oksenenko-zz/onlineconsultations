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
            <li class="active"><a href="<c:url value='/admin/sub_subjects'/>">Sub subjects</a></li>
            <li><a href="<c:url value='/admin/users'/>">Users</a></li>
        </ul>
    </div>

    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form:form method="POST" commandName="subSubject">
                <div class="form-group">
                    <form:select path="parentSubjectId"
                                 items="${subjects}"
                                 itemLabel="name"
                                 itemValue="id"
                                 cssClass="form-control"
                            />
                </div>
                <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:textarea path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="text-danger"/>
                </div>
                <button type="submit" class="btn btn-default">Save</button>
                <a href="<c:url value="/admin/sub_subjects/"/>" class="btn btn-default">Cancel</a>
            </form:form>
        </div>
    </div>

</div>
</body>

</html>
