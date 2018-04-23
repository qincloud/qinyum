<#include "/include/frame.ftl" />
<@frame title="机构管理">
<script>
	
</script>
<div class="card">
	<div class="card-body" style="float: left; width: 61.8%;">
		<form id="roleform" action="/system/role/saveorupdate">
			<div class="form-group">
				<label for="exampleInputEmail1">角色名</label> <input type="text"
					class="form-control" id="name" name="name" value="${(model.name)!}"
					placeholder=""><input type="hidden" id="id" name="id"
					value="${(model.id)!}" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">角色英文名</label> <input type="text"
					class="form-control" id="ename" name="ename"
					value="${(model.ename)!}" placeholder=""><input
					type="hidden" id="ids" name="ids" />
			</div>
			<div class="form-group">
				<div class="col-lg-9 col-lg-offset-3">
					<button type="submit" class="btn btn-info">保存</button>
					<a href="/system/role" class="btn btn-info">返回</a>
				</div>
			</div>
		</form>
	</div>
</div>
</@frame>