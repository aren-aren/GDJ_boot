<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>
    <c:import url="../temp/css.jsp"/>
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <c:import url="../temp/sidebar.jsp"/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <c:import url="../temp/topbar.jsp"/>
            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                </div>

                <!-- Main Contents -->
                <main class="row justify-content-center">
                    <form method="post" class="col-9" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="title" class="form-label">제목</label>
                            <input type="text" class="form-control" id="title" name="boardTitle" placeholder="제목을 입력하세요.">
                        </div>
                        <div class="mb-3">
                            <label for="writer" class="form-label">작성자</label>
                            <input type="text" class="form-control" id="writer" name="writer" placeholder="작성자를 입력하세요.">
                        </div>
                        <div class="mb-3">
                            <label for="contents" class="form-label">내용</label>
                            <textarea class="form-control" id="contents" name="boardContents" rows="3" placeholder="내용을 입력하세요"></textarea>
                        </div>

                        <div class="mb-3">
                            <input class="d-block form-control-file" type="file" name="attach">
                            <input class="d-block form-control-file" type="file" name="attach">
                            <input class="d-block form-control-file" type="file" name="attach">
                        </div>

                        <div class="mb-3">
                            <button class="btn btn-primary">작성하기</button>
                        </div>

                    </form>
                </main>

            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->
        <c:import url="../temp/footer.jsp"/>
    </div>
    <!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>


<c:import url="../temp/script.jsp"/>

</body>

</html>