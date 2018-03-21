$(function() {
	$(".navbar-expand-toggle").click(function() {
		$(".app-container").toggleClass("expanded");
		return $(".navbar-expand-toggle").toggleClass("fa-rotate-90");
	});
	return $(".navbar-right-expand-toggle").click(function() {
		$(".navbar-right").toggleClass("expanded");
		return $(".navbar-right-expand-toggle").toggleClass("fa-rotate-90");
	});
});

$(function() {
	return $('select').select2();
});

$(function() {
	return $('.toggle-checkbox').bootstrapSwitch({
		size : "small"
	});
});

$(function() {
	return $('.match-height').matchHeight();
});

$(function() {
	var oLanguage = {
		"oAria" : {
			"sSortAscending" : ": 升序排列",
			"sSortDescending" : ": 降序排列"
		},
		"oPaginate" : {
			"sFirst" : "首页",
			"sLast" : "末页",
			"sNext" : "下页",
			"sPrevious" : "上页"
		},
		"sEmptyTable" : "没有相关记录",
		"sInfo" : "第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条",
		"sInfoEmpty" : "第 0 到 0 条记录，共 0 条",
		"sInfoFiltered" : "(从 _MAX_ 条记录中检索)",
		"sInfoPostFix" : "",
		"sDecimal" : "",
		"sThousands" : ",",
		"sLengthMenu" : "每页显示条数: _MENU_",
		"sLoadingRecords" : "正在载入...",
		"sProcessing" : "正在载入...",
		"sSearch" : "搜索:",
		"sSearchPlaceholder" : "",
		"sUrl" : "",
		"sZeroRecords" : "没有相关记录"
	}
	$.fn.dataTable.defaults.oLanguage = oLanguage;
	return $('.datatable').DataTable({
		"dom" : '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
	});
});

$(function() {
	return $(".side-menu .nav .dropdown").on('show.bs.collapse', function() {
		return $(".side-menu .nav .dropdown .collapse").collapse('hide');
	});
});

$(function() {
	options = {
		size : "small",
		bootstrapMajorVersion : 3,
		currentPage : '${pageInfo.pageNum}',
		numberOfPages : '${pageInfo.pages}',
		totalPages : '${pageInfo.pages}',
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
});
