<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <h1> ${news.name}</h1>
    <div class="btn-toolbar" role="toolbar">
        <sec:authorize access="isAuthenticated()">
            <div class="input-form-div">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <sf:form method="DELETE" action='/spring/news/${news.id}' class="btn btn-succses">
                        <input class="btn btn-danger delete-user" type="submit"
                               value="<spring:message code="view.News.Remove"/>">
                    </sf:form>

                    <sf:form method="POST" action='/spring/news/${news.id}' class="btn btn-succses">
                        <input class="btn btn-success" type="submit" value="<spring:message code="view.News.Edit"/>">
                    </sf:form>
                </sec:authorize>
            </div>
        </sec:authorize>
    </div>
    <br>

    <div class="tab-pane">
        <div class="col-xs-6 col-md-3">
            <a href="/spring/resources/img/news.png" target="_blank" class="thumbnail">
                <img src="/spring/resources/img/news.png" alt="${news.name}">
            </a>
        </div>
        <div class="col-xs-6 col-md-9">
            <table class="table desc-table">
                <tbody>
                <c:if test="${news.name ne null}">
                    <tr>
                        <td class="col-xs-4"><spring:message code="view.News.Name"/></td>
                        <td>${news.name}</td>
                    </tr>
                </c:if>
                <c:if test="${news.author ne null}">
                    <tr>
                        <td><spring:message code="view.News.Author"/></td>
                        <td>${news.author}</td>
                    </tr>
                </c:if>
                <c:if test="${news.data ne null}">
                    <tr>
                        <td><spring:message code="view.News.Data"/></td>
                        <td>${news.data}</td>
                    </tr>
                </c:if>
                <c:if test="${news.type ne null}">
                    <tr>
                        <td><spring:message code="view.News.Type"/></td>
                        <td>${news.type}</td>
                    </tr>
                </c:if>
                <c:if test="${news.text ne null}">
                    <tr>
                        <td colspan="2">
                                ${news.text}
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
