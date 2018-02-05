package com.gym.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {
	public static long getRandomNumber(long scale) {
		return ThreadLocalRandom.current().nextLong(scale);
	}
	
	
	public  static void main(String[] args){
		System.out.println(getRandomNumber(1));
		System.out.println(getRandomNumber(2));
		System.out.println(getRandomNumber(2));
		System.out.println(getRandomNumber(2));
		System.out.println(getRandomNumber(2));
		System.out.println(getRandomNumber(2));
	}

}