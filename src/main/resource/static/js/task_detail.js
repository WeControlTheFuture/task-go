//@ sourceURL=task_details.js
var progress_id = 0;
$(function() {
	$("#do_task_box").boxWidget({ removeTrigger : '[data-widget="remove"]' });
	var bodydiv = $("#do_task_box").find("div.box-body")[0];
	bodydiv.ondrop = function(e) {
		e.preventDefault();
		$(bodydiv).removeAttr("style", "");
		console.log(e.dataTransfer.files);
		var file = e.dataTransfer.files[0];
		var item = attechment_item(file.name);
		$("#attechment_ul").append(item.html);
		upload_attechment(file,item.progress_div_id,item.item_div_id);
	};
	bodydiv.ondragover = function(e) {
		$(bodydiv).css("border-style", "dashed");
		return false;
	}

	bodydiv.ondragleave = function(e) {
		$(bodydiv).removeAttr("style", "");
		return false;
	}
	
	$("#task_comment_submit").on("click", function() {
		var param = {};
		param.comment = $("#task_comment_input").val();
		param.id = $("#task_id").val();
		console.log(param);
		$.ajax({
			type:"post",
			data: param,
			url: "/task/comment",
			success:function(data){  
				console.log(data);
	        	if(data.code==0){
	        		refresh_task(param.id);
	        		console.log("评论成功!!!!");    
	        	}
			}
		});
	});
});

function refresh_task(id) {
	$('#do_task_div').load('/task/detail?id='+id);
}

function upload_attechment(file, progress_div_id,item_div_id){
	 var formData = new FormData();
	 var task_id = $("#task_id").val();
     formData.append("file",file);   
     formData.append("id",task_id);
     console.log(formData)
     $.ajax({
        type:"post",
        async:true, 
        Accept:'text/html;charset=UTF-8',
        data:formData,
        contentType:"multipart/form-data",
        url: "/task/attechment",
        processData: false,
        contentType: false,
        xhr:function(){                        
            myXhr = $.ajaxSettings.xhr();
            if(myXhr.upload){ // check if upload property exists
                myXhr.upload.addEventListener('progress',function(e){                            
                    var loaded = e.loaded;                  // 已经上传大小情况
                    var total = e.total;                      // 附件总大小
                    var percent = Math.floor(100*loaded/total)+"%";     // 已经上传的百分比
                    console.log("已经上传了："+percent);   
                    console.log(progress_div_id);
                    $("#"+progress_div_id).css("width",percent);
                    if(loaded == total){
                    	$("#"+progress_div_id).parent().remove();
                    }
                }, false); // for handling the progress of the upload
            }
            return myXhr;
        },                    
        success:function(data){  
        	console.log(data);
        	if(data.code==0){
        		refresh_task(task_id);
        		// $("#"+item_div_id).append('<span class="product-description"> '+data.userName+' 上传于 - '+data.createTs+'</span>');
        		console.log("上传成功!!!!");    
        	}
        },
        error:function(){
            alert("上传失败！");
        }
    });       
}

function attechment_item(filename) {
	var html = '<li class="item">';
	html += '<div class="product-img">';
	html += '<img src="icons/' + get_file_pic(filename) + '" alt="Product Image">';
	var item_div_id = "item_div_"+progress_id;
	html += '</div><div id="'+item_div_id+'" class="product-info">';
	html += '<a href="javascript:void(0)" class="product-title">' + filename + ' </a> ';
	var progress_div_id = 'progress_'+progress_id;
	progress_id +=1;
	html += '<div class="progress">';
	html += '<div id="'+progress_div_id+'" class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">';
	html += '</div></div>';
	html += '</div></li>';
	var result= {};
	result.html = html;
	result.progress_div_id = progress_div_id;
	result.item_div_id = item_div_id;
	return result;
}

function get_file_pic(filename) {
	var start_index = filename.lastIndexOf(".");
	var file_type = "";
	if (start_index != -1)
		file_type = filename.substring(start_index + 1, filename.length).toLowerCase();
	if (file_type == 'xls' || file_type == 'xlsx')
		return 'excel.jpg';
	else if (file_type == 'ppt')
		return 'ppt.jpg';
	else if (file_type == 'doc' || file_type == "docx")
		return "word.jpg";
	else if (file_type == 'pdf')
		return "pdf.jpg";
	else if (file_type == 'zip' || file_type == 'rar')
		return "zip.jpg";
	else
		return "unknown.jpg";
}
