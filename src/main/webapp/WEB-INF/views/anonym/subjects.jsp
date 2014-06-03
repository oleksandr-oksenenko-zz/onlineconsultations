<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../common/js-include.jsp"/>
    <c:import url="../common/css-include.jsp"/>
</head>

<body>
    <div class="container">
        <c:import url="../common/header.jsp" />

        <c:forEach var="subject" items="${subjects}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="<c:url value='/subjects/${subject.id}'/>">${subject.name}</a>
                </div>
                <div class="panel-body">
                    ${ subject.description }
                </div>
            </div>
        </c:forEach>

        <c:import url="../common/footer.jsp" />
    </div>
</body>

</html>