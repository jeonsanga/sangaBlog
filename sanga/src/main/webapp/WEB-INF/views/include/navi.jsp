<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>

<script src="/js/jquery/jquery.cookie.js"></script>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<body>



    <!-- Nav Item - Pages Collapse Menu -->
    
    

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Login Screens:</h6>
                        <a class="collapse-item" href="/user/login">Login</a>
                        <a class="collapse-item" href="/user/register">Register</a>
                        <a class="collapse-item" href="/user/forgot-password">Forgot Password</a>
                        <div class="collapse-divider"></div>
                        <h6 class="collapse-header">Other Pages:</h6>
                        <a class="collapse-item" href="404.html">404 Page</a>
                        <a class="collapse-item" href="blank.html">Blank Page</a>
                    </div>
                </div>
            </li>
            
        <!-- Nav Item - Charts -->
           <li class="nav-item">
               <a class="nav-link" href="/jsoup/charts">
                   <i class="fas fa-fw fa-chart-area"></i>
                   <span>Charts</span></a>
           </li>

           <!-- Nav Item - Tables -->
           <li class="nav-item">
               <a class="nav-link" href="/board/tables">
                   <i class="fas fa-fw fa-table"></i>
                   <span>Tables</span></a>
           </li>

</body>
