<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">

	<h1><spring:message code="view.EnterTheSystem"/></h1>
	<br/>
	<div id="registration-form-wrapper">
		<jsp:include page="/WEB-INF/views/loginform.jsp" />
	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
