<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<meta http-equiv="content-type" content="text/html; charset=UTF-8">--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <spring:url value="/css/yeti.css" var="_css"/>
    <spring:url value="/css/bookstore.css" var="bookstore_css"/>
    <spring:url value="/css/awesomplete.css" var="awesomplete_css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${_css}"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${bookstore_css}"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${awesomplete_css}"/>


    <spring:message code="welcome.message" var="welcome"/>
    <spring:message code="bookstore.message" var="bookstore"/>
    <spring:message code="add.new.book.message" var="add_new_book"/>
    <spring:message code="add.new.book.to.message" var="add_new_book_to"/>
    <spring:message code="enter.credentials.message" var="enter_credentials"/>
    <spring:message code="new.book.message" var="new_book"/>
    <spring:message code="add.new.user.message" var="add_new_user"/>
    <spring:message code="register.new.user.message" var="register_new_user"/>
    <spring:message code="first.name.message" var="first_name"/>
    <spring:message code="second.name.message" var="second_name"/>
    <spring:message code="edit.book.message" var="edit_book"/>
    <spring:message code="go.to.wiki.message" var="go_to_wiki"/>
    <spring:message code="users.message" var="users"/>
    <spring:message code="books.message" var="booksMsg"/>
    <spring:message code="to.bottom.message" var="to_bottom"/>
    <spring:message code="to.top.message" var="to_top"/>
    <spring:message code="title.message" var="title"/>
    <spring:message code="country.message" var="country"/>
    <spring:message code="year.message" var="year"/>
    <spring:message code="author.message" var="author"/>
    <spring:message code="pages.message" var="pages"/>
    <spring:message code="language.message" var="language"/>
    <spring:message code="delete.message" var="delete"/>
    <spring:message code="submit.message" var="submit"/>
    <spring:message code="added.by.message" var="added_by"/>
    <spring:message code="viewers.message" var="reviewers"/>
    <spring:message code="image.message" var="image"/>
    <spring:message code="back.to.catalog.message" var="back_to_catalog"/>
    <spring:message code="login.message" var="login"/>
    <spring:message code="logout.message" var="logout"/>
    <spring:message code="password.message" var="password"/>
    <spring:message code="enter.credentials.message" var="enter_credentials"/>
    <spring:message code="login.to.bookstore.message" var="login_to_bookstore"/>
    <spring:message code="new.book.info.message" var="new_book_info"/>
    <spring:message code="about.book.message" var="about_book"/>
    <spring:message code="show.books.message" var="show_books"/>
    <spring:message code="show.users.message" var="show_users"/>

    <title>${bookstore}</title>
</head>

