public class ChaseCameraUtil {

    private ChaseCamera chaseCamera;

    public ChaseCameraUtil(ChaseCamera chaseCamera) {
        this.chaseCamera = chaseCamera;
    }

    protected void updateCamera(float tpf) {
        if (enabled) {
            targetLocation.set(target.getWorldTranslation()).addLocal(lookAtOffset);
            if (smoothMotion) {
                handleSmoothMotion(tpf);
            } else {
                hanldeNoSmoothMotion();
            }

            //keeping track on the previous position of the target
            chaseCamera.prevPos.set(targetLocation);
            //the cam looks at the target
            chaseCamera.cam.lookAt(targetLocation, initialUpVec);
        }
    }

    private void hanldeNoSmoothMotion() {
        //easy no smooth motion
        chaseCamera.vRotation = chaseCamera.targetVRotation;
        chaseCamera.rotation = chaseCamera.targetRotation;
        chaseCamera.distance = chaseCamera.targetDistance;
        chaseCamera.computePosition();
        chaseCamera.cam.setLocation(pos.addLocal(lookAtOffset));
    }

    private void handleSmoothMotion(float tpf) {
        handleLowPassFiltering();

        //the user is rotating the cam by dragging the mouse
        if (chaseCamera.canRotate) {
            //reseting the trailing lerp factor
            chaseCamera.trailingLerpFactor = 0;
            //stop trailing user has the control
            chaseCamera.trailing = false;
        }

        handleTrailing(tpf);
        handleChasing(tpf);
        handleZooming(tpf);
        handleInterpolationHorizontally(tpf);
        handleInterpolationVertically(tpf);
        //computing the position
        chaseCamera.computePosition();
        //setting the position at last
        chaseCamera.cam.setLocation(pos.addLocal(lookAtOffset));
    }

    /**
     * linear interpolation of the rotation while rotating vertically
     *
     * @param tpf
     */
    private void handleInterpolationVertically(float tpf) {
        if (chaseCamera.vRotating) {
            chaseCamera.vRotationLerpFactor = Math.min(chaseCamera.vRotationLerpFactor + tpf * tpf * chaseCamera.rotationSensitivity, 1);
            chaseCamera.vRotation = FastMath.interpolateLinear(chaseCamera.vRotationLerpFactor, chaseCamera.vRotation, chaseCamera.targetVRotation);
            if (chaseCamera.targetVRotation + 0.01f >= chaseCamera.vRotation && chaseCamera.targetVRotation - 0.01f <= chaseCamera.vRotation) {
                chaseCamera.vRotating = false;
                chaseCamera.vRotationLerpFactor = 0;
            }
        }
    }

    /**
     * linear interpolation of the rotation while rotating horizontally
     *
     * @param tpf
     */
    private void handleInterpolationHorizontally(float tpf) {
        if (chaseCamera.rotating) {
            chaseCamera.rotationLerpFactor = Math.min(chaseCamera.rotationLerpFactor + tpf * tpf * chaseCamera.rotationSensitivity, 1);
            chaseCamera.rotation = FastMath.interpolateLinear(chaseCamera.rotationLerpFactor, chaseCamera.rotation, chaseCamera.targetRotation);
            if (chaseCamera.targetRotation + 0.01f >= chaseCamera.rotation && chaseCamera.targetRotation - 0.01f <= chaseCamera.rotation) {
                chaseCamera.rotating = false;
                chaseCamera.rotationLerpFactor = 0;
            }
        }
    }

    /**
     * linear interpolation of the distance while zooming
     *
     * @param tpf
     */
    private void handleZooming(float tpf) {
        if (chaseCamera.zooming) {
            chaseCamera.distanceLerpFactor = Math.min(chaseCamera.distanceLerpFactor + (tpf * tpf * chaseCamera.zoomSensitivity), 1);
            chaseCamera.distance = FastMath.interpolateLinear(chaseCamera.distanceLerpFactor, chaseCamera.distance, chaseCamera.targetDistance);
            if (chaseCamera.targetDistance + 0.1f >= chaseCamera.distance && chaseCamera.targetDistance - 0.1f <= chaseCamera.distance) {
                chaseCamera.zooming = false;
                chaseCamera.distanceLerpFactor = 0;
            }
        }
    }

    /**
     * linear interpolation of the distance while chasing
     *
     * @param tpf
     */
    private void handleChasing(float tpf) {
        if (chaseCamera.chasing) {
            chaseCamera.distance = temp.set(chaseCamera.targetLocation).subtractLocal(chaseCamera.cam.getLocation()).length();
            chaseCamera.distanceLerpFactor = Math.min(chaseCamera.distanceLerpFactor + (tpf * tpf * chaseCamera.chasingSensitivity * 0.05f), 1);
            chaseCamera.distance = FastMath.interpolateLinear(chaseCamera.distanceLerpFactor, chaseCamera.distance, chaseCamera.targetDistance);
            if (chaseCamera.targetDistance + 0.01f >= chaseCamera.distance && chaseCamera.targetDistance - 0.01f <= chaseCamera.distance) {
                chaseCamera.distanceLerpFactor = 0;
                chaseCamera.chasing = false;
            }
        }
    }

    private void handleTrailing(float tpf) {
        if (chaseCamera.trailingEnabled && chaseCamera.trailing) {
            if (chaseCamera.targetMoves) {
                //computation if the inverted direction of the target
                Vector3f a = chaseCamera.targetDir.negate().normalizeLocal();
                //the x unit vector
                Vector3f b = Vector3f.UNIT_X;
                //2d is good enough
                a.y = 0;
                //computation of the rotation angle between the x axis and the trail
                if (chaseCamera.targetDir.z > 0) {
                    chaseCamera.targetRotation = FastMath.TWO_PI - FastMath.acos(a.dot(b));
                } else {
                    chaseCamera.targetRotation = FastMath.acos(a.dot(b));
                }
                if (chaseCamera.targetRotation - chaseCamera.rotation > FastMath.PI || targetRotation - rotation < -FastMath.PI) {
                    chaseCamera.targetRotation -= FastMath.TWO_PI;
                }

                //if there is an important change in the direction while trailing reset of the lerp factor to avoid jumpy movements
                if (targetRotation != previousTargetRotation && FastMath.abs(targetRotation - previousTargetRotation) > FastMath.PI / 8) {
                    trailingLerpFactor = 0;
                }
                previousTargetRotation = targetRotation;
            }
            //computing lerp factor
            trailingLerpFactor = Math.min(trailingLerpFactor + tpf * tpf * trailingSensitivity, 1);
            //computing rotation by linear interpolation
            rotation = FastMath.interpolateLinear(trailingLerpFactor, rotation, targetRotation);

            //if the rotation is near the target rotation we're good, that's over
            if (targetRotation + 0.01f >= rotation && targetRotation - 0.01f <= rotation) {
                trailing = false;
                trailingLerpFactor = 0;
            }
        }
    }

    private void handleLowPassFiltering() {
        //computation of target direction
        chaseCamera.targetDir.set(targetLocation).subtractLocal(prevPos);
        float dist = targetDir.length();

        //Low pass filtering on the target postition to avoid shaking when physics are enabled.
        if (chaseCamera.offsetDistance < dist) {
            //target moves, start chasing.
            chasing = true;
            //target moves, start trailing if it has to.
            if (chaseCamera.trailingEnabled) {
                chaseCamera.trailing = true;
            }
            //target moves...
            chaseCamera.targetMoves = true;
        } else {
            //if target was moving, we compute a slight offset in rotation to avoid a rought stop of the cam
            //We do not if the player is rotationg the cam
            if (chaseCamera.targetMoves && !chaseCamera.canRotate) {
                if (targetRotation - rotation > trailingRotationInertia) {
                    targetRotation = rotation + trailingRotationInertia;
                } else if (targetRotation - rotation < -trailingRotationInertia) {
                    targetRotation = rotation - trailingRotationInertia;
                }
            }
            //Target stops
            targetMoves = false;
        }
    }
}