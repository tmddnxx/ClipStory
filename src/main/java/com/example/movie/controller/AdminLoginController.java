package com.example.movie.controller;

import com.example.movie.model.dto.AdminDTO;
import com.example.movie.service.AdminService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login/admin")
@Log4j2
public class AdminLoginController extends HttpServlet {
    AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Admin GET...");
        req.getRequestDispatcher("/WEB-INF/login/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Admin POST...");

        try {
            AdminDTO adminDTO = adminService.adminLogin(req);
            log.info(adminDTO);
            if (adminDTO != null) {
                HttpSession session = req.getSession();
                session.setAttribute("superInfo", adminDTO);
                session.setAttribute("superId", adminDTO.getSuperId());
                log.info(adminDTO);
                resp.sendRedirect("/WEB-INF/admin/main.jsp");
                return;
            }
        } catch (Exception e) {
            log.error("관리자가 아닌 경우 : " + e.getMessage());
            resp.sendRedirect("/admin?result=error");
            return;
        }
        resp.sendRedirect("/admin?result=error");
    }
}