package simplicity;

public class Effect {
    private String motiveName;
    private Integer motiveEffect;
    private Integer cooldown;

    public Effect(String motiveName, Integer motiveEffect, Integer cooldown) {
        this.motiveName = motiveName;
        this.motiveEffect = motiveEffect;
        this.cooldown = cooldown; // in seconds
    }

    public String getMotiveName() {
        return motiveName;
    }

    public Integer getMotiveEffect() {
        return motiveEffect;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setMotiveName(String motiveName) {
        this.motiveName = motiveName;
    }

    public void setMotiveName(Integer motiveEffect) {
        this.motiveEffect = motiveEffect;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }
}
