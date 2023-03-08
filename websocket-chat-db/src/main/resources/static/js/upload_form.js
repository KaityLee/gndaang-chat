function upload_item(){
  var form = $('#upload-form')[0]
	    var data = new FormData(form);
		
		$.ajax({
				url : '/board/upload',	
				method : 'post',
				enctype: 'multipart/form-data',
				data : data,  											
				cache : false,		
				dataType:'json',        
				processData: false,  
			    contentType: false,
				success : function(res)                
				{									
					alert(res.msg);
					location.href='/board/list';
									
				},
				error : function(xhr,status,err)
				{
					alert(err);	
				}
				});
}