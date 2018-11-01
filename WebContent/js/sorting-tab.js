
function showData(data){
	 var tbcontents="";
	 for(var x=0;x<data.length;x++){
		console.log(data[x]);
		tbcontents=tbcontents+'<tr id="rowdata'+data[x].sno+'">';
		tbcontents=tbcontents+'<td>'+(x+1)+'</td>';
		tbcontents=tbcontents+'<td>'+data[x].name+'</td>';
		tbcontents=tbcontents+'<td>'+data[x].email+'</td>';
		tbcontents=tbcontents+'<td>'+data[x].mobile+'</td>';
		tbcontents=tbcontents+'<td>'+data[x].gender+'</td>';
		tbcontents=tbcontents+'<td><img id="pimage'+data[x].sno+'" src="image-loader?sno='+data[x].sno+'" style="height:120px;"/>';
		tbcontents=tbcontents+'<a href="javascript:deleteRow('+data[x].sno+');"><img src="delete.png" style="height:40px;"/></a>';
		tbcontents=tbcontents+'<a href="javascript:editRow('+data[x].sno+');"><img src="edit.png" style="height:40px;"/></a>';
		tbcontents=tbcontents+'</td></tr>';
	 }
	 $("#tcontents").html(tbcontents);
}

$(document).ready(function(){
	$("#dsorting").click(function(){
			
			$.getJSON("sort-student?sort=desc",function(data){ //data will hold json data as a JavaScript object
				showData(data);
				$("#dsorting").hide();
				$("#asorting").show();
			});
	});
	
	$("#asorting").click(function(){
		$.getJSON("sort-student?sort=asc",function(data){ //data will hold json data as a JavaScript object
			showData(data);
			$("#dsorting").show();
			$("#asorting").hide();
		});
});
});