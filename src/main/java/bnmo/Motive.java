package bnmo;

public class Motive {
    private int mood;
    private int hunger;
    private int health;

    public Motive(){
        mood = 100;
        hunger = 100;
        health = 100;
    }
    
    public void addMood(int addPoints){
        mood += addPoints;
        if (mood > 100){
            mood = 100;
        }
    }

    public void addHunger(int addPoints){
        hunger += addPoints;
        if (hunger > 100){
            hunger = 100;
        }
    }

    public void addHealth(int addPoints){
        health += addPoints;
        if (health > 100){
            health = 100;
        }
    }

    public void minMood(int minPoints){
        mood -= minPoints;
    }

    public void minHunger(int minPoints){
        hunger -= minPoints;
    }

    public void minHealth(int minPoints){
        health -= minPoints;
    }

    public int getMood(){
        return mood;
    }

    public int getHunger(){
        return hunger;
    }

    public int getHealth(){
        return health;
    }

    public void setMood(int mood){
        if (mood > 100){
            mood = 100;
        }

        this.mood = mood;
    }

    public void setHunger(int hunger){
        if (hunger > 100){
            hunger = 100;
        }

        this.hunger = hunger;
    }

    public void setHealth(int health){
        if (health > 100){
            health = 100;
        }
        
        this.health = health;
    }
}
