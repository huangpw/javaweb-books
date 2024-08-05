<%--
  Created by IntelliJ IDEA.
  User: AlbertHPW
  Date: 2024/8/4
  Time: 2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria- live="polite"
             aria-relevant="all">显示 ${ pageUtils.start + 1 } 到 ${pageUtils.end}
            项，共 ${pageUtils.totalCount} 项
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">
                <c:if test="${requestScope.pageUtils.pageNum == 1}">
                    <li class="paginate_button previous disabled" aria-controls="DataTables_Table_0"
                        tabindex="0" id="DataTables_Table_0_previous">
                        <a href="#">上一页</a>
                    </li>
                </c:if>
                <c:if test="${requestScope.pageUtils.pageNum != 1}">
                    <li class="paginate_button previous" aria-controls="DataTables_Table_0"
                        tabindex="0" id="DataTables_Table_0_previous">
                        <a href="javascript:void(0)" onclick="goPre()">上一页</a>
                    </li>
                </c:if>

                <c:forEach items="${requestScope.pageUtils.pageList}" var="page">
                    <c:if test="${page == requestScope.pageUtils.pageNum.toString()}">
                        <li class="paginate_button active" aria-controls="DataTables_Table_0"
                            tabindex="0">
                            <a href="javascript:void(0)" onclick="goPage(${page})">${page}</a>
                        </li>
                    </c:if>
                    <c:if test="${page != requestScope.pageUtils.pageNum.toString()}">
                        <li class="paginate_button " aria-controls="DataTables_Table_0"
                            tabindex="0">
                            <a href="javascript:void(0)" onclick="goPage(${page})">${page}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${requestScope.pageUtils.pageNum == requestScope.pageUtils.totalPage}">
                    <li class="paginate_button next disabled" aria-controls="DataTables_Table_0"
                        tabindex="0" id="DataTables_Table_0_next">
                        <a href="#">下一页</a>
                    </li>
                </c:if>
                <c:if test="${requestScope.pageUtils.pageNum != requestScope.pageUtils.totalPage}">
                    <li class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_next">
                        <a href="javascript:void(0)" onclick="goNext()">下一页</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<script>
    // 上一页
    function goPre() {
        // 前一页：当前页-1
        $("#pageNum").val(${requestScope.pageUtils.pageNum - 1});
        // 提交表单
        $("#myForm").submit();
    }
    // 跳转到指定页
    function goPage(page) {
        if(page === '...') return
        // 前一页：当前页-1
        $("#pageNum").val(page);
        // 提交表单
        $("#myForm").submit();
    }
    // 下一页
    function goNext() {
        // 下一页：当前页+1
        $("#pageNum").val(${requestScope.pageUtils.pageNum + 1});
        // 提交表单
        $("#myForm").submit();
    }
</script>
