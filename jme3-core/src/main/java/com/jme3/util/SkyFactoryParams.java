package com.jme3.util;

public class  SkyFactoryParams {
    private AssetManager assetManager;
    private Texture west;
    private Texture east;
    private Texture north;
    private Texture south;
    private Texture up;
    private Texture down;
    private Vector3f normalScale;
    private Float sphereRadius;
    private Vector3f normalScale;
    private EnvMapType envMapType;

    public static class SkyFactoryParamsBuilder {

        private AssetManager assetManager;
        private Texture west;
        private Texture east;
        private Texture north;
        private Texture south;
        private Texture up;
        private Texture down;
        private Vector3f normalScale;
        private Float sphereRadius;
        private EnvMapType envMapType;

        public  SkyFactoryParamsBuilder assetManager(AssetManager assetManager){
            this.assetManager = assetManager;
        }

        public  SkyFactoryParamsBuilder west(Texture west){
            this.west = west;
        }

        public  SkyFactoryParamsBuilder east(Texture east){
            this.east = east;
        }

        public  SkyFactoryParamsBuilder north(Texture north){
            this.north = north;
        }

        public  SkyFactoryParamsBuilder south(Texture south){
            this.south = south;
        }

        public  SkyFactoryParamsBuilder up(Texture up){
            this.up = up;
        }

        public  SkyFactoryParamsBuilder down(Texture down){
            this.down = down;
        }

        public  SkyFactoryParamsBuilder normalScale(Vector3f normalScale){
            this.normalScale = normalScale;
        }

        public  SkyFactoryParamsBuilder sphereRadius(Float sphereRadius){
            this.sphereRadius = sphereRadius;
        }

        public  SkyFactoryParamsBuilder envMapType(EnvMapType envMapType){
            this.envMapType = envMapType;
        }

        public SkyFactoryParams build() {
            SkyFactoryParams params = new SkyFactoryParams();

            params.assetManager = this.assetManager ;
            params.west = this.west;
            params.east = this.east;
            params.north = this.north;
            params.south = this.south;
            params.up = this.south;
            params.down = this.down;
            params.normalScale = this.normalScale;
            params.sphereRadius = this.sphereRadius;
            params.normalScale = this.normalScale;
            params.envMapType = this.envMapType;

            return params;
        }
    }

}