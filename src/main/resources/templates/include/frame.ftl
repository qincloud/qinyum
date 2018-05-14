<#compress> <#macro frame title charset="utf-8" lang="zh-CN">
<#assign fns=JspTaglibs["http://java.sun.com/jsp/jstl/functionss"] />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!doctype html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="shortcut icon " type="images/x-icon" href="/image/favicon.ico">
<title>人事系统</title>
<!-- CSS statics -->
<link rel="stylesheet" type="text/css" href="/tmp/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/tmp/jqueryui/jquery-ui-1.10.0.custom.min.css">
<link rel="stylesheet" type="text/css"
	href="/tmp/bootstrapValidator/css/bootstrapValidator.min.css">
<link rel="stylesheet" type="text/css"
	href="/tmp/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/tmp/css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href="/tmp/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css" href="/tmp/css/checkbox3.min.css">
<link rel="stylesheet" type="text/css"
	href="/tmp/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="/tmp/css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="/tmp/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href="/tmp/css/style.css">
<link rel="stylesheet" type="text/css" href="/tmp/themes/flat-blue.css">
<link id="skin" rel="stylesheet" href="/tmp/jBox/Skins2/Metro/jbox.css" />

<!-- Javascript statics -->
<script type="text/javascript" src="/tmp/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/tmp/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/tmp/jqueryui/jquery-ui-1.10.0.custom.min.js"></script>
<script type="text/javascript"
	src="/tmp/bootstrap/bootstrap-treeview.js"></script>
<script type="text/javascript"
	src="/tmp/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="/tmp/js/Chart.min.js"></script>
<script type="text/javascript"
	src="/tmp/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript"
	src="/tmp/jquery/jquery.matchHeight-min.js"></script>
<script type="text/javascript"
	src="/tmp/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="/tmp/bootstrap/dataTables.bootstrap.min.js"></script>
<script type="text/javascript"
	src="/tmp/bootstrap/bootstrap-paginator.js"></script>
<script type="text/javascript" src="/tmp/js/select2.full.min.js"></script>
<script type="text/javascript" src="/tmp/ace/ace.js"></script>
<script type="text/javascript" src="/tmp/ace/mode-html.js"></script>
<script type="text/javascript" src="/tmp/ace/theme-github.js"></script>
<script type="text/javascript" src="/tmp/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="/tmp/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="/tmp/js/app.js"></script>
<!-- Javascript -->
<script type="text/javascript">
	//获取指定form中的所有的<input>对象  
	function getElements(formId) {
		var form = document.getElementById(formId);
		var elements = new Array();
		var tagElements = form.getElementsByTagName('input');
		for (var j = 0; j < tagElements.length; j++) {
			elements.push(tagElements[j]);

		}
		return elements;
	}

	//获取单个input中的【name,value】数组 
	function inputSelector(element) {
		if (element.checked)
			return [ element.name, element.value ];
	}

	function input(element) {
		switch (element.type.toLowerCase()) {
		case 'submit':
		case 'hidden':
		case 'password':
		case 'text':
			return [ element.name, element.value ];
		case 'checkbox':
		case 'radio':
			return inputSelector(element);
		}
		return false;
	}

	//组合URL 
	function serializeElement(element) {
		var method = element.tagName.toLowerCase();
		var parameter = input(element);

		if (parameter) {
			var key = encodeURIComponent(parameter[0]);
			if (key.length == 0)
				return;

			if (parameter[1].constructor != Array)
				parameter[1] = [ parameter[1] ];

			var values = parameter[1];
			var results = [];
			for (var i = 0; i < values.length; i++) {
				results.push(key + '=' + +values[i]);
			}
			return results.join('&');
		}
	}

	//调用方法   
	function serializeForm(formId) {
		var elements = getElements(formId);
		var queryComponents = new Array();

		for (var i = 0; i < elements.length; i++) {
			var queryComponent = serializeElement(elements[i]);
			if (queryComponent)
				queryComponents.push(queryComponent);
		}

		return queryComponents.join('&');
	}

	function root() {
		return $('#menuroot');
	}
	
	$(function(){
		var $href = window.document.location.href;
		<#list ms as menu> 
		   <#if (menu.rank!) == '1'>
		       $.get('/system/menu/child',{'rank' : '${(menu.id)!}'},function(json){
		    	  var $ul = $('#'+'${(menu.id)!}')
		    	  json.data.forEach(function(value,index){
		    		  var $li = null;
		    		  if($href.indexOf(json.data[index].href)=='21'){
		    			  $li = $('<li></li>').attr("class","active");
		    			  $('#${(menu.name)!}').attr("class","in");
		    		  }else{
		    			  $li = $('<li></li>');
		    		  }
			    	  var $a = $('<a></a>').attr('href',json.data[index].href);
			    	  var $iconspan = $('<span></span>').attr("class","icon fa "+json.data[index].icon);
			    	  var $textspan = $('<span></span>').text(json.data[index].name)
			    	  $a.append($iconspan);
			    	  $a.append($textspan);
			    	  $li.append($a);
			    	  $ul.append($li);
		    	  });
		 
		       });
		   </#if>
		</#list>
	});
	
	function fullscreen(element){
		if(!isFullscreenEnabled()){
			// 判断各种浏览器，找到正确的方法
			 var exitMethod = document.exitFullscreen || //W3C
			 document.mozCancelFullScreen || //Chrome等
			 document.webkitExitFullscreen || //FireFox
			 document.webkitExitFullscreen; //IE11
			 if (exitMethod) {
			  exitMethod.call(element);
			 }
			 else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
			  var wscript = new ActiveXObject("WScript.Shell");
			  if (wscript !== null) {
			   wscript.SendKeys("{F11}");
			  }
			 }
		}else{
		// 判断各种浏览器，找到正确的方法
		 var requestMethod = element.requestFullScreen || //W3C
		 element.webkitRequestFullScreen || //Chrome等
		 element.mozRequestFullScreen || //FireFox
		 element.msRequestFullScreen; //IE11
		 if (requestMethod) {
		  requestMethod.call(element);
		 }
		 else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
		  var wscript = new ActiveXObject("WScript.Shell");
		  if (wscript !== null) {
		   wscript.SendKeys("{F11}");
		  }
		 }
		}
	}

	/*
	 * 判断是否全屏
	 **/
	function isFullscreenEnabled(){
         return document.fullscreenEnabled       ||
                document.mozFullScreenEnabled    ||
                document.webkitFullscreenEnabled ||
                document.msFullscreenEnabled || false;
     }
</script>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
<!-- <script type="text/javascript" src="/js/index.js"></script> -->
</head>
<body class="flat-blue">
	<div class="app-container expanded">
		<div class="row content-container">
			<nav class="navbar navbar-default navbar-fixed-top navbar-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-expand-toggle">
							<i class="fa fa-bars icon"></i>
						</button>
						<ol class="breadcrumb navbar-breadcrumb">
							<li class="active">${(title)!}</li>
						</ol>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-th icon"></i>
						</button>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="title"><a href="/">主页</a></li>
						<!-- 
						<li class="title"><a href="#"
							onclick="fullscreen(document.documentElement)"><span
								class="glyphicon glyphicon-th-large"></span></a></li> -->
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-times icon"></i>
						</button>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false"><i
								class="fa fa-comments-o"></i></a>
							<ul class="dropdown-menu animated fadeInDown">
								<li class="title">通知 <span class="badge pull-right">0</span>
								</li>
								<li class="message">没有新的通知</li>
							</ul></li>
						<li class="dropdown danger"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false"><i class="fa fa-star-half-o"></i> 4</a>
							<ul class="dropdown-menu danger  animated fadeInDown">
								<li class="title">通知 <span class="badge pull-right">4</span>
								</li>
								<li>
									<ul class="list-group notifications">
										<a href="#">
											<li class="list-group-item"><span class="badge">1</span>
												<i class="fa fa-exclamation-circle icon"></i> 监狱警察</li>
										</a>
										<a href="#">
											<li class="list-group-item"><span class="badge success">1</span>
												<i class="fa fa-check icon"></i> 警察休闲</li>
										</a>
										<a href="#">
											<li class="list-group-item"><span class="badge danger">2</span>
												<i class="fa fa-comments icon"></i> 警察新人</li>
										</a>
										<a href="#">
											<li class="list-group-item message">查看全部</li>
										</a>
									</ul>
								</li>
							</ul></li>
						<li class="dropdown profile"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${fns.getUsername()} <span class="caret"></span></a>
							<ul class="dropdown-menu animated fadeInDown">
								<!-- 
								<li class="profile-img"><img
									src="../img/profile/picjumbo.com_HNCK4153_resize.jpg"
									class="profile-img"></li>
								<li> -->
								<form action="/logout" method="post" id="logoutForm">
									<input type="hidden" id="csrf" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
								<div class="profile-info">
									<h4 class="username">${(username)!}</h4>
									<p>${(email)!}</p>
									<div class="btn-group margin-bottom-2x" role="group">
										<button type="button" class="btn btn-default">
											<i class="fa fa-user"></i> 个人
										</button>
										<button type="button" onclick="javascript:formSubmit();" class="btn btn-default">
											<i class="fa fa-sign-out"></i> 退出
										</button>
									</div>
								</div></li>
					</ul>
					</li>
					</ul>
				</div>
			</nav>
			<div class="side-menu">
				<nav class="navbar navbar-default" role="navigation">
					<div class="side-menu-container">
						<div class="navbar-header">
							<a class="navbar-brand" href="#">
								<div class="icon fa fa-paper-plane"></div>
								<div class="title">人事系统</div>
							</a>
							<button type="button"
								class="navbar-expand-toggle pull-right visible-xs">
								<i class="fa fa-times icon"></i>
							</button>
						</div>
						<ul id="menuroot" class="nav navbar-nav">
							<!-- 
							<li><a href="index.html"> <span
									class="icon fa fa-tachometer"></span><span class="title">Dashboard</span>
							</a></li>
							<li class="panel panel-default dropdown"><a
								data-toggle="collapse" href="#dropdown-element"> <span
									class="icon fa fa-desktop"></span><span class="title">系统管理</span>
							</a>
								<div id="dropdown-element" class="panel-collapse collapse">
									<div class="panel-body">
										<ul class="nav navbar-nav">
											<li><a href="ui-kits/theming.html">Theming</a></li>
											<li><a href="ui-kits/grid.html">Grid</a></li>
											<li><a href="ui-kits/button.html">Buttons</a></li>
											<li><a href="ui-kits/card.html">Cards</a></li>
											<li><a href="ui-kits/list.html">Lists</a></li>
											<li><a href="ui-kits/modal.html">Modals</a></li>
											<li><a href="ui-kits/alert.html">Alerts & Toasts</a></li>
											<li><a href="ui-kits/panel.html">Panels</a></li>
											<li><a href="ui-kits/loader.html">Loaders</a></li>
											<li><a href="ui-kits/step.html">Tabs & Steps</a></li>
											<li><a href="ui-kits/other.html">Other</a></li>
										</ul>
									</div>
								</div></li>
								
								 <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-table">
                                    <span class="icon fa fa-table"></span><span class="title">Table</span>
                                </a>
 
                                <div id="dropdown-table" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="table/table.html">Table</a>
                                            </li>
                                            <li><a href="table/datatable.html">Datatable</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li> -->

							<#list ms as menu> <#if (menu.rank!) == '1'>
							<li class="panel panel-default dropdown"><a
								data-toggle="collapse" href="#${(menu.name)!}"> <span
									class="icon fa ${(menu.icon)!}"></span><span class="title">${(menu.name)!}</span>
							</a>
								<div id="${(menu.name)!}" class="panel-collapse collapse">
									<div class="panel-body">
										<ul id='${(menu.id)!}' class="nav navbar-nav">

										</ul>
									</div>
								</div></li> </#if> </#list>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</nav>
			</div>
			<div class="container-fluid">

				<div class="side-body padding-top"><#nested/></div>

			</div>
			<footer class="app-footer">
				<div class="wrapper">
					<span class="pull-right"></span>
				</div>
			</footer>
		</div>
</body>
<script type="text/javascript">
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
xhr.setRequestHeader(header, token);
});
$(function() {
	var tabs = $( "#tabs3" ).tabs();
	/**全选*/
	$(".checkall").click(function (){
		var $t=$(this);
		var cd=$t.prop("cd");
		if (!cd || cd=="1"){
			$(".check").prop("checked",true);
			$(".checkall").prop("checked",true);
			$t.prop("cd","0");
		} else {
			$(".check").prop("checked",false);
			$(".checkall").prop("checked",false);
			$t.prop("cd","1");
		}
		
		if ($(".check:checked").size()>0){
			$(".delall").prop("disabled",false);
		} else {
			$(".delall").prop("disabled",true);
		}
	});
	
	/**单选*/
	$(".check").click(function (){
		if ($(".check:not(:checked)").size()>0){
			$(".checkall").prop("checked",false);
		} else {
			$(".checkall").prop("checked",true);
		}
		
		if ($(".check:checked").size()>0){
			$(".delall").prop("disabled",false);
		} else {
			$(".delall").prop("disabled",true);
		}
	});
	
	<#if (pageInfo)??>
	options = {
	    size:"normal", 
		bootstrapMajorVersion : 3,
		listContainerClass: "info",
        itemContainerClass: function (type, page, current) {
            return page === current ? "active" : ""
        },
        itemContentClass: function (type, page, current) {
            return ""
        },
		currentPage : '${pageInfo.pageNum}',
		numberOfPages : '${pageInfo.pages}',
		totalPages : '${pageInfo.pages}',
		alignment:"right",
		itemTexts : function(type, page, current) {
			switch (type) {
			case "first":
				return "首页";
			case "prev":
				return "上一页";
			case "next":
				return "下一页";
			case "last":
				return "末页";
			case "page":
				return page;
			}
		},
		pageUrl : function(type, page, current) {
			return $('.pagination').attr('url')+"?page=" + page;

		}
	};
	$('.pagination').bootstrapPaginator(options);
	</#if>
});
</script>
</html>
</#macro> </#compress>
