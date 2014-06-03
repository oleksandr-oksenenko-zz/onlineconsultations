<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>

<head>
    <c:import url="../common/css-include.jsp"/>
</head>

<body>
    <div class="container">
        <div id="chatEndModalWindow" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4>Consultant has left the chat</h4>
                    </div>
                    <div class="modal-body">
                        <p>Do you want to end chat?</p>
                        <a href="<c:url value="/chat/end"/>" class="btn btn-default">
                            End the chat
                        </a>
                        <button type="button" class="btn btn-default" id="btnCloseChatEndModalWindow">
                            Cancel
                        </button>
                    </div>
                    <div class="modal-footer">
                        <p>footer</p>
                    </div>
                </div>
            </div>
        </div>

        <c:import url="../common/header.jsp" />
        
        <div id="messages">
        
        </div>
        
        <form role="form">
            <div class="form-group">
                <label for="message">New message (<small>you can send messages using ctrl+enter</small>)</label>
                <textarea rows="3"
                    class="form-control"
                    id="message"
                    disabled="disabled"
                    ></textarea>
            </div>
            <button type="button"
                    class="btn btn-default"
                    id="btnPostMessage"
                    disabled="disabled"
                    >
                Post a message
            </button>

            <a href="<c:url value="/chat/end"/>" id="btnEndChat" class="btn btn-default" disabled="disabled">
                End the chat
            </a>
        </form>

        <c:import url="../common/footer.jsp" />
    </div>

    <c:import url="../common/js-include.jsp"/>

    <script type="text/javascript" src="<c:url value='/js/common/chat_poller.js'/>"></script>
    <script type="text/javascript" src="<c:url value="/js/common/chat.js"/>"></script>

    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <script type="text/javascript" src="<c:url value='/js/anonym/consultant_poller.js'/>"></script>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_CONSULTANT')">
        <script type="text/javascript" src="<c:url value="/js/consultant/anonym_poller.js"/>"></script>
    </sec:authorize>

</body>

</html>