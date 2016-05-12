<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<title>ExtTop - Desktop Sample App</title>
	<%request.setAttribute("ctxPath", request.getContextPath());%>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/html/mydesktop/css/desktop.css" />
    <x-compile>
    <x-bootstrap> 
	<script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/ext-all.js"></script>
	<script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/shared/include-ext.js"></script>
    <script type="text/javascript" src="${ctxPath}/html/mydesktop/ext_base/shared/options-toolbar.js"></script>
    </x-bootstrap>
    <script type="text/javascript">
    	var path = "<%=request.getContextPath()%>";
    	//设置命名空间，方便引用
        Ext.Loader.setPath({
            'Ext.ux.desktop': '${ctxPath}/html/mydesktop/js',
            'MyDesktop':'${ctxPath}/html/mydesktop',
            'Leo':'${ctxPath}/js',
            MyDesktop: '',
            mine:''
        });
        Ext.require('MyDesktop.App');
        var username = '<sec:authentication property="name"/>';
        //动态模块
        var modules = [];
    </script>
    <!-- </x-compile> -->
</head>

<body>
    <a href="http://www.sencha.com" target="_blank" alt="Powered by Ext JS"
       id="poweredby"><div></div></a>
    <!-- spring security 通过权限控制不同用户显示不同模块 -->   
    <sec:authorize url="/html/notepad">
    	<script type="text/javascript">
    		modules.push(Ext.create('MyDesktop.Notepad',{
    			id:'Notepad',
    			title:'文本编辑器',
    			iconCls:'notepad-shortcut',
    			launcher : {
					text : '编辑文本',
					iconCls : 'notepad-shortcut'
				}
    		}));
    	</script>
    </sec:authorize>
	<script type="text/javascript">
		var myDesktopApp;
	    Ext.onReady(function () {
	        myDesktopApp = Ext.create('MyDesktop.App', {
				modules : modules
			});
	        if (modules.length == 0) {
				Ext.Msg.alert('无权限', '对不起，您没有此系统的任何使用权限');
			}
	    });
	</script>
</body>
</html>
