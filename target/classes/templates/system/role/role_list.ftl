<#include "/include/frame.ftl" /> <@frame title="角色管理">

<div class="card">
	<div class="card-header">
		<div class="card-title">
			<a href="/system/role/add" class="btn btn-info">添加角色</a>
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
					<td style="width: 22.5%">角色名</td>
					<td style="width: 22.5%">角色英文名</td>
					<td style="width: 22.5%">创建时间</td>
					<td style="width: 22.5%">操作</td>
				</tr>
			</thead>
			<tbody>
				<#list pageInfo.list as x>
				<tr>
					<td><input type="checkbox" class="check" id="${(x.id)!}" /></td>
					<td style="width: 10%">${x_index+(pageInfo.startRow)!0}</td>
					<td style="width: 15%">${(x.name)!}</td>
					<td style="width: 15%">${(x.ename)!}</td>
					<td style="width: 15%">${(x.create_time())!}</td>
					<td style="width: 15%"><a href="/system/role/edit?id=${(x.id)!}"
							 class="btn btn-success btn-sm">编辑</a>
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
<script type="text/javascript">
</script>
</@frame>
