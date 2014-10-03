package com.daniellockyer.pizza;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Pizza {
	private String alphabet = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!,?";

	public Pizza() throws IOException {
		System.out.println("PIZZA\n---------------\nSupported characters: " + alphabet + "\n---------------");
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		scanner.close();

		int height = (int) (Math.ceil(Math.log10(alphabet.length()) / Math.log10(2)));
		int[] pixels = new int[line.length() * height];

		int offset = 0;

		for (int i = 0; i < line.length(); i++) {
			int position = alphabet.indexOf(line.charAt(i)) + offset++;
			if (position > alphabet.length()) position = position % alphabet.length();

			String string = String.format("%" + height + "s", Integer.toBinaryString(position)).replace(' ', '0'); // Binary version

			for (int j = 0; j < string.length(); j++) {
				if (string.charAt(j) == '0') pixels[j * line.length() + i] = 0xffffff;
			}
		}

		BufferedImage pixelImage = new BufferedImage(line.length(), height, BufferedImage.TYPE_INT_RGB);
		pixelImage.setRGB(0, 0, line.length(), height, pixels, 0, line.length());
		ImageIO.write(pixelImage, "png", new File("saved.png"));
		System.out.println("Saved!");
	}

	public static void main(String[] args) {
		try {
			new Pizza();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
