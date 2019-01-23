/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines (TODO and return null) from each method
 * and replace them with your implementation that returns an updated image.
 */

/* Names: Romina Parimi, Giovanni Thompson
 * Section Leader: Belce Dogru
 * Purpose: The objective of this program is to implement image manipulation
 * algorithms that accept a GImage parameter and return the modified version
 * of the GImage.
 * Citations: The Art and Science of Java, lecture slides
 */
import java.util.*;
import acm.graphics.*;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {
	
	/* This method will flip the image horizontally by switching the index of
	 * the column with the array width-1-column index.
	 */
	public GImage flipHorizontal(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length / 2; col++) {
				int endCol = (pixels[0].length - 1) - col;
				int temp = pixels[row][col];
				pixels[row][col] = pixels[row][endCol];
				pixels[row][endCol] = temp;
			}
		}
		source.setPixelArray(pixels);
		return source;
	}
	
	/* This method will rotate the image to the left by switching the row with
	 * the column index and the column with array width-1-row index.
	 */
	public GImage rotateLeft(GImage source) {
		int[][] origin = source.getPixelArray();
		int[][] newArray = new int[origin[0].length][origin.length];
		// Reset pixels array and turn left
		for (int row = 0; row < newArray.length; row++) {
			for (int col = 0; col < newArray[0].length; col++) {
				newArray[row][col] = origin[col][origin[0].length - 1 - row];
			}
		}
		source.setPixelArray(newArray);
		return source;
	}

	/* This method will rotate the image to the right by repeating the
	 * rotateLeft() method three times.
	 */
	public GImage rotateRight(GImage source) {
		for (int i = 0; i < 3; i++) {
			source = rotateLeft(source);
		}
		return source;
	}

	/* This method will iterate through all the pixels to make the green pixels
	 * transparent if the green pixel value is more than twice the red or blue 
	 * pixel values.
	 */
	public GImage greenScreen(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				int r = GImage.getRed(pixels[row][col]);
				int b = GImage.getBlue(pixels[row][col]);
				int g = GImage.getGreen(pixels[row][col]);
				if (g > (Math.max(r, b)) * 2) {
					pixels[row][col] = GImage.createRGBPixel(r, g, b, 0);

				}

			}
		}
		source.setPixelArray(pixels);
		return source;
	}

	/* This method's objective is to increase the image's contrast by
	 * calculating the cumulative luminosity and luminosity histograms. 
	 * It then calculates the luminosity as a percentage of the pixels in the 
	 * image and sets the new pixels to those of the original index.
	 */
	public GImage equalize(GImage source) {
        int[][] origin = source.getPixelArray();
        int[][] equalized = new int[origin.length][origin[0].length];
        double[] cumulative = cumulativeHistogram(origin);
        double percentage;
        int luminosity;
        for (int row = 0; row < origin.length; row++) {
            for (int col = 0; col < origin[0].length; col++) {
                int r = GImage.getRed(origin[row][col]);
                int g = GImage.getGreen(origin[row][col]);
                int b = GImage.getBlue(origin[row][col]);
                luminosity = computeLuminosity(r, g, b);
                percentage = cumulative[luminosity] / cumulative[255];
                luminosity = (int) (percentage * 255);
                equalized[row][col] = GImage.createRGBPixel(luminosity, luminosity, luminosity);
            }
        }
        source.setPixelArray(equalized);
        return source;
    }
	
	// This method will compute the luminosity histogram.
    private int[] luminosityHistogram(int[][] origin) {
        int[] histogram = new int[256];
        for (int row = 0; row < origin.length; row++) {
            for (int col = 0; col < origin[0].length; col++) {
                int red = GImage.getRed(origin[row][col]);
                int green = GImage.getGreen(origin[row][col]);
                int blue = GImage.getBlue(origin[row][col]);
                int luminosity = computeLuminosity(red, green, blue);
                histogram[luminosity] += 1; 
            }
        }
        return histogram;
    }

	
 // This method will compute the cumulative luminosity histogram.
    private double[] cumulativeHistogram(int[][] origin) {
        double[] cumulative = new double[256];
        int[] histogram = luminosityHistogram(origin);
        for (int row = 0; row < histogram.length; row++) {
            if (row == 0) {
                cumulative[0] = histogram[0];
            } else {
                cumulative[row] = cumulative[row - 1] + histogram[row];
            }
        }
        return cumulative;
    }

	/* The purpose of this method is to return the image in its negative color
	 * by switching each RGB value with 255-(RGB value).
	 */
	public GImage negative(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				int r = GImage.getRed(pixels[row][col]);
				int g = GImage.getGreen(pixels[row][col]);
				int b = GImage.getBlue(pixels[row][col]);
				pixels[row][col] = GImage.createRGBPixel(255-r, 255-g, 255-b);
			}
		}
		source.setPixelArray(pixels);
		return source;
	}

	/* This method will translate the image by dx and dy, determined by
	 * user input.
	 */
	public GImage translate(GImage source, int dx, int dy) {
        int[][] pixels = source.getPixelArray();
        int[][] arr = new int[pixels.length][pixels[0].length];
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                int tempx = dx + col;
                int tempy = dy + row;
                int x = tempx - col;
                int y = tempy-col;

                if(dx < 0) {
                	x = (arr[0].length+col + dx%arr[0].length)%arr[0].length;
                } else {
                	x = tempx%(arr[0].length);
                }
                if(dy < 0) {
                	y = (arr.length+row + dy%arr.length)%arr.length;
                } else {
                	y = tempy%(arr.length);
                }
                arr[y][x] = pixels[row][col];
            }
        }
        source.setPixelArray(arr);
        return source;
    }

	
	private int calculateColor(int row, int col, int[][] origin, int color) {
		int count = 1;
		int r = GImage.getRed(origin[row][col]);
		int g = GImage.getGreen(origin[row][col]);
		int b = GImage.getBlue(origin[row][col]);
		
		if(row-1 >= 0) {
			if(color == GImage.getRed(origin[row][col])) {
				int top = GImage.getRed(origin[row-1][col]);
				r += top;
				count++;
			} 
			if(color == GImage.getGreen(origin[row][col])) {
				int top = GImage.getGreen(origin[row-1][col]);
				r += top;
				count++;
			} 
			if(color == GImage.getBlue(origin[row][col])) {
				int top = GImage.getBlue(origin[row-1][col]);
				r += top;
				count++;
			}
		}
		if(row+1 <= origin.length) {
			if(color == GImage.getRed(origin[row][col])) {
				int bottom = GImage.getRed(origin[row-1][col]);
				r += bottom;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int bottom = GImage.getGreen(origin[row-1][col]);
				r += bottom;
				count++;
			} 
			if(color == GImage.getBlue(origin[row][col])) {
				int bottom = GImage.getBlue(origin[row-1][col]);
				r += bottom;
				count++;
			}
		}
		if(col-1 >= 0) {
			if(color == GImage.getRed(origin[row][col])) {
				int left = GImage.getRed(origin[row-1][col]);
				r += left;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int left = GImage.getGreen(origin[row-1][col]);
				r += left;
				count++;
			}
			if(color == GImage.getBlue(origin[row][col])) {
				int left = GImage.getBlue(origin[row-1][col]);
				r += left;
			}
		}
		if(col+1 <= origin[0].length) {
			if(color == GImage.getRed(origin[row][col])) {
				int right = GImage.getRed(origin[row-1][col]);
				r += right;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int right = GImage.getGreen(origin[row-1][col]);
				r += right;
				count++;
			}
			if(color == GImage.getBlue(origin[row][col])) {
				int right = GImage.getBlue(origin[row-1][col]);
				r += right;
				count++;
			}
		}
		if(row-1 >= 0 && col-1 >= 0) {
			if(color == GImage.getRed(origin[row][col])) {
				int topLeft = GImage.getRed(origin[row-1][col]);
				r += topLeft;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int topLeft = GImage.getGreen(origin[row-1][col]);
				r += topLeft;
				count++;
			}
			if(color == GImage.getBlue(origin[row][col])) {
				int topLeft = GImage.getBlue(origin[row-1][col]);
				r += topLeft;
				count++;
			}
		}
		if(row-1 >= 0 && col+1 <= origin[0].length) {
			if(color == GImage.getRed(origin[row][col])) {
				int topRight = GImage.getRed(origin[row-1][col]);
				r += topRight;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int topRight = GImage.getGreen(origin[row-1][col]);
				r += topRight;
				count++;
			}
			if(color == GImage.getBlue(origin[row][col])) {
				int topRight = GImage.getBlue(origin[row-1][col]);
				r += topRight;
				count++;
			}
		}
		if(row+1 <= origin.length && col >= 0) {
			if(color == GImage.getRed(origin[row][col])) {
				int bottomLeft = GImage.getRed(origin[row-1][col]);
				r += bottomLeft;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int bottomLeft = GImage.getGreen(origin[row-1][col]);
				r += bottomLeft;
				count++;
			}
			if(color == GImage.getBlue(origin[row][col])) {
				int bottomLeft = GImage.getBlue(origin[row-1][col]);
				r += bottomLeft;
				count++;
			}
		}
		if(row+1 <= origin.length && col+1 <= origin[0].length) {
			if(color == GImage.getRed(origin[row][col])) {
				int bottomRight = GImage.getRed(origin[row-1][col]);
				r += bottomRight;
				count++;
			}
			if(color == GImage.getGreen(origin[row][col])) {
				int bottomRight = GImage.getGreen(origin[row-1][col]);
				r += bottomRight;
				count++;
			}
			if(color == GImage.getBlue(origin[row][col])) {
				int bottomRight = GImage.getBlue(origin[row-1][col]);
				r += bottomRight;
				count++;
			}
		}
		return color/count;
	}
	
	/* This method will blur the image by calculating the average of the
	 * surrounding RGB pixels and then swap the original pixels
	 * with those of the average value.
	 */
	public GImage blur(GImage source) {
		int[][] origin = source.getPixelArray();
		int[][] arr = new int[origin.length][origin[0].length];
		for (int row = 0; row < arr.length-1; row++) {
			for (int col = 0; col < arr[0].length; col++) {
				//int r = calculateRed(row, col, origin);
				int r = GImage.getRed(origin[row][col]);
				r = calculateColor(row, col, origin, r);
				int g = GImage.getGreen(origin[row][col]);
				g = calculateColor(row, col, origin, g);
				int b = GImage.getRed(origin[row][col]);
				b = calculateColor(row, col, origin, b);
				arr[row][col] = GImage.createRGBPixel(r, g, b);
			}
		}
		source.setPixelArray(arr);
		return source;
	}
	
}