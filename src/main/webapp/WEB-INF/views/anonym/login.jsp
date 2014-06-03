<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../common/js-include.jsp"/>
    <c:import url="../common/css-include.jsp"/>
</head>

<body>
    <div class="container" style="max-width: 25%">
        <c:import url="../common/header.jsp"/>

        <c:if test="${ error eq true }">
            <p class="text-danger">Invalid login or password.</p>
        </c:if>
        <form action="<c:url value='/login-check'/>" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Enter username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Enter password">
            </div>
            <button type="submit" class="btn btn-default">Login</button>
        </form>

        <c:import url="../common/footer.jsp" />
    </div>
</body>

</html>