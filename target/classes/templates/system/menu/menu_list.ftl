<#include "/include/frame.ftl" /> <@frame title="菜单管理">
<div class="card">
	<div class="card-header">
		<div class="card-title">
			<form action="/system/menu">
				<div class="form-inline">
					<div class="form-group">
						<label for="exampleInputName2">菜单名</label> <input type="text"
							class="form-control" id="name" name="name"
							value="${(model.name)!}" placeholder="菜单名">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">菜单路径</label> <input type="text"
							class="form-control" id="href" name="href"
							value="${(model.href)!}" placeholder="菜单路径">
					</div>

				</div>
				<div class="form-inline" style="text-align: right;">
					<button type="submit" class="btn btn-info">搜素</button>
					<a href="#" class="btn btn-info">清空</a> <a class="btn btn-info"
						onclick="tark('/system/menu/add')">添加菜单</a>
				</div>
			</form>
		</div>
	</div>
	<div class="card-body">
		<table
			class="table table-striped table-bordered table-hover table-condensed"
			width="100%">
			<thead>
				<tr class="active">
					<td style="width: 5%"><input type="checkbox" class="checkall" /></td>
					<td>序号</td>
					<td>菜单名称</td>
					<td>路径</td>
					<td>菜单层级</td>
					<td>上级菜单</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<#list pageInfo.list as x>
				<tr>
					<td><input type="checkbox" class="check" id="${(x.id)!}" /></td>
					<td>${x_index+(pageInfo.startRow)!0}</td>
					<td>${(x.name)!}</td>
					<td>${(x.href)!}</td>
					<td><#if (x.rank!) == '1'>目录级<#elseif (x.rank!) ==
						'2'>菜单级<#else>按钮级</#if></td>
					<td>${(x.parent)!}</td>

					<td><button href="#" onclick="edit('${(x.id)!}')"
							class="btn btn-success btn-sm">编辑</button>
						<button href="#" onclick="del('${(x.id)!}')"
							class="btn btn-danger btn-sm">删除</button></td>
				</tr>
				</#list>
			</tbody>
			<tfooter>
			<tr>
				<td>
					<div style="text-align: left;">
						<a href="#" class="btn btn-info">删除全部</a>
					</div>
				</td>
				<td colspan="7">
					<ul class="pagination" url='/system/menu'
						style="text-align: right;"></ul>
				</td>
			</tr>
			</tfooter>
		</table>
	</div>
</div>
<script>
	function edit(id) {
		location.href = "/system/menu/edit?id=" + id;
	}

	function del(id) {
		$.getJSON('/system/menu/delete', {
			"id" : id
		}, function(json) {
			if (json.status == '200') {
				alert(json.msg);
				location.href = '/system/menu'
			}
		});
	}

	function tark(url) {
		location.href = url;
	}
</script>
</@frame>
