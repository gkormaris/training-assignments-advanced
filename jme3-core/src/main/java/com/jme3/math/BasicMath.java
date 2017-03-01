package com.jme3.math;

import java.util.Random;

/**
 * <code>BasicMath</code> provides 'basic' Math functions.
 * These are all used as static values and functions.
 *
 * @author Various
 * @version $Id: FastMath.java,v 1.45 2007/08/26 08:44:20 irrisor Exp $
 */
final public class BasicMath {

    private BasicMath() {
    }

    /**
     * Returns true if the number is a power of 2 (2,4,8,16...)
     *
     * A good implementation found on the Java boards. note: a number is a power
     * of two if and only if it is the smallest number with that number of
     * significant bits. Therefore, if you subtract 1, you know that the new
     * number will have fewer bits, so ANDing the original number with anything
     * less than it will give 0.
     *
     * @param number
     *            The number to test.
     * @return True if it is a power of two.
     */
    public static boolean isPowerOfTwo(int number) {
        return (number > 0) && (number & (number - 1)) == 0;
    }

    /**
     * Get the next power of two of the given number.
     *
     * E.g. for an input 100, this returns 128.
     * Returns 1 for all numbers <= 1.
     *
     * @param number The number to obtain the POT for.
     * @return The next power of two.
     */
    public static int nearestPowerOfTwo(int number) {
        number--;
        number |= number >> 1;
        number |= number >> 2;
        number |= number >> 4;
        number |= number >> 8;
        number |= number >> 16;
        number++;
        number += (number == 0) ? 1 : 0;
        return number;
    }

    /**
     * Returns the arc cosine of a value.<br>
     * Special cases:
     * <ul><li>If fValue is smaller than -1, then the result is PI.
     * <li>If the argument is greater than 1, then the result is 0.</ul>
     * @param fValue The value to arc cosine.
     * @return The angle, in radians.
     * @see java.lang.Math#acos(double)
     */
    public static float acos(float fValue) {
        if (-1.0f < fValue) {
            if (fValue < 1.0f) {
                return (float) Math.acos(fValue);
            }

            return 0.0f;
        }

        return PI;
    }

    /**
     * Returns the arc sine of a value.<br>
     * Special cases:
     * <ul><li>If fValue is smaller than -1, then the result is -HALF_PI.
     * <li>If the argument is greater than 1, then the result is HALF_PI.</ul>
     * @param fValue The value to arc sine.
     * @return the angle in radians.
     * @see java.lang.Math#asin(double)
     */
    public static float asin(float fValue) {
        if (-1.0f < fValue) {
            if (fValue < 1.0f) {
                return (float) Math.asin(fValue);
            }

            return HALF_PI;
        }

        return -HALF_PI;
    }

    /**
     * Returns the arc tangent of an angle given in radians.<br>
     * @param fValue The angle, in radians.
     * @return fValue's atan
     * @see java.lang.Math#atan(double)
     */
    public static float atan(float fValue) {
        return (float) Math.atan(fValue);
    }

    /**
     * A direct call to Math.atan2.
     * @param fY
     * @param fX
     * @return Math.atan2(fY,fX)
     * @see java.lang.Math#atan2(double, double)
     */
    public static float atan2(float fY, float fX) {
        return (float) Math.atan2(fY, fX);
    }

    /**
     * Rounds a fValue up.  A call to Math.ceil
     * @param fValue The value.
     * @return The fValue rounded up
     * @see java.lang.Math#ceil(double)
     */
    public static float ceil(float fValue) {
        return (float) Math.ceil(fValue);
    }

    /**
     * Returns cosine of an angle. Direct call to java.lang.Math
     * @see Math#cos(double)
     * @param v The angle to cosine.
     * @return  the cosine of the angle.
     */
    public static float cos(float v) {
        return (float) Math.cos(v);
    }

    /**
     * Returns the sine of an angle. Direct call to java.lang.Math
     * @see Math#sin(double)
     * @param v The angle to sine.
     * @return the sine of the angle.
     */
    public static float sin(float v) {
        return (float) Math.sin(v);
    }

    /**
     * Returns E^fValue
     * @param fValue Value to raise to a power.
     * @return The value E^fValue
     * @see java.lang.Math#exp(double)
     */
    public static float exp(float fValue) {
        return (float) Math.exp(fValue);
    }

    /**
     * Returns Absolute value of a float.
     * @param fValue The value to abs.
     * @return The abs of the value.
     * @see java.lang.Math#abs(float)
     */
    public static float abs(float fValue) {
        if (fValue < 0) {
            return -fValue;
        }
        return fValue;
    }

    /**
     * Returns a number rounded down.
     * @param fValue The value to round
     * @return The given number rounded down
     * @see java.lang.Math#floor(double)
     */
    public static float floor(float fValue) {
        return (float) Math.floor(fValue);
    }

    /**
     * Returns 1/sqrt(fValue)
     * @param fValue The value to process.
     * @return 1/sqrt(fValue)
     * @see java.lang.Math#sqrt(double)
     */
    public static float invSqrt(float fValue) {
        return (float) (1.0f / Math.sqrt(fValue));
    }

    public static float fastInvSqrt(float x) {
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x); // get bits for floating value
        i = 0x5f375a86 - (i >> 1); // gives initial guess y0
        x = Float.intBitsToFloat(i); // convert bits back to float
        x = x * (1.5f - xhalf * x * x); // Newton step, repeating increases accuracy
        return x;
    }

    /**
     * Returns the log base E of a value.
     * @param fValue The value to log.
     * @return The log of fValue base E
     * @see java.lang.Math#log(double)
     */
    public static float log(float fValue) {
        return (float) Math.log(fValue);
    }

    /**
     * Returns the logarithm of value with given base, calculated as log(value)/log(base),
     * so that pow(base, return)==value (contributed by vear)
     * @param value The value to log.
     * @param base Base of logarithm.
     * @return The logarithm of value with given base
     */
    public static float log(float value, float base) {
        return (float) (Math.log(value) / Math.log(base));
    }

    /**
     * Returns a number raised to an exponent power.  fBase^fExponent
     * @param fBase The base value (IE 2)
     * @param fExponent The exponent value (IE 3)
     * @return base raised to exponent (IE 8)
     * @see java.lang.Math#pow(double, double)
     */
    public static float pow(float fBase, float fExponent) {
        return (float) Math.pow(fBase, fExponent);
    }

    /**
     * Returns the value squared.  fValue ^ 2
     * @param fValue The value to square.
     * @return The square of the given value.
     */
    public static float sqr(float fValue) {
        return fValue * fValue;
    }

    /**
     * Returns the square root of a given value.
     * @param fValue The value to sqrt.
     * @return The square root of the given value.
     * @see java.lang.Math#sqrt(double)
     */
    public static float sqrt(float fValue) {
        return (float) Math.sqrt(fValue);
    }

    /**
     * Returns the tangent of a value.  If USE_FAST_TRIG is enabled, an approximate value
     * is returned.  Otherwise, a direct value is used.
     * @param fValue The value to tangent, in radians.
     * @return The tangent of fValue.
     * @see java.lang.Math#tan(double)
     */
    public static float tan(float fValue) {
        return (float) Math.tan(fValue);
    }

    /**
     * Returns 1 if the number is positive, -1 if the number is negative, and 0 otherwise
     * @param iValue The integer to examine.
     * @return The integer's sign.
     */
    public static int sign(int iValue) {
        if (iValue > 0) {
            return 1;
        }
        if (iValue < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Returns 1 if the number is positive, -1 if the number is negative, and 0 otherwise
     * @param fValue The float to examine.
     * @return The float's sign.
     */
    public static float sign(float fValue) {
        return Math.signum(fValue);
    }

    /**
     * Returns a random float between 0 and 1.
     *
     * @return A random float between <tt>0.0f</tt> (inclusive) to
     *         <tt>1.0f</tt> (exclusive).
     */
    public static float nextRandomFloat() {
        return rand.nextFloat();
    }

    /**
     * Returns a random integer between min and max.
     *
     * @return A random int between <tt>min</tt> (inclusive) to
     *         <tt>max</tt> (inclusive).
     */
    public static int nextRandomInt(int min, int max) {
        return (int) (nextRandomFloat() * (max - min + 1)) + min;
    }

    public static int nextRandomInt() {
        return rand.nextInt();
    }

    /**
     * Takes an value and expresses it in terms of min to max.
     *
     * @param val -
     *            the angle to normalize (in radians)
     * @return the normalized angle (also in radians)
     */
    public static float normalize(float val, float min, float max) {
        if (Float.isInfinite(val) || Float.isNaN(val)) {
            return 0f;
        }
        float range = max - min;
        while (val > max) {
            val -= range;
        }
        while (val < min) {
            val += range;
        }
        return val;
    }

    /**
     * @param x
     *            the value whose sign is to be adjusted.
     * @param y
     *            the value whose sign is to be used.
     * @return x with its sign changed to match the sign of y.
     */
    public static float copysign(float x, float y) {
        if (y >= 0 && x <= -0) {
            return -x;
        } else if (y < 0 && x >= 0) {
            return -x;
        } else {
            return x;
        }
    }

    /**
     * Take a float input and clamp it between min and max.
     *
     * @param input
     * @param min
     * @param max
     * @return clamped input
     */
    public static float clamp(float input, float min, float max) {
        return (input < min) ? min : (input > max) ? max : input;
    }

    /**
     * Clamps the given float to be between 0 and 1.
     *
     * @param input
     * @return input clamped between 0 and 1.
     */
    public static float saturate(float input) {
        return clamp(input, 0f, 1f);
    }

    /**
     * Determine if two floats are approximately equal.
     * This takes into account the magnitude of the floats, since
     * large numbers will have larger differences be close to each other.
     *
     * Should return true for a=100000, b=100001, but false for a=10000, b=10001.
     *
     * @param a The first float to compare
     * @param b The second float to compare
     * @return True if a and b are approximately equal, false otherwise.
     */
    public static boolean approximateEquals(float a, float b) {
        if (a == b) {
            return true;
        } else {
            return (abs(a - b) / Math.max(abs(a), abs(b))) <= 0.00001f;
        }
    }

    /**
     * Converts a single precision (32 bit) floating point value
     * into half precision (16 bit).
     *
     * <p>Source: <a href="ftp://www.fox-toolkit.org/pub/fasthalffloatconversion.pdf</a>
     *
     * @param half The half floating point value as a short.
     * @return floating point value of the half.
     */
    public static float convertHalfToFloat(short half) {
        switch ((int) half) {
            case 0x0000:
                return 0f;
            case 0x8000:
                return -0f;
            case 0x7c00:
                return Float.POSITIVE_INFINITY;
            case 0xfc00:
                return Float.NEGATIVE_INFINITY;
            // TODO: Support for NaN?
            default:
                return Float.intBitsToFloat(((half & 0x8000) << 16)
                        | (((half & 0x7c00) + 0x1C000) << 13)
                        | ((half & 0x03FF) << 13));
        }
    }

    public static short convertFloatToHalf(float flt) {
        if (Float.isNaN(flt)) {
            throw new UnsupportedOperationException("NaN to half conversion not supported!");
        } else if (flt == Float.POSITIVE_INFINITY) {
            return (short) 0x7c00;
        } else if (flt == Float.NEGATIVE_INFINITY) {
            return (short) 0xfc00;
        } else if (flt == 0f) {
            return (short) 0x0000;
        } else if (flt == -0f) {
            return (short) 0x8000;
        } else if (flt > 65504f) {
            // max value supported by half float
            return 0x7bff;
        } else if (flt < -65504f) {
            return (short) (0x7bff | 0x8000);
        } else if (flt > 0f && flt < 3.054738E-5f) {
            return 0x0001;
        } else if (flt < 0f && flt > -3.054738E-5f) {
            return (short) 0x8001;
        }

        int f = Float.floatToIntBits(flt);
        return (short) (((f >> 16) & 0x8000)
                | ((((f & 0x7f800000) - 0x38000000) >> 13) & 0x7c00)
                | ((f >> 13) & 0x03ff));
    }
}
