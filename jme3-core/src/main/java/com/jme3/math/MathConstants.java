package com.jme3.math;

final public class MathConstants {

    private MathConstants() {
    }

    /** A "close to zero" double epsilon value for use*/
    public static final double DBL_EPSILON = 2.220446049250313E-16d;
    /** A "close to zero" float epsilon value for use*/
    public static final float FLT_EPSILON = 1.1920928955078125E-7f;
    /** A "close to zero" float epsilon value for use*/
    public static final float ZERO_TOLERANCE = 0.0001f;
    public static final float ONE_THIRD = 1f / 3f;
    /** The value PI as a float. (180 degrees) */
    public static final float PI = (float) Math.PI;
    /** The value 2PI as a float. (360 degrees) */
    public static final float TWO_PI = 2.0f * PI;
    /** The value PI/2 as a float. (90 degrees) */
    public static final float HALF_PI = 0.5f * PI;
    /** The value PI/4 as a float. (45 degrees) */
    public static final float QUARTER_PI = 0.25f * PI;
    /** The value 1/PI as a float. */
    public static final float INV_PI = 1.0f / PI;
    /** The value 1/(2PI) as a float. */
    public static final float INV_TWO_PI = 1.0f / TWO_PI;
    /** A value to multiply a degree value by, to convert it to radians. */
    public static final float DEG_TO_RAD = PI / 180.0f;
    /** A value to multiply a radian value by, to convert it to degrees. */
    public static final float RAD_TO_DEG = 180.0f / PI;
    /** A precreated random object for random numbers. */
    public static final Random rand = new Random(System.currentTimeMillis());

}