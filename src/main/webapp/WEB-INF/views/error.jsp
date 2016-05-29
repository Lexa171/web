<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/header.jsp"/>



<div class="container" align="center">
	<br/>
	<h1 ><strong><spring:message code="view.Error" /></strong></h1>
	<hr/>
	<br/>
	
	<c:if test="${not empty errMsg}">
		<h1>${errMsg}</h1>
	</c:if>
	
	<c:if test="${not empty errCode}">
		<h3>${errCode}</h3>
	</c:if>

	<c:if test="${empty errCode}">
		<h3>
			<spring:message code="view.SystemErrors" />
		</h3>
	</c:if>

	

	<details>
		<summary>
			<spring:message code="view.ShowStackTrace" />
		</summary>
		<pre></pre>
		<p>${errStackTrace}</p>
	</details>


</div>


<jsp:include page="/WEB-INF/views/footer.jsp" />