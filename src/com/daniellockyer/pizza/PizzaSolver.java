package com.daniellockyer.pizza;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PizzaSolver {
	// public String alphabetGuess = "abcdefghijklmnopqrstuvwxyz";
	public String alphabetGuess = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!,?";

	public PizzaSolver() throws IOException {
		BufferedImage image = ImageIO.read(new File("jet.jpg"));
		int[] numbers = new int[image.getWidth()];

		for (int i = 0; i < image.getWidth(); i++) {
			int total = 0;
			for (int j = 0; j < image.getHeight(); j++) {
				if (image.getRGB(i, j) == -16777216) {
					total += (int) Math.pow(2, image.getHeight() - j - 1);
				}
			}
			numbers[i] = total;
		}

		int singleOffset = 0;

		for (int i : numbers) {
			int pos = i - singleOffset++;
			if (pos > alphabetGuess.length()) pos = pos % alphabetGuess.length();
			System.out.print(alphabetGuess.charAt(pos));
		}
	}

	public static void main(String[] args) throws IOException {
	    new PizzaSolver();
	}
}
