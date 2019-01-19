package picture;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

public class Main {

  public static void main(String[] args) {
    String f = args[0];
    switch (f) {
      case "invert":
        Picture pic = Utils.loadPicture(args[1]);
        Process process = new Process(pic);
        process.invert();
        Utils.savePicture(pic, args[2]);
        break;
      case "grayscale":
        Picture pic2 = Utils.loadPicture(args[1]);
        Process process2 = new Process(pic2);
        process2.grayScale();
        Utils.savePicture(pic2, args[2]);
        break;
      case "rotate":
        Picture pic3 = Utils.loadPicture(args[2]);
        Process process3 = new Process(pic3);
        process3.rotate(args[1]);
        Utils.savePicture(process3.getPicture(), args[3]);
        break;
      case "flip":
        Picture pic4 = Utils.loadPicture(args[2]);
        Process process4 = new Process(pic4);
        process4.flip(args[1]);
        Utils.savePicture(process4.getPicture(), args[3]);
        break;
      case "blend":
        String[] inputs = Arrays.copyOfRange(args, 1, args.length - 1);//take only the inputs
        Picture[] pics = new Picture[inputs.length];
        for (int i = 0; i < inputs.length; i++ ) {
          pics[i] = Utils.loadPicture(inputs[i]);
        }
        Process process5 = new Process(pics);
        process5.blend();
        Utils.savePicture(process5.getPicture(), args[args.length -1]);
        break;
      case "blur":
        Picture pic6 = Utils.loadPicture(args[1]);
        Process process6 = new Process(pic6);
        process6.blur();
        Utils.savePicture(process6.getPicture(), args[2]);
        break;
//      default:
//        break;

    }
  }
}
