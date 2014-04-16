<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="subject" items="${subjects}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <c:out value="${ subject.name }" />
            <span class="pull-right">
                <a href="<c:url value='subjects/${subject.id}/edit'/>">
                    <span class="glyphicon glyphicon-pencil"/>
                </a>
                <a href="<c:url value='subjects/${subject.id}/remove'/>">
                    <span class="glyphicon glyphicon-remove"/>
                </a>
            </span>
        </div>
        <div class="panel-body">
            <c:out value="${ subject.description }"/>
        </div>
    </div>

</c:forEach>