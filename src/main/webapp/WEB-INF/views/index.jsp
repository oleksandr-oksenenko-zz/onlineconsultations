<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
	<link rel="stylesheet" href="<c:url value='/resources/bootstrap/3.0.0/css/bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>

<body>
    <div id="wrap" class="container">
        <div id="header" class="page-header">
            <%@ include file="header.jsp"%>
        </div>

        <div id="body">
            <form action="encode" method="post">
                <div class="form-group" style="width: 30%">
                    <label for="textToEncode">Text to encode: </label>
                    <input type="text" name="textToEncode" id="textToEncode" class="form-control">
                </div>
                <button type="submit" class="btn btn-default">Encode!</button>
            </form>
            <hr>
            <form action="decode" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="fileToDecode">File to decode: </label>
                    <input type="file" name="fileToDecode" id="fileToDecode" style="width: 30%">
                </div>
                <button type="submit" class="btn btn-default">Decode!</button>
            </form>
        </div>

        <div id="footer">
            <%@ include file="footer.jsp"%>
        </div>
    </div>
</body>

</html>