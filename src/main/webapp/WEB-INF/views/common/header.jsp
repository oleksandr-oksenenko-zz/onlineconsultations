<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="page-header">
    <h3><a href="<c:url value='/'/>">Online consultations for everyone!</a></h3>
    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <div class="row">
            <div class="col-md-offset-11">
                <a href="<c:url value="/login"/>">Login</a>
            </div>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div class="row">
            <div class="col-md-offset-11">
                <a href="<c:url value='/logout'/>">Logout</a>
            </div>
        </div>
    </sec:authorize>
</div>