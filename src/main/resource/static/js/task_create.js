var cheditorObj;
$(function() {
	$("#create_task_box").boxWidget({ collapseTrigger : '[data-widget="collapse"]' });
	$('#stop_date_picker').datepicker({ autoclose : true, format : 'yyyy-mm-dd' });

	// text area object
	cheditorObj = CKEDITOR.replace('taskDescription');

	// init modal members when page ready
	$.ajax({
		type : 'POST',
		url : '/orgnization/members',
		contentType : 'application/json;charset=utf-8',
		success : function(result) {
			console.log(result);
			var modals_html = '';
			$.each(result, function(idx, obj) {
				modals_html += '<li><a onclick="task_assign_to_click(this,\'' + obj.code + '\')"><img src="' + obj.headpic + '" alt="User Image"> <span class="users-list-name">' + obj.name
						+ '</span></a></li>';
			});
			$("#assign_to_modal_ul").html(modals_html);
		} });

	$("#create_task").on("click", function() {
		var param = {};
		param.taskTitle = $("#inputTaskName").val();
		if ($('#stop_date_picker').val() != "")
			param.stopDate = $('#stop_date_picker').val();
		param.description = cheditorObj.getData();
		param.assignTo = $("#assign_to_input").attr('code');
		console.log(param);
		$.ajax({ type : 'POST', url : '/task/create', data : JSON.stringify(param), contentType : 'application/json;charset=utf-8', success : function(result) {
			if (result.code == 1) {
				clear_when_task_saved();
				$('#modal-success').modal('show');
			} else {
				console.log(result);
				$('#modal-danger').modal('show');
			}
		} });
	});
});

function task_assign_to_click(obj, code) {
	$('#assign_to_input').attr('code', code);
	$('#assign_to_input').attr('value', obj.text);
	$('#modal-assignto').modal('hide');
}

function clear_when_task_saved() {
	$('#task_form')[0].reset();
	$('#assign_to_input').removeAttr('code');
	$('#assign_to_input').removeAttr('value');
	CKEDITOR.instances.inputTaskDescription.setData('');
}