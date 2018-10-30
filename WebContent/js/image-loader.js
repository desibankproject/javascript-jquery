	
function editRow(sno){
	//access the row object to be edited
	var rowObj=$("#rowdata"+sno);
	$("#spsno").val(sno);
    var name = rowObj.find("td:nth-child(2)").text();
    var email = rowObj.find("td:nth-child(3)").text();
    var mobile = rowObj.find("td:nth-child(4)").text();
    var gender = rowObj.find("td:nth-child(5)").text();
    $("#addStudentModal").modal("show");
    $("#name").val(name);
    $("#email").val(email);
    $("#mobile").val(mobile);
    $("#gender").val(gender);
    $("#ephoto").attr("src","image-loader?sno="+sno);
	alert("Editing the row!!!!!!!!!!");
}

function deleteRow(sno) {
		//$.ajax , ACCEPT= JSON,METHOD=GET
		/*$.getJSON("delete-profile?sno="+sno,function(data){
			if(data.status=="success"){
				$("#rowdata"+sno).hide();
			}
			$("#appmessage").html(data.message);
		});*/
			$.ajax({url:"delete-profile?sno="+sno, type: 'GET',success:function(data) {
				if(data.status=="success"){
					$("#rowdata"+sno).hide();
				}
				$("#appmessage").html(data.message);
			}});
	}

//Ready Handler is function which will be  called when dom is loaded into the memory!
		 $(document).ready(function(){
				//All the jQuery code we will write here
				//document.elementById("load");
				$("#load").click(function(){
					//fetching JSON data from txt file
					var  url="students"
					$.getJSON("students",function(data){ //data will hold json data as a JavaScript object
						console.log(data);
						 //data is Array of JavaScript object	
						 //console.log(data);
						/*  data.forEach(pdata=>{
							console.log(pdata);
						 }) */
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
					});

				});
				
				$("#addStudent").click(function(){
					$("#addStudentModal").modal("show");
				});
				
				
				$("#upload").click(function(){
					//here we can write code for validation!
						alert(")@#)RAHUL#)#)#");
						//SPECIAL CODE TO SUBMITTING FORM DATA WITH IMAGE ATTACHMENT USING AJAX AND
						//THEN GETTING JSON RESPONSE!
						//FormData is predefine Function constructor inside JavaScript
						   //imageUploadAjaxForm is my form id and using it we are creating form data
							//which we will send through ajax
							//processData: false,
                        	//contentType: false, ->> to send image as attachment in AJAX
		                   var myForm = new FormData(document.getElementById('imageUploadAjaxForm'));
		                   //var myForm = $("#imagePopupFormId").serialize();
		                   $.ajax({url : "profileUpload",
		                             dataType: 'json',
		                             data : myForm,
		                             processData: false,
		                             contentType: false,
		                             type : 'POST',
		                             success : function(cdata) {
		                            	     // String json = gson.toJson(applicationMessage);
		                                 		//response.getWriter().println(json); 
		                            	 	  //cdate=json	
		                                     $("#appmessage").html(cdata.message);
		                                      //close the modal using jQuery
		                                     $("#addStudentModal").modal("hide");
		                                     var psno=$("#spsno").val();
		                                     if(psno!=0){
		                                    	 $("#pimage"+psno).attr("src","image-loader?sno="+psno+"& _=" + new Date().getTime());
		                                     }
		                             }
		                   });

					 
				});
				
				
				
				
	     });
	