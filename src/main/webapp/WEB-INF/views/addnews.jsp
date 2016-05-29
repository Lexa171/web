<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <h1><spring:message code="view.News.AddOrEdit"/> ${news.name}</h1>

    <c:choose>
        <c:when test="${not empty news.id}">
            <c:url var="formUrl" value='/news/${news.id}'></c:url>
        </c:when>

        <c:otherwise>
            <c:url var="formUrl" value='/news/'></c:url>
        </c:otherwise>
    </c:choose>

    <div id="registration-form-wrapper">
        <div class="tab-pane">
            <sf:form modelAttribute="news" method="PUT" action="${formUrl}" data-toggle="validator">
                <div class="form-group">
                    <sf:label path="name" class="control-label"><spring:message code="view.News.Name"/></sf:label>
                    <p style="color: red;"><sf:errors path="name"/></p>
                    <sf:input path="name" class="form-control" required="required"/>
                </div>
                <div class="form-group">
                    <sf:label path="author" class="control-label"><spring:message code="view.News.Author"/></sf:label>
                    <br/><small><spring:message code="view.News.InputAuthorRestriction"/></small><br/>
                    <p style="color: red;"><sf:errors path="author"/></p>
                    <sf:input path="author" maxlength="30" minlength="3" class="form-control" required="required" pattern="^[a-zA-Zа-яёА-ЯЁ0-9]+[a-zA-Zа-яёА-ЯЁ0-9,!.]*$" />
                </div>
                <div class="form-group">
                    <sf:label path="data" class="control-label"><spring:message code="view.News.Data"/></sf:label>
                    <br/><small><spring:message code="view.News.InputDataRestriction"/></small><br/>
                    <p style="color: red;"><sf:errors path="data" /></p>
                    <sf:input path="data"  maxlength="10" class="form-control"  required="required" pattern="^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$"/>
                </div>
                <div class="form-group">
                    <sf:label path="text" class="control-label"><spring:message code="view.News.Text"/></sf:label>
                    <p style="color: red;"><sf:errors path="text"/></p>
                    <sf:textarea path="text" rows="5" class="form-control" required="required" />
                </div>
                <div class="form-group">
                    <sf:label path="type" class="control-label"><spring:message code="view.News.Type"/></sf:label>
                    <br/><small><spring:message code="view.News.InputAuthorRestriction"/></small><br/>
                    <p style="color: red;"><sf:errors path="type"/></p>
                    <sf:input path="type" maxlength="30" minlength="3" class="form-control" required="required" pattern="^[a-zA-Zа-яёА-ЯЁ0-9]+[a-zA-Zа-яёА-ЯЁ0-9,!.]*$" />
                </div>
                <button type="submit" class="btn btn-primary"><spring:message code="view.Submit"/></button>
            </sf:form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
