/*
 * Copyright 2016 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.rendering.nui;

import org.terasology.math.geom.Rect2f;
import org.terasology.rendering.animation.Animation;
import org.terasology.rendering.animation.AnimationListener;
import org.terasology.rendering.animation.Animator;
import org.terasology.rendering.animation.Rect2fAnimator;
import org.terasology.rendering.animation.TimeModifiers;

/**
 * TODO Type description
 */
public class MenuAnimationSystem {

    private Rect2f animRegion;
    private Animation animLeft;
    private Animation animRight;

    /**
     *
     */
    public MenuAnimationSystem() {
        Rect2f left = Rect2f.createFromMinAndSize(-1, 0, 1, 1);
        Rect2f center = Rect2f.createFromMinAndSize(0, 0, 1, 1);
        Rect2f right = Rect2f.createFromMinAndSize(1, 0, 1, 1);

        Animator ipolLeft = new Rect2fAnimator(left, center, rc -> animRegion = rc);
        Animator ipolRight = new Rect2fAnimator(center, right, rc -> animRegion = rc);

        animRegion = center;
        animLeft = Animation.once(ipolLeft, 1.2f, TimeModifiers.inverse().andThen(TimeModifiers.square()));
        animRight = Animation.once(ipolRight, 1.2f, TimeModifiers.square());
    }

    public void triggerStart() {
        animLeft.start();
    }

    /**
     *
     */
    public void triggerEnd() {
        animRight.start();
    }

    /**
     * @param listener
     */
    public void onEnd(AnimationListener listener) {
        animRight.addListener(listener);
    }

    /**
     * @param delta
     */
    public void update(float delta) {
        animLeft.update(delta);
        animRight.update(delta);
    }

    /**
     * @return
     */
    public Rect2f getAnimRegion() {
        return animRegion;
    }

}
