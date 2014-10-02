package ragdoll;

public class Limb {

    public float[] start, end;
    private float length;

    public Limb(float[] start, float[] end) {
        this.start = start;
        this.end = end;
        this.length = distance();
    }

    private float distance() {
        double temp_length = Math.sqrt(Math.pow(end[0] - start[0], 2d) + Math.pow(end[1] - start[1], 2d));
        return (float) temp_length;
    }

    private void contract(float[] start, float[] end, float factor) {
        float dx = start[0] - end[0];
        float dy = start[1] - end[1];

        start[0] += dx * (-1f) * factor;
        start[1] += dy * (-1f) * factor;
        end[0] += dx * factor;
        end[1] += dy * factor;
    }

    public void update() {
        if(distance() > length) {
            contract(start, end, 0.06f);
        }
    }

}
