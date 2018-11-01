package app.utils;

public class Util {
	    public static int convertToPositiveInt(String input) throws IllegalArgumentException {
	        int i = Integer.parseInt(input);
	        if (i <= 0)
	            throw new IllegalArgumentException();

	        return i;
	    }


	    public static void shouldAllNonNegative(int... numbers) {
	        for (int num : numbers) {
	            if (num < 0) {
	                throw new IllegalArgumentException("Error. Number should not be negative, should be > 0");
	            }
	        }
	    }
	    public static void shouldAllPositive(int... numbers) {
	        for (int num : numbers) {
	            if (num < 1) {
	                throw new IllegalArgumentException("Error. Number should be positive number > 0");
	            }
	        }
	    }
	}

