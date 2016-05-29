<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><spring:message code="view.TitleSite"/></title>
    <link rel='stylesheet' href='/spring/resources/css/bootstrap.min.css'
          type='text/css' media='all'>
    <link rel='stylesheet' href='/spring/resources/css/style.css'
          type='text/css' media='all'>
    <script type="text/javascript"
            src="/spring/resources/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript"
            src="/spring/resources/js/bootstrap.min.js"></script>

    <link rel="icon" type="image/x-icon"
          href="/spring/resources/favicon.ico">
</head>

<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <sec:authorize access="isAuthenticated()">
                <div class="navbar-header">
                    <a class="navbar-brand"
                       href="/spring/my/<sec:authentication property="principal.username" />"><spring:message
                            code="view.User.MyPage"/></a>
                </div>
            </sec:authorize>

            <div class="collapse navbar-collapse"
                 id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav pull-right">
                    <li>
                        <form method="get" action='/spring/users'>
                            <input class="navbar-link transButton" type="submit"
                                   value="<spring:message code="view.User.Users"/>">
                        </form>
                    </li>
                    <li>
                        <form method="get" action='/spring/news'>
                            <input class="navbar-link transButton" type="submit"
                                   value="<spring:message code="view.News.News"/>">
                        </form>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <p class="navbar-text">
                                <spring:message code="view.YouLoggedAs"/> <strong><sec:authentication
                                    property="principal.username"/></strong>
                            </p>
                        </li>
                        <li>
                            <form method="get" action='/spring/logout'>
                                <input class="navbar-link transButton" type="submit"
                                       value="<spring:message code="view.Exit"/>">
                            </form>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li>
                            <form method="get" action='/spring/login'>
                                <input class="navbar-link transButton" type="submit"
                                       value="<spring:message code="view.Enter"/>">
                            </form>
                        </li>
                        <li>
                            <form method="get" action='/spring/users/reg'>
                                <input class="navbar-link transButton" type="submit"
                                       value="<spring:message code="view.Registration"/>">
                            </form>
                        </li>
                    </sec:authorize>
                    <li role="presentation" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true"
                           aria-expanded="false">
                            <spring:message code="view.Language"/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="?lang=ru_RU">Русский</a>
                            </li>
                            <li>
                                <a href="?lang=en_US">English</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>