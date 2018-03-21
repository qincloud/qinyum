<#include "/include/frame.ftl" /> <@frame title="菜单添加">
<style>
.tree {
    min-height:20px;
    padding:19px;
    margin-bottom:20px;
    background-color:#fbfbfb;
    border:1px solid #999;
    -webkit-border-radius:4px;
    -moz-border-radius:4px;
    border-radius:4px;
    -webkit-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    -moz-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05)
}
.tree li {
    list-style-type:none;
    margin:0;
    padding:10px 5px 0 5px;
    position:relative
}
.tree li::before, .tree li::after {
    content:'';
    left:-20px;
    position:absolute;
    right:auto
}
.tree li::before {
    border-left:1px solid #999;
    bottom:50px;
    height:100%;
    top:0;
    width:1px
}
.tree li::after {
    border-top:1px solid #999;
    height:20px;
    top:25px;
    width:25px
}
.tree li span {
    -moz-border-radius:5px;
    -webkit-border-radius:5px;
    border:1px solid #999;
    border-radius:5px;
    display:inline-block;
    padding:3px 8px;
    text-decoration:none
}
.tree li.parent_li>span {
    cursor:pointer
}
.tree>ul>li::before, .tree>ul>li::after {
    border:0
}
.tree li:last-child::before {
    height:30px
}
.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
    background:#eee;
    border:1px solid #94a0b4;
    color:#000
}
</style>
<script type="text/javascript">
	function selectmenu(id, menuname) {
		$('#parentmenu').val(menuname);
		$('#parent_id').val(id);
	}

	function extendsChild(root, layer) {
		$.get('/system/menu/child', {
			'rank' : layer
		}, function(json) {
			var $ul = $('<ul></ul>');
			json.data.forEach(function(value, i) {
				var $li = $('<li></li>');
				var $iconspan = $('<span></span>').attr("class",
						"icon fa " + json.data[i].icon);
				var $span = $('<span></span>').text(json.data[i].name);
				$li.append($iconspan);
				$li.append($span);
				if (layer == '0') {
					var $a = $("<a></a>").attr("href", "#").attr(
							"onclick",
							"selectmenu('" + json.data[i].id
									+ "'                   ,'"
									+ json.data[i].name + "')").text(' 选择');
					$li.append($a);
				}
				$ul.append($li);
				root.append($ul);
				extendsChild($li, json.data[i].id);
			});
		});
	}

	$(function() {
		var root = $('#treeroot');
		var li = $('<li></li>');
		var span = $('<span></span>').text('顶级菜单');
		span.append($('<i></i>').attr('class', 'icon-folder-open'));
		var $a = $("<a></a>").attr("href", "#").attr("onclick",
				"selectmenu('0','顶级菜单')").text(' 选择');
		li.append(span);
		li.append($a);
		root.append(li);
		extendsChild(li, 0);
		$('#menuform').bootstrapValidator({
			message : '这个值没有验证',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				name : {
					message : '菜单没有验证',
					validators : {
						notEmpty : {
							message : '菜单名称不能为空'
						}
					}
				},
				sort : {
					message : '排序号没有验证',
					validators : {
						notEmpty : {
							message : '排序号不能为空'
						}
					}
				},
				permission : {
					message : '权限没有验证',
					validators : {
						notEmpty : {
							message : '权限不能为空'
						}
					}
				},
				permission : {
					message : '权限没有验证',
					validators : {
						notEmpty : {
							message : '权限不能为空'
						}
					}
				}
			}
		}).on('success.form.bv', function(e) {
			e.preventDefault();
			var $form = $(e.target);
			var bv = $form.data('bootstrapValidator');
			$.post($form.attr('action'), $form.serialize(), function(json) {
				if (json.status == '200') {
					alert('成功' + json.msg);
				} else {
					alert('失败' + json.msg);
				}
				location.href = '/system/menu';
			}, 'json');
		});
	});

	function submitForm() {
		return;
	}

	$(function(){
	    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	    $('.tree li.parent_li > span').on('click', function (e) {
	        var children = $(this).parent('li.parent_li').find(' > ul > li');
	        if (children.is(":visible")) {
	            children.hide('fast');
	            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
	        } else {
	            children.show('fast');
	            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
	        }
	        e.stopPropagation();
	    });
	});

	$(function() {
		if ('${(model.rank)!}' == 2) {
			$('#hrefdiv').css('display', 'block');
		} else {
			$('#hrefdiv').css('display', 'none');
		}
	});
</script>
<div class="card">
	<div class="tree well" style="float: right; width: 50%;">
		<ul id="treeroot">

		</ul>
	</div>
	<div class="card-body" style="float: left; width: 38.2%;">
		<form id="menuform" action="/system/menu/saveorupdate">
			<div class="form-group">
				<label for="exampleInputEmail1">菜单名字</label> <input type="text"
					class="form-control" id="name" name="name" value="${(model.name)!}"
					placeholder=""><input type="hidden" id="id" name="id"
					value="${(model.id)!}" />
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">菜单层级</label> <select
					class="form-control" id="rank" defValue='${(model.rank)!}'
					form="menuform" onchange="sele_Change()">
					<option value="1">目录级</option>
					<option value="2" selected>菜单级</option>
					<option value="3">按钮级</option>
				</select><input type="hidden" id="rankhidden" name="rank"
					value="${(model.rank)!}" />

			</div>
			<div id="hrefdiv" class="form-group" style="display: none;">
				<label for="exampleInputPassword1">菜单路径</label> <input type="text"
					class="form-control" id="href" name="href" value="${(model.href)!}"
					placeholder="">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">排序</label> <input type="text"
					class="form-control" id="sort" name="sort" value="${(model.sort)!}"
					placeholder="">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">权限标志</label> <input type="text"
					class="form-control" id="permission" name="permission"
					value="${(model.permission)!}" placeholder="">
			</div>

			<div class="form-group">
				<label for="exampleInputPassword1">图标</label> <input type="text"
					class="form-control" id="icon" name="icon" placeholder=""
					value="${(model.icon)!}" />
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">父菜单</label> <input type="text"
					readonly class="form-control" id="parentmenu" name="parent"
					placeholder="" value="${(model.parent)!}" /><input type="hidden"
					id="parent_id" name="parent_id" value="${(model.parent_id)!}" />
			</div>
			<div class="form-group">
				<div class="col-lg-9 col-lg-offset-3">
					<button type="submit" class="btn btn-info">保存</button>
					<a href="/system/menu" class="btn btn-info">返回</a>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$(function() {
		$('#rankhidden').val('1');
	});
	function sele_Change() {
		$('#rankhidden').val($('#rank').val());
		if ($('#rank').val() == 2) {
			$('#hrefdiv').css('display', 'block');
		} else {
			$('#hrefdiv').css('display', 'none');
		}
	}
</script>
</@frame>
