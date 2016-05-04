<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<title>ExtTop - Desktop Sample App</title>
	<%request.setAttribute("ctxPath", request.getContextPath());%>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/html/mydesktop/css/desktop.css" />
    
    <!-- GC -->

    <!-- <x-compile> -->
    <!-- <x-bootstrap> -->
	<script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/ext-all.js"></script>
	<script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/shared/include-ext.js"></script>
    <script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/shared/options-toolbar.js"></script>
    <!-- </x-bootstrap> -->
    <script type="text/javascript">
    	var path = "<%=request.getContextPath()%>";
        Ext.Loader.setPath({
            'Ext.ux.desktop': 'js',
            MyDesktop: ''
        });

        Ext.require('MyDesktop.App');

        var myDesktopApp;
        Ext.onReady(function () {
            myDesktopApp = new MyDesktop.App();
        });
    </script>
    <!-- </x-compile> -->
</head>

<body>

    <a href="http://www.sencha.com" target="_blank" alt="Powered by Ext JS"
       id="poweredby"><div></div></a>

</body>
</html>
