<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="tab-pane">
	<form method="POST" action="<c:url value='/login'></c:url>"
		  data-toggle="validator">
		<div class="form-group">
			<label for="j_username" class="control-label"><spring:message code="view.User.Login"/></label><input
				type="text" name="username" class="form-control" placeholder="<spring:message code="view.User.Login"/>"
				required>
		</div>
		<div class="form-group">
			<label for="j_password" class="control-label"><spring:message code="view.User.Password"/></label><input
				type="password" name="password" class="form-control"
				placeholder="<spring:message code="view.User.Password"/>" required>
		</div>
		<button type="submit" class="btn btn-primary"><spring:message code="view.Enter"/></button>
	</form>
</div>

