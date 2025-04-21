package com.lighting.meeting;

public class MySetScoreImage {
    
    public String mySetScoreImage(String score) {
       
        double num = Double.parseDouble(score);
        String scoreImage = "";
        
        if (num < 2) {
            scoreImage = "실버.png";
        } else if (num < 3) {
            scoreImage = "골드.png";
        } else if (num < 4) {
            scoreImage ="다이아.png";
        } else if (num <= 5) {
            scoreImage ="마스터.png";
        } else {
            scoreImage = "닫기.png";
        }
        
        return scoreImage;
    }
    
}
