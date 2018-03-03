/*
추후 추가될 js안에 동일한 이름의 function이 존재할 경우 덮어써지는 것을
방지하기 위해 main 객체안에서 function 선언.
*/
var main = {
		init : function(){
			var _this = this;
			$('#btn-save').on('click', function(){
				_this.save();
			});
		},
		
		save : function(){
			var data = {
					title: $('#title').val(),
					author: $('#author').val(),
					content: $('#content').val()
			};
			
			$.ajax({
				type: 'POST',
				url: '/posts',
				dataType: 'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function(result){
//				console.log(result); // 등록된 entity id
				alert('글이 등록되었습니다.');
				location.reload();
			}).fail(function(error){
				alert(error);
			});
		}
};

main.init();