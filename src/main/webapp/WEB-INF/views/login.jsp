<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head></head>

<body>
    <c:if test="${ error eq true }">
        <b>Invalid login or password.</b>
    </c:if>
    <form action="<c:url value='login-check'/>" method="post">
        Username: <input type="text" name="username">
        Password: <input type="password" name="password">
        <input type="submit">
    </form>
</body>

</html>