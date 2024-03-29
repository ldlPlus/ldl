<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <security:authentication property="principal.username"/>
                </p>
                <a href="#"><em class="fa fa-circle text-success"></em> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a
                    href="${pageContext.request.contextPath}/pages/main.jsp"><em
                    class="fa fa-dashboard"></em> <span>首页</span></a></li>

            <li class="treeview"><a href="#"> <em class="fa fa-cogs"></em>
                <span>系统管理</span> <span class="pull-right-container"> <em
                        class="fa fa-angle-left pull-right"></em>
				</span>


            </a>
                <ul class="treeview-menu">

                    <li><a
                            href="${pageContext.request.contextPath}/user/list?page=1"> <em
                            class="fa fa-circle-o"></em> 用户管理
                    </a></li>
                    <li><a
                            href="${pageContext.request.contextPath}/role/list?page=1"> <em
                            class="fa fa-circle-o"></em> 角色管理
                    </a></li>
                    <li><a
                            href="${pageContext.request.contextPath}/pages/syslog-list.jsp"> <em
                            class="fa fa-circle-o"></em> 访问日志
                    </a></li>
                </ul>
            </li>
            <li class="treeview"><a href="#"> <em class="fa fa-cube"></em>
                <span>基础数据</span> <span class="pull-right-container"> <em
                        class="fa fa-angle-left pull-right"></em>
				</span>
            </a>
                <ul class="treeview-menu">

                    <li><a
                            href="#">
                        <em class="fa fa-circle-o"></em> 产品管理
                    </a></li>
                    <li><a
                            href="#">
                        <em class="fa fa-circle-o"></em> 订单管理
                    </a></li>

                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>