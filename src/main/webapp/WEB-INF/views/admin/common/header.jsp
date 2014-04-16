<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
    <ul class="nav nav-pills">
        <li
            <c:if test="${pageType eq 'subjects'}">class="active"</c:if>
            ><a href="<c:url value='/admin/subjects'/>">Subjects</a>
        </li>
        <li
            <c:if test="${pageType eq 'subsubjects'}">class="active"</c:if>
            ><a href="<c:url value='/admin/sub_subjects'/>">Sub subjects</a>
        </li>
        <li
            <c:if test="${pageType eq 'users'}">class="active"</c:if>
            ><a href="<c:url value='/admin/users'/>">Users</a>
        </li>
    </ul>
</div>
