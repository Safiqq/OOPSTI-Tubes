package simplicity;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private String actionName;
    private String description;
    private List<Effect> listEffect;

    public Action(String actionName, String description) {
        this.actionName = actionName;
        this.description = description;
        this.listEffect = new ArrayList<Effect>();
    }

    public Action(String actionName, String description, List<Effect> listEffect) {
        this.actionName = actionName;
        this.description = description;
        this.listEffect = listEffect;
    }

    public String getActionName() {
        return actionName;
    }

    public String getDescription() {
        return description;
    }

    public List<Effect> getListEffect() {
        return listEffect;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListEffect(List<Effect> listEffect) {
        this.listEffect = listEffect;
    }

    public void addEffect(Effect effect) {
        listEffect.add(effect);
    }

    public void deleteEffect(String key) {
        for (Effect effect : listEffect) {
            if (effect.getMotiveName().equals(key)) {
                listEffect.remove(effect);
            }
        }
    }
}
