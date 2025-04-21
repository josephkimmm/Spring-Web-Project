package com.lighting.gallery;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lighting.gallery.model.GalleryDAO;
import com.lighting.gallery.model.GalleryDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/gallery/galleryok.do")
public class GalleryOk extends HttpServlet {

    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        try {
        	
            HttpSession session = req.getSession();
            String tblMemberSeq = (String) session.getAttribute("auth");
            
            if (tblMemberSeq != null && !tblMemberSeq.equals("")) {
            
                // 이미지 저장 경로
                String path = req.getServletContext().getRealPath("/images");  
                System.out.println("Upload path: " + path);  
    
                // 최대 파일 크기 설정 (10MB)
                int size = 5120 * 5120 * 50;  
    
                // MultipartRequest 객체 생성  
                MultipartRequest multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());  
    
                // 파라미터 가져오기  
                String tblphotopostseq = multi.getParameter("tblphotopostseq");  
                String photofilename = multi.getFilesystemName("imageUpload");
                
                System.out.println(photofilename);
    
                // DAO와 DTO 객체 생성  
                GalleryDAO dao = new GalleryDAO();  
                GalleryDTO dto = new GalleryDTO();  
    
                // DTO에 데이터 설정
                
                //System.out.println(tblmemberseq);
                dto.setTblmemberseq(tblMemberSeq);  
                dto.setPhotofilename(photofilename);  
    
                // 데이터베이스에 사진 정보 저장  
                int result = dao.addPhoto(dto);
                
                System.out.println(1); // 확인용  
    
                // 결과에 따라 리다이렉트 또는 실패 메시지 처리  
                if (result == 1) {
    				resp.sendRedirect("/lighting/gallery/gallerymain.do");
    			} else {
    				PrintWriter writer = resp.getWriter();
    				writer.print("""
    						<script>
    							alert('failed');
    							history.back();
    						</script>
    						""");
    				writer.close();
    			}

           // System.out.println(2); // 확인용  
            } else {
                req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
            }
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
            sendFailureResponse(resp);  
        }  
        
    }  

    
    // 실패 시 사용자에게 메시지를 보여주는 메서드  
    private void sendFailureResponse(HttpServletResponse resp) throws IOException {  
    	
    	resp.setCharacterEncoding("UTF-8");
    	resp.setContentType("application/json, UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print("""  
                <script>
                    alert('업로드에 실패했습니다.');  
                    history.back();  
                </script>  
                """);  
        writer.close();  
        
        //System.out.println(9); // 확인용  
    }
}
