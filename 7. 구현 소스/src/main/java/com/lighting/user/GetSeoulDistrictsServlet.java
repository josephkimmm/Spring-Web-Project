package com.lighting.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/getSeoulDistricts")
public class GetSeoulDistrictsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] seoulDistricts = {
            "강남구", "강동구", "강북구", "강서구", "관악구", "광진구",
            "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구",
            "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구",
            "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"
        };

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print("[");
        for (int i = 0; i < seoulDistricts.length; i++) {
            out.print("\"" + seoulDistricts[i] + "\"");
            if (i < seoulDistricts.length - 1) {
                out.print(", ");
            }
        }
        out.print("]");
        out.flush();
        out.close();
    }
}

