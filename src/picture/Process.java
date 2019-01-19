package picture;

import java.util.Arrays;

public class Process {

  Picture pic;
  Picture[] pics;

  Process(Picture picture) {
    this.pic = picture;
  }
  Process(Picture[] pics){
    this.pics = pics;
  }

  public void invert() {
    int width = pic.getWidth();
    int height = pic.getHeight();
    int maxIntensity = 255;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color color = pic.getPixel(x, y);
        color.setRed(maxIntensity - color.getRed()); //keep it an int 1) change color 2) get int
        color.setGreen(maxIntensity - color.getGreen());
        color.setBlue(maxIntensity - color.getBlue());
        pic.setPixel(x, y, color);
      }
    }

  }

  public void grayScale() {
    int width = pic.getWidth();
    int height = pic.getHeight();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color color = pic.getPixel(x, y);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int avg = (red + green + blue) / 3;
        color.setRed(avg);
        color.setGreen(avg);
        color.setBlue(avg);
        pic.setPixel(x, y, color);
      }
    }
  }

  public void rotate(String degree) {

    if (degree.equals("90")) {
      rotate90();
    }
    if (degree.equals("180")) {
      rotate90();
      rotate90();
    }
    if (degree.equals("270")) {
      rotate90();
      rotate90();
      rotate90();
    }
  }

  public void rotate90() {
    int width = pic.getWidth();
    int height = pic.getHeight();
    Picture newPic = Utils.createPicture(height, width);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newPic.setPixel(height - y - 1, x, pic.getPixel(x,y));
      }
    }
    this.pic = newPic;
  }

  public void flip(String str){
    if (str.equals("H")){
      flipH();
    }
    else{
      flipV();
    }
  }

  public void flipV() {
    int width = pic.getWidth();
    int height = pic.getHeight();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height / 2; y++) {
        Color c = pic.getPixel(x,y);
        pic.setPixel(x,y, pic.getPixel(x,height - y - 1));
        pic.setPixel(x,height - y - 1, c);
      }
    }
  }
  public void flipH(){
    int width = pic.getWidth();
    int height = pic.getHeight();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width / 2; x++) {
        Color c = pic.getPixel(x, y);
        pic.setPixel(x,y, pic.getPixel(width - x - 1, y));
        pic.setPixel(width - x - 1,y, c);
      }
    }
  }
  public void blend() {
    int n = pics.length;
    int[] widths = new int[n];
    int[] heights= new int[n];
    for (int i = 0; i < n; i++){
      widths[i] = pics[i].getWidth();
      heights[i] = pics[i].getHeight();
    }
    Arrays.sort(widths);
    Arrays.sort(heights);
    int minWidth = widths[0];
    int minHeight = heights[0] ;
    Picture newPic = Utils.createPicture(minWidth, minHeight);
    for (int x = 0; x < minWidth; x ++){
      for (int y = 0; y < minHeight; y++){
        int redAcc = 0;
        int greenAcc = 0;
        int blueAcc = 0;
        for (int p = 0; p < n; p++){
              Color color = pics[p].getPixel(x, y);
              redAcc += color.getRed();
              greenAcc += color.getGreen();
              blueAcc += color.getBlue();
        }
        Color c = new Color(redAcc /n, greenAcc / n, blueAcc / n);
        newPic.setPixel(x, y, c);
      }
    }
    this.pic = newPic;
  }

  public void blur() {
    int width = pic.getWidth();
    int height = pic.getHeight();
    Picture newPic = Utils.createPicture(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (x >= 1 && x < width - 1 && y >= 1 && y < height - 1) {
          newPic.setPixel(x,y, blurPoint(x,y));
        } else {
          newPic.setPixel(x, y, pic.getPixel(x, y));
        }
      }
    }
    this.pic = newPic;
  }
  private Color blurPoint(int x, int y) {
    int redAcc = 0;
    int greenAcc = 0;
    int blueAcc = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        Color color = pic.getPixel(i, j);
        redAcc += color.getRed();
        greenAcc += color.getGreen();
        blueAcc += color.getBlue();

      }
    }
    return new Color(redAcc / 9, greenAcc / 9, blueAcc / 9);
  }
public Picture getPicture(){
    return pic;
}

}
