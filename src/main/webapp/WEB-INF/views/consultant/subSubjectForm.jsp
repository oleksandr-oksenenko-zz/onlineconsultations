<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../common/js-include.jsp"/>
    <c:import url="../common/css-include.jsp"/>

    <script type="text/javascript">
        var uploadSubSubjects = function() {
            var subjectId = $("#subjectsSelect").val();

            $.ajax({
                url: baseUrl + "/consultant/subjects/" + subjectId + "/sub_subjects",
                type: "POST",
                dataType: "json",
                success: setSubSubjects,
                error: function(xhr, status, error) {
                    console.log("error: status = " + status + ", error = " + error);
                }
            });
        };

        var setSubSubjects = function(data) {
            console.log("here");
            var subSubjectsSelect = $("#subSubjectsSelect");
            subSubjectsSelect.empty();
            $.each(data, function(index, subSubject) {
                subSubjectsSelect.append(
                    $("<option></option>")
                        .attr("value", subSubject.id)
                        .text(subSubject.name));
            });
        }
    </script>
</head>

<body>
    <div class="container">
        <div class="page-header">
            <ul class="nav nav-pills">
                <li><a href="<c:url value='/consultant/waiting_room'/>">Waiting room</a></li>
                <li class="active"><a href="<c:url value='/consultant/settings'/>">Settings</a></li>
            </ul>
        </div>

        <form:form method="POST" commandName="subSubject">
            <div class="form-group">
                <form:select path="subjectId"
                             items="${subjects}"
                             itemLabel="name"
                             itemValue="id"
                             cssClass="form-control"
                             id="subjectsSelect"
                             onchange="javascript:uploadSubSubjects()"
                        />
            </div>
            <div class="form-group">
                <form:select path="subSubjectId"
                             cssClass="form-control"
                             id="subSubjectsSelect"
                             items="${subSubjects}"
                             itemLabel="name"
                             itemValue="id"
                        />
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="<c:url value='/consultant/settings'/>" class="btn btn-danger">Cancel</a>
        </form:form>

        <c:import url="../common/footer.jsp" />
    </div>

</body>

</html>
