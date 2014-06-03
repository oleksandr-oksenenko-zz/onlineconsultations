<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <c:import url="common/js-include.jsp"/>
    <c:import url="common/css-include.jsp"/>
</head>

<body>
<div class="container">
    <c:import url="common/header.jsp" />

    <p class="text-center" style="color: red;">
        Error happened. Reason: ${reason}
    </p>

    <c:import url="common/footer.jsp" />
</div>

</body>
</html>