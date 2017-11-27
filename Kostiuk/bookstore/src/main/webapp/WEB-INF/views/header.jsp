<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

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
    <title>Bookstore</title>
</head>



