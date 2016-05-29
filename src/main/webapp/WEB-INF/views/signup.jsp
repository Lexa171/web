<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
	<h1><spring:message code="view.User.AddOrEdit"/></h1>
	<div class="text-redirect"><spring:message code="view.User.RedirectAfterSignup"/></div>
	<br/>

	<c:choose>
		<c:when test="${not empty user.userId}">
			<c:url var="formUrl" value='/users/${user.userId}'></c:url>
		</c:when>
		<c:otherwise>
			<c:url var="formUrl" value='/users/reg'></c:url>
		</c:otherwise>
	</c:choose>

	<div id="registration-form-wrapper">
		<div class="tab-pane">
			<sf:form modelAttribute="user" method="PUT" action="${formUrl}" data-toggle="validator">
				<c:if test="${not empty user.userId}">
					<sf:input path="userId"  type="hidden" name="userId" value="${user.userId}"/>
				</c:if>
				<div class="form-group">
					<sf:label path="userName" class="control-label"><spring:message code="view.User.Login"/></sf:label>
					<br/><small><spring:message code="view.User.InputLoginRestriction"/></small><br/>
					<sf:input path="userName" maxlength="20" minlength="3" class="form-control" required="required"  pattern="^[a-zA-Z0-9]+$"/>
					<sf:errors path="userName" cssClass="error"/>
				</div>
				<div class="form-group">
					<sf:label path="userPassword" class="control-label"><spring:message code="view.User.Password"/></sf:label>
					<br/><small><spring:message code="view.User.InputPasswordRestriction"/></small><br/>
					<sf:password path="userPassword" maxlength="30" minlength="6" class="form-control" required="required" pattern="^[a-zA-Z0-9]+$" />
					<sf:errors path="userPassword" cssClass="error"/>
				</div>
				<div class="form-group">
					<sf:label path="userMail" class="control-label"><spring:message code="view.User.Mail"/></sf:label>
					<br/><small><spring:message code="view.User.InputMailRestriction"/></small><br/>
					<sf:input path="userMail" maxlength="40" type="email" class="form-control" pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" />
					<sf:errors path="userMail" cssClass="error"/>
				</div>
				<button type="submit" class="btn btn-primary"><spring:message code="view.Submit"/></button>
			</sf:form>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
