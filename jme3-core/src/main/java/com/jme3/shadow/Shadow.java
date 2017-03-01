package com.jme3.shadow;

public class Shadow {

    private float zFar;
    private int edgesThickness;
    private float length;
    protected Vector2f fadeInfo;
    protected float fadeLength;

    public Shadow() {
    }

    public Shadow(float zFar, int edgesThickness, float length) {
        this.zFar = zFar;
        this.edgesThickness = edgesThickness;
        this.length = length;
    }

    public float getzFar() {
        return zFar;
    }

    public void setzFar(float zFar) {
        this.zFar = zFar;
    }

    public int getEdgesThickness() {
        return edgesThickness;
    }

    public void setEdgesThickness(int edgesThickness) {
        this.edgesThickness = edgesThickness;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    /**
     * Define the length over which the shadow will fade out when using a
     * shadowZextend This is useful to make dynamic shadows fade into baked
     * shadows in the distance.
     *
     * @param length the fade length in world units
     */
    public void setShadowZFadeLength(float length) {
        if (length == 0) {
            fadeInfo = null;
            fadeLength = 0;
            postshadowMat.clearParam("FadeInfo");
        } else {
            if (zFarOverride == 0) {
                fadeInfo = new Vector2f(0, 0);
            } else {
                fadeInfo = new Vector2f(zFarOverride - length, 1.0f / length);
            }
            fadeLength = length;
            postshadowMat.setVector2("FadeInfo", fadeInfo);
        }
    }

    /**
     * get the length over which the shadow will fade out when using a
     * shadowZextend
     *
     * @return the fade length in world units
     */
    public float getShadowZFadeLength() {
        if (fadeInfo != null) {
            return zFarOverride - fadeInfo.x;
        }
        return 0f;
    }

}