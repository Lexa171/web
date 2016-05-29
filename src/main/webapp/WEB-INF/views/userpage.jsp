<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<jsp:include page="header.jsp"/>
<div class="container">
    <h1><spring:message code="view.User.Page"/> ${user.userName}</h1>


    <spring:message code="role.Admin" var="ROLE_ADMIN" scope="page"/>
    <sec:authentication property="principal.username" var="userAuthName" scope="page" />

    <sec:authentication property="principal.authorities" var="userRole" scope="page" />

    <c:set var="accessUser" value="${userAuthName == user.userName}" scope="page" />

    <div class="input-form-div">
        <c:choose>
            <c:when test="${accessUser == true}">
                <sf:form method="POST" action='/spring/users/${user.userId}'
                         class="btn btn-succses">
                    <input class="btn btn-success" type="submit"
                           value="<spring:message code="view.User.Edit"/>">
                </sf:form>
            </c:when>
            <c:otherwise>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <sf:form method="POST" action='/spring/users/${user.userId}'
                             class="btn btn-succses">
                        <input class="btn btn-success" type="submit"
                               value="<spring:message code="view.User.Edit"/>">
                    </sf:form>
                    <sf:form method="DELETE" action='/spring/users/${user.userId}'
                             class="btn btn-succses">
                        <input class="btn btn-danger delete-user" type="submit"
                               value="<spring:message code="view.User.Remove"/>">
                    </sf:form>
                </sec:authorize>
            </c:otherwise>
        </c:choose>
    </div>

    <br>
    <div class="tab-pane">
        <div class="col-xs-6 col-md-3">
            <a href="/spring/resources/img/theuser.jpg" target="_blank"
               class="thumbnail"> <img src="/spring/resources/img/theuser.jpg"
                                       alt="${user.userName}">
            </a>
        </div>
        <div class="col-xs-6 col-md-9">
            <table class="table desc-table">
                <tbody>
                <c:if test="${user.userName ne null}">
                    <tr>
                        <td class="col-xs-4"><spring:message code="view.User.Login"/></td>
                        <td>${user.userName}</td>
                    </tr>
                </c:if>
                <tr>
                    <td class="col-xs-4"><spring:message code="view.User.Role"/></td>
                    <td>${user.userRole}</td>
                </tr>
                <c:if test="${user.userMail ne null}">
                    <tr>
                        <td class="col-xs-4"><spring:message code="view.User.Mail"/></td>
                        <td>${user.userMail}</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp" />
