<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<jsp:include page="../includes/head.jsp" />
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="../includes/header.jsp" />
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Board Register</h1>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Board Register</h6>
						</div>
						<div class="card-body">
							<form method="post">
								<div class="form-group">
									<label for="title" class="text-dark font-weight-bold">Title</label>
									<input class="form-control" id="title" name="title">
								</div>
								<div class="form-group">
									<label for="content" class="text-dark font-weight-bold">Content</label>
									<textarea rows="10" class="form-control" id="content" name="content"></textarea>
								</div>
								<div class="form-group">
									<label for="writer" class="text-dark font-weight-bold">Writer</label>
									<input class="form-control" id="writer" name="writer">
								</div>
								<button class="btn btn-primary" id="btnSubmit">Submit</button>
								<button class="btn btn-danger" type="reset">Reset</button>
							</form>
						</div>
					</div>
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Attach Register</h6>
						</div>
						<div class="card-body">
							<div class="form-group uploadDiv">
								<label for="files" class="btn btn-primary px-4"><i class="far fa-file mr-2"></i> File</label>
								<input type="file" class="form-control d-none" id="files" name="files" multiple>
							</div>
							<div class="uploadResult">
								<ul class="list-group">
								</ul>
							</div>
						</div>
					</div>
					
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<jsp:include page="../includes/footer.jsp" />
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->
<script>
	function showImage(fileCallPath) {
		$("#pictureModal")
			.find("img").attr("src", "/display?fileName=" + fileCallPath)
		.end().modal("show");
	}
    $(function() {
    	var cloneObj = $(".uploadDiv").clone();
    	
        var regex = /(.*?)\.(exe|sh|zip|alz)$/;
        var maxSize = 1024 * 1024 * 5;

        function checkExtension(fileName, fileSize){
            if(fileSize >= maxSize){
                alert("?????? ????????? ??????");
                return false;
            }
            
            if(regex.test(fileName)){
                alert("?????? ????????? ????????? ???????????? ??? ????????????.");
                return false;
            }
            return true;
        }
        
        function showUploadedFile(resultArr) {
			var str = "";
			for(var i in resultArr) {
				str += "<li class='list-group-item' "
				str += "data-uuid='" + resultArr[i].uuid+ "' ";
				str += "data-origin='" + resultArr[i].origin+ "' ";
				str += "data-path='" + resultArr[i].path+ "' " ;
				str += "data-size='" + resultArr[i].size+ "' "
				str += "data-image='" + resultArr[i].image+ "' "
				str += "data-mime='" + resultArr[i].mime+ "' "
				str += "data-ext='" + resultArr[i].ext+ "' "
				str += ">"
				if(resultArr[i].image) {
					str += "<a href='javascript:showImage(\"" + resultArr[i].fullPath + "\")'>";
					str += "<img src = '/display?fileName=" + resultArr[i].thumb + "'>";
					str += "</a>";
				}
				else {
					str += "<a href='/download?fileName=" + resultArr[i].fullPath + "'>";
					str += "<i class='fas fa-paperclip'></i>" + resultArr[i].origin + "</a>";
				}
				str += "<small><i data-file='" + resultArr[i].fullPath + "'  data-image='" + resultArr[i].image +"'";
				str += "class='fas fa-trash-alt text-danger'></i></small></li>";
			}
			$(".uploadResult ul").append(str);
		}
        

        $(".uploadDiv").on("change", "#files", function() {
            var files = $("#files")[0].files;
            console.log(files);

            var formData = new FormData();
            for(var i in files) {
                if(!checkExtension(files[i].name, files[i].size)){
                    return false;
                }
              formData.append("files", files[i]);
            }

            $.ajax("/upload", {
                processData:false,
                contentType:false,
                data:formData,
                dataType:'json',
                type:"POST",
                success:function(result){
                    console.log(result);
                    $(".uploadDiv").html(cloneObj.html());
                    showUploadedFile(result);
                }
            })
        });
        
        $(".uploadResult").on("click","small i", function() {
	        var $li = $(this).closest("li");
        	$.ajax("/deleteFile", {
        		type : "post",
        		data : {fileName:$(this).data("file"), image:$(this).data("image")},
        		success : function (result) {
					if(result){
        				$li.remove();
        				// console.log(this); // this -> ajax ?????? ??? ??????
        			}
        		}      	
       		})
        });
        
        // ??? ?????? ?????????
        
        $("#btnSubmit").click(function () {
        	event.preventDefault();
        

        	var str ="";
        	var datas = ["uuid","path","origin","ext","mime","size","image"];
        	$(".uploadResult li").each(function(i) {
        		for(var j in datas)  // for(var j = 0 ; j< datas.length; j++)
        			str += "<input type='hidden' name='attachs["+i+"]." + datas[j] + "' value='" + $(this).data(datas[j]) + "'>";
        	});
        	 $(this).closest("form").append(str).submit();
//         	 console.log($(this).closest("form").append(str).html());
        })
    })
</script>

	<jsp:include page="../includes/foot.jsp" />
</body>

</html>