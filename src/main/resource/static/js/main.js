$(function() {
	$('#content').load('/daily');
	
	$('#menu_ul').tree({accordion:false});
});

function load(url,obj) {
	remove_menu_active();
	obj.parent().addClass('active');
	$('#content').load(url);
}

function remove_menu_active(){
	$('#menu_ul').find('li').each(function(idx,obj){
		if($(obj).hasClass('active'))
			$(obj).removeClass('active');
	});
}
