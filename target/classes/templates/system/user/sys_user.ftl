<#include "/include/frame.ftl" /> <@frame title="用户管理">
<style>
</style>
<div class="card">
	<div class="card-header">
		<div class="card-title">
			<form action="/system/user">
				<div class="form-inline">
					<div class="form-group">
						<label for="exampleInputName2">用户名</label> <input type="text"
							class="form-control" id="logname" name="logname"
							value="${(model.logname)!}" placeholder="用户名">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">姓名</label> <input type="text"
							class="form-control" id="username" name="username"
							value="${(model.username)!}" placeholder="用户姓名">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">邮件</label> <input type="text"
							class="form-control" id="email" name="email"
							value="${(model.email)!}" placeholder="用户邮件">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">电话</label> <input type="text"
							class="form-control" id="phone" name="phone"
							value="${(model.phone)!}" placeholder="用户电话">
					</div>

				</div>
				<div class="form-inline" style="text-align: right;">
					<button type="submit" class="btn btn-info">搜素</button>
					<a href="#" class="btn btn-info">清空</a> <a href="/system/user/add"
						class="btn btn-info">添加用户</a>
				</div>
			</form>
		</div>
	</div>

	<div class="card-body">
		<table
			class="table table-striped table-bordered table-hover table-condensed"
			width="100%">
			<thead class="">
				<tr class="active">
					<td style="width: 5%"><input type="checkbox" class="checkall" /></td>
					<td style="width: 5%">序号</td>
					<td style="width: 15%">用户名</td>
					<td style="width: 15%">姓名</td>
					<td style="width: 15%">邮件</td>
					<td style="width: 15%">电话</td>
					<td style="width: 15%">创建时间</td>
					<td style="width: 15%">操作</td>
				</tr>
			</thead>
			<tbody>
				<#list pageInfo.list as x>
				<tr>
					<td><input type="checkbox" class="check" id="${(x.id)!}" /></td>
					<td style="width: 10%">${x_index+(pageInfo.startRow)!0}</td>
					<td style="width: 15%">${(x.logname)!}</td>
					<td style="width: 15%">${(x.username)!}</td>
					<td style="width: 15%">${(x.email)!}</td>
					<td style="width: 15%">${(x.phone)!}</td>
					<td style="width: 15%">${(x.create_time())!}</td>
					<td style="width: 15%"><button href="#"
							onclick="edit('${(x.id)!}')" class="btn btn-success btn-sm">编辑</button>
						<button onclick="del('${(x.id)!}','${(x.username)!}')"
							class="btn btn-danger btn-sm">删除</button></td>
				</tr>
				</#list>
			</tbody>
			<tfooter>
			<tr>
				<td>
					<div style="text-align: left;">
						<a href="#" class="btn btn-info" onclick="delAll()">删除所选</a>
					</div>
				</td>
				<td colspan="7">
					<ul class="pagination" url='/system/user'
						style="text-align: right;"></ul>
				</td>
			</tr>
			</tfooter>
		</table>
	</div>
</div>
<script>
	function edit(id) {
		location.href = "/system/user/edit?id=" + id;
	}
	
	function del(id, xm) {
		var submit = function(v, h, f) {
			if (v == 'ok') {
				$.post('/system/user/delete', {
					r : Math.random(),
					'id' : id
				}, function(json) {
					if (json.status == 200) {
						location.href = "/system/user"
					} else {

					}
				});
			} else if (v == 'cancel') {
			}
			return true; //close
		};
		$.jBox.confirm("确定要删除" + xm + "吗？", "提示", submit);
	}

	function delAll() {

		var $cd = $(".check:checked");
		if ($cd.size() == 0) {
			return;
		}

		var result = "";
		$cd.each(function() {
			result += $(this).attr("id") + ",";
		});

		var submit = function(v, h, f) {
			if (v == 'ok') {
				$.post('/system/user/deleteall', {
					r : Math.random(),
					'ids' : result
				}, function(json) {
					if (json.status == 200) {
						location.href = "/system/user"
					} else {

					}
				});
			} else if (v == 'cancel') {
			}
			return true;
		};
		$.jBox.confirm("确定要删除选择的" + $cd.size() + "个用户吗？", "提示", submit);
	}
</script>
</@frame>
