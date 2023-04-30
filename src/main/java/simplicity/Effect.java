package simplicity;

public class Effect {
    private String motiveName;
    private int motiveEffect;
    private int cooldown;

    public Effect(String motiveName, int motiveEffect, int cooldown) {
        this.motiveName = motiveName;
        this.motiveEffect = motiveEffect;
        this.cooldown = cooldown; // in seconds
    }

    public String getMotiveName() {
        return motiveName;
    }

    public void setMotiveName(String motiveName) {
        this.motiveName = motiveName;
    }

    public int getMotiveEffect() {
        return motiveEffect;
    }

    public void setMotiveEffect(int motiveEffect) {
        this.motiveEffect = motiveEffect;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
