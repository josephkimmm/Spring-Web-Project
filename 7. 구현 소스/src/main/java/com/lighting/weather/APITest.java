package com.lighting.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APITest {
    public static void main(String[] args) throws Exception {

        // 변수 설정
        String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String authKey = "j6HKCi8s4LL9mRV%2FFfnurB8qjGDnvcJkoWOnJXH8q1VIqhraXMaTUGgtv7FDv7Sp2DhrSaDGzvNo599YB16w%2BQ%3D%3D"; // 본인 서비스 키

        // 구하고자 하는 시간과 좌표 대입
        String nx = "61";
        String ny = "125";
        String baseDate = "20250404";
        String baseTime = "0800";

        String dataType = "JSON";

        StringBuilder urlBuilder = new StringBuilder(apiURL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + authKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows=10", "UTF-8"));    // 표 개수
        urlBuilder.append("&" + URLEncoder.encode("pageNo=1", "UTF-8"));    // 페이지 수
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); // 받으려는 타입
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); // 조회하고 싶은 날짜
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); // 조회하고싶은 시간
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); // x좌표
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); // y좌표

        
        
        
        URL url = new URL(urlBuilder.toString());
        //System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();

        // 테스트를 위해 출력
        System.out.println(result);
        
     // JSON 파서를 생성합니다.
        JSONParser parser = new JSONParser();
        try {
            // 루트 객체 파싱
            JSONObject root = (JSONObject) parser.parse(result);
            // response 객체 추출
            JSONObject response = (JSONObject) root.get("response");
            // body 객체 추출
            JSONObject body = (JSONObject) response.get("body");
            // items 객체 추출
            JSONObject items = (JSONObject) body.get("items");
            // item 배열 추출
            JSONArray itemArray = (JSONArray) items.get("item");
            
            // 온도와 강수 형태 값을 저장할 변수
            String temperature = null;
            String precipitationType = null;
            String humidity = null;             // 습도
            String oneHourPrecipitation = null;   // 한시간 강수량
            
            // item 배열을 순회하며 필요한 데이터를 추출합니다.
            for (Object obj : itemArray) {
                JSONObject item = (JSONObject) obj;
                String category = (String) item.get("category");
                // 각 항목의 관측값은 "obsrValue" 필드에 있습니다.
                String obsrValue = item.get("obsrValue").toString();
                
                //초단기 실황
                // 온도: T1H ℃
                if ("T1H".equals(category)) {
                    temperature = obsrValue;
                }
                // 강수형태: PTY (0: 없음, 1: 비, 2: 비/눈, 3: 눈 등, 상세 코드는 기상청 문서 참조)
                //(초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7) 
                if ("PTY".equals(category)) {
                    precipitationType = obsrValue;
                }
                //습도 : %
                if ("REH".equals(category)) {
                    humidity = obsrValue;
                }
                //1시간 강수량 : mm
                if ("RN1".equals(category)) {
                    oneHourPrecipitation = obsrValue;
                }
            }
            
            // 결과 출력
            System.out.println("온도 : " + temperature +"℃");
            System.out.println("강수형태: " + precipitationType+" 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7) ");
            System.out.println("습도: " + precipitationType+"%");
            System.out.println("1시간 강수량: " + precipitationType+"mm");
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
