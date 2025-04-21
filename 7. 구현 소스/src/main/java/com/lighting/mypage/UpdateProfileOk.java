package com.lighting.mypage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.lighting.mypage.model.MemberDAO;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1MB
    maxFileSize = 1024 * 1024 * 5,       // 5MB
    maxRequestSize = 1024 * 1024 * 15     // 15MB
)
@WebServlet("/mypage/updateprofileok.do")
public class UpdateProfileOk extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Object userSeqObj = session.getAttribute("auth");

        if (userSeqObj == null) {
            response.sendRedirect("/lighting/user/login.do");
            return;
        }

        int userSeq = (userSeqObj instanceof Integer)
            ? (Integer) userSeqObj
            : Integer.parseInt(userSeqObj.toString());

        // 업로드된 이미지 파일
        Part filePart = request.getPart("profileImage");
        String fileName = getFileName(filePart); // 원본 파일명

        // 저장할 파일명 (중복 방지를 위해 유저 번호 붙임)
        String savedFileName = "profile_" + userSeq + "_" + System.currentTimeMillis() + "_" + fileName;

        // 이미지 저장 경로
        String realPath = request.getServletContext().getRealPath("/images");
        File saveDir = new File(realPath);
        if (!saveDir.exists()) saveDir.mkdirs();

        // 파일 저장
        filePart.write(realPath + File.separator + savedFileName);

        // DB 업데이트
        MemberDAO dao = new MemberDAO();
        int result = dao.updateProfileImage(userSeq, savedFileName);

        // 응답
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (result == 1) {
            writer.print("{ \"status\": \"success\", \"fileName\": \"" + savedFileName + "\" }");
        } else {
            writer.print("{ \"status\": \"fail\" }");
        }
        writer.close();
    }

    // 파일 이름 추출 함수
    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        for (String s : header.split(";")) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
