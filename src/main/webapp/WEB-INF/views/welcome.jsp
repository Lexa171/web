<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <h1><spring:message code="view.Welcome"/></h1>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />