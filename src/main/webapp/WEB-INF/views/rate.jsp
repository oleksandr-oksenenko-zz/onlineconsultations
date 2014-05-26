<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/jquery/1.9.0/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/libs/bootstrap-rating-input.min.js'/>"></script>
</head>

<body>
<div class="container">
    <c:import url="common/header.jsp" />

    <form method="POST">
        <div class="form-group">
            <label for="user_rating">Please rate the consultant</label>
            <input type="number"
                   class="rating form-control"
                   id="user_rating"
                   name="user_rating"
                   data-min="1"
                   data-max="5"/>
            <span class="pull-right">
                <button type="submit" class="btn btn-default">Save</button>
                <a href="<c:url value='/'/>" class="btn btn-default">Cancel</a>
            </span>
        </div>
    </form>

    <c:import url="common/footer.jsp" />
</div>
</body>

</html>
