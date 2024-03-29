<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <h1 class="h3 mb-0 text-gray-800">${board}</h1>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                </div>

                <!-- Main Contents -->
                <main class="row">
                    <table class="table table-primary table-hover table-striped">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Title</th>
                                <th>Writer</th>
                                <th>Date</th>
                                <th>Views</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${list}" var="vo">
                                <tr>
                                    <td>${vo.boardNum}</td>
                                    <td><a href="detail?boardNum=${vo.boardNum}">${vo.boardTitle}</a></td>
                                    <td>${vo.writer}</td>
                                    <td>${vo.boardDate}</td>
                                    <td>${vo.boardViews}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </main>

                <div class="align-content-center">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">

                            <li class="page-item ${pager.start ? "disabled" : ""}">
                                <a class="page-link" href="?page=${pager.startNum - 1}&kind=${pager.kind}&search=${pager.search}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>

                            <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
                            <li class="page-item">
                                <a class="page-link" href="?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a>
                            </li>
                            </c:forEach>

                            <li class="page-item ${pager.last ? "disabled" : ""}">
                                <a class="page-link" href="?page=${pager.lastNum + 1}&kind=${pager.kind}&search=${pager.search}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>

                        </ul>
                    </nav>
                    <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                        <a class="btn btn-primary" href="add">글쓰기</a>
                    </sec:authorize>
                </div>

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