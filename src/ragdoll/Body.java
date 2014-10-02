package ragdoll;

import java.util.*;

public class Body {

    private int headX;
    private int headY;
    private final int headR = 10;
    private int spineLength = 125;
    private int armLength = 75;
    private int legLength = 300;
    private int neckLength = 20;

    public List<Limb> limbs;

    public Body() {
        limbs = new ArrayList<>();
    }

    public void build(int width, int height) {
        this.headX = width/2;
        this.headY = height/4;
        float[] headPoint = v2f(headX, headY);
        float[] neckPoint = v2f(headX, headY + neckLength);
        float[] spinePoint = v2f(headX, headY + neckLength + spineLength);
        float[] leftArmPoint = v2f(headX - armLength, headY + headR);
        float[] rightArmPoint = v2f(headX + armLength, headY + headR);
        float[] leftLegPoint = v2f(headX - armLength, headY + legLength);
        float[] rightLegPoint = v2f(headX + armLength, headY + legLength);
        attach(new Limb(headPoint, neckPoint));
        attach(new Limb(neckPoint,leftArmPoint));
        attach(new Limb(neckPoint, rightArmPoint));
        attach(new Limb(neckPoint, spinePoint));
        attach(new Limb(spinePoint, leftLegPoint));
        attach(new Limb(spinePoint, rightLegPoint));
    }

    public void update(int x, int y) {
        limbs.get(0).start[0] = x;
        limbs.get(0).start[1] = y;
        for(Limb l : limbs) {
            l.update();
        }
    }

    private float[] v2f(int x, int y) {
        return new float[]{(float) x, (float) y};
    }

    private Body attach(Limb limb) {
        limbs.add(limb);
        return this;
    }
}
