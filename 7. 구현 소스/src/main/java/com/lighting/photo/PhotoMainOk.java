/*
 * package com.lighting.photo;
 * 
 * import java.io.IOException; import java.io.PrintWriter;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import com.lighting.photo.model.PhotoDAO; import
 * com.lighting.photo.model.PhotoDTO; import
 * com.oreilly.servlet.MultipartRequest; import
 * com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
 * 
 * @WebServlet("/photo/photomainok.do") public class PhotoMainOk extends
 * HttpServlet {
 * 
 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
 * resp) throws ServletException, IOException { try { // 이미지 저장 경로 String path =
 * req.getServletContext().getRealPath("/images");
 * System.out.println("Upload path: " + path);
 * 
 * // 최대 파일 크기 설정 (10MB) int size = 1024 * 1024 * 10;
 * 
 * // MultipartRequest 객체 생성
 * 
 * 
 * MultipartRequest multi = new MultipartRequest( req, path, size, "UTF-8", new
 * DefaultFileRenamePolicy() );
 * 
 * System.out.println(10);
 * 
 * // 파라미터 가져오기 String tblattachedphotoseq =
 * multi.getParameter("tblattachedphotoseq"); String photofilename =
 * multi.getFilesystemName("photofilename"); // 실제 파일 이름 가져오기
 * 
 * // DAO와 DTO 객체 생성 PhotoDAO dao = new PhotoDAO(); PhotoDTO dto = new
 * PhotoDTO();
 * 
 * // DTO에 데이터 설정 dto.setTblattachedphotoseq(tblattachedphotoseq);
 * dto.setPhotofilename(photofilename);
 * 
 * // 데이터베이스에 사진 정보 저장 int result = dao.addPhoto(dto);
 * 
 * // 결과에 따라 리다이렉트 또는 실패 메시지 처리 if (result == 1) {
 * resp.sendRedirect("/photo/photomain.do"); } else { sendFailureResponse(resp);
 * }
 * 
 * } catch (Exception e) { e.printStackTrace(); sendFailureResponse(resp); } }
 * 
 * // 실패 시 사용자에게 메시지를 보여주는 메서드 private void
 * sendFailureResponse(HttpServletResponse resp) throws IOException {
 * PrintWriter writer = resp.getWriter(); writer.print(""" <script> alert('업로드에
 * 실패했습니다.'); history.back(); </script> """); writer.close(); } }
 */