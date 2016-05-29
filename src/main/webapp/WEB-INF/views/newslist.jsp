<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <h1><spring:message code="view.News.List"/></h1>

    <div class="panel panel-primary news-shelf">
        <div class="panel-heading">
            <spring:message code="view.News.AddedNews"/>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <sf:form method="post" action = '/spring/news/' class ="btn btn-succses">
                    <input class="btn btn-success" type="submit"  value="<spring:message code="view.News.Add"/>">
                </sf:form>
            </sec:authorize>
        </div>

        <div class="panel-body">
            <div class="row">
                <c:forEach items="${newslist}" var="singleNews">
                    <div class="panel">
                        <div class="thumbnail">
                            <a href="/spring/news/${singleNews.id}"><img src="/spring/resources/img/news.png" alt="${singleNews.name}"></a>
                            <style>
                                .text {
                                    text-align:  center;
                                }
                            </style>
                            <div class="text">
                                <p><a href="/spring/news/${singleNews.id}">${singleNews.name}</a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />