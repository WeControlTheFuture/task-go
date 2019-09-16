var randomPics = [];
$(function() {
	$("#todo_list_box").boxWidget({ collapseTrigger : '[data-widget="collapse"]' });
	$('.todo-list').sortable({ placeholder : 'sort-highlight', handle : '.handle', forcePlaceholderSize : true, zIndex : 999999 });

	/* The todo list plugin */
	$('.todo-list').todoList({ onCheck : function() {
		window.console.log($(this), 'The element has been checked');
	}, onUnCheck : function() {
		window.console.log($(this), 'The element has been unchecked');
	} });

});

function show_do_task(id) {
	$('#do_task_div').load('/task/detail?id=' + id);
}