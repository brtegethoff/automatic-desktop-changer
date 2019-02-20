package desktopSwitch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class DesktopSwitch {
	
	private static String projectPath = System.getProperty("user.dir");
	private static String imageLibPath = projectPath + "\\ImageLibrary";
	private static String mergedLibPath = projectPath + "\\MergedLibrary";
	private static String quoteLibPath = projectPath + "\\QuoteLibrary";
	
	public static void main(String[] args) {
		/**
		 * 1. Create / extend "library" of awesome images from Reddit
		 * 2. Create / extend "library" of nice quotes from Reddit
		 * 3. Select random combination
		 * 4. Combine in a new image
		 * 5. Set as background
		 */
		
		System.out.println("Started!");
		
		// 1. Extend library of awesome images from Reddit
		addNewImage();
		
		// 2. Extend library of nice quotes from Reddit
		addNewQuote();
		
		// 3. + 4. Combine a new image with random images and quotes
		File newImage = mergeImage(randomImage(), randomQuote());
		
		// 5. Set as background
		setBackground(newImage);
		System.out.println("Done!");
	}
	
	public static void setBackground(File image) {
		Runtime rt = Runtime.getRuntime();
		String wallpaperPath = image.getPath() + "\\" + image.getName();
		
		try {
			rt.exec("reg add \"HKEY_CURRENT_USER\\Control Panel\\Desktop\" /v Wallpaper /t REG_SZ /d  " + wallpaperPath + "/f");
			rt.exec("RUNDLL32.EXE user32.dll,UpdatePerUserSystemParameters");
		} catch (IOException e) {
			System.out.println("Error running command line to se Background!");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static File mergeImage(File image, String quote) {
		String oldFilename = image.getName();
		String newImagePath = imageLibPath + "\\_-_merged_-_" + oldFilename;
		File newImage = new File(newImagePath);
		
		// BIG TODO
		
		return newImage;
	}
	
	public static void addNewQuote() {
		File quotes = new File(quoteLibPath + "\\" + "quotes.txt");
		String newQuote = downloadQuote();
		
		try {
			FileWriter out = new FileWriter(quotes, true);
			out.write(newQuote + "\n");
			out.close();
		} catch (IOException e) {
			System.err.println("Error creating output writer to quotes!");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void addNewImage() {
		// BIG TODO
		int width = 1920;
		int height = 1080;
	    BufferedImage image = downloadImage();
		
	    try{
	        File newImage = new File(imageLibPath + "\\" + newImageName);  //output file path
	        ImageIO.write(image, "jpg", newImage);
	        System.out.println("Writing complete.");
	      }catch(IOException e){
	        System.out.println("Error: "+e);
	      }
	}
	
	public static File randomImage() {
		File folder = new File(imageLibPath);
		String[] filenames = folder.list();
		String randomFilename = getRandomStringElement(filenames);
		File image = new File(randomFilename);
				
		return image;
	}
	
	public static String randomQuote() { // Expects one quote per line of document
		String quote = ""; 
		File quoteFile = new File(quoteLibPath + "\\" + "quotes.txt");
		
		try {
			quote = B.choose(quoteFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error choosing random quote!");
			e.printStackTrace();
			System.exit(0);
		}
		
		return quote;
	}
	
	public static String downloadQuote() {
		String newQuote = "";
		
		// TODO 
		
		return newQuote;
	}
	
	public static BufferedImage downloadImage() {
		BufferedImage image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
		
		// TODO
		
		return image;
	}
	
	public static String getRandomStringElement(String[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}

}

class B {

	  public static void main(String[] args) throws FileNotFoundException {
	     Map<String,Integer> map = new HashMap<String,Integer>();
	     for(int i = 0; i < 1000; ++i)
	     {
	        String s = choose(new File("g:/temp/a.txt"));
	        if(!map.containsKey(s))
	           map.put(s, 0);
	        map.put(s, map.get(s) + 1);
	     }

	     System.out.println(map);
	  }

	  public static String choose(File f) throws FileNotFoundException
	  {
	     String result = null;
	     Random rand = new Random();
	     int n = 0;
	     for(Scanner sc = new Scanner(f); sc.hasNext(); )
	     {
	        ++n;
	        String line = sc.nextLine();
	        if(rand.nextInt(n) == 0)
	           result = line;         
	     }

	     return result;      
	  }
	}
