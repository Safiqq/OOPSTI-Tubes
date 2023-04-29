package simplicity;

public class Motive {
    private int mood;
    private int hunger;
    private int health;

    public Motive() {
        mood = 80;
        hunger = 80;
        health = 80;
    }

    public void changeMood(int points) throws IllegalArgumentException {
        mood += points;
        if (mood > 100) {
            mood = 100;
        } else if (mood <= 0) {
            throw new IllegalArgumentException("Mood Sim mencapai 0, Sim mati karena depresi");
        }
    }

    public void changeHunger(int points) throws IllegalArgumentException {
        hunger += points;
        if (hunger > 100) {
            hunger = 100;
        } else if (hunger <= 0) {
            throw new IllegalArgumentException("Kekenyangan Sim mencapai 0, Sim mati karena kelaparan");
        }
    }

    public void changeHealth(int points) {
        health += points;
        if (health > 100) {
            health = 100;
        } else if (health <= 0) {
            throw new IllegalArgumentException("Kesehatan Sim mencapai 0, Sim mati karena sakit");
        }
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) throws IllegalArgumentException {
        if (mood > 100) {
            mood = 100;
        }

        this.mood = mood;
        if (mood <= 0) {
            throw new IllegalArgumentException("Mood Sim mencapai 0, Sim mati karena depresi");
        }
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) throws IllegalArgumentException {
        if (hunger > 100) {
            hunger = 100;
        }

        this.hunger = hunger;
        if (hunger <= 0) {
            throw new IllegalArgumentException("Kekenyangan Sim mencapai 0, Sim mati karena kelaparan");
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws IllegalArgumentException {
        if (health > 100) {
            health = 100;
        }

        this.health = health;
        if (health <= 0) {
            throw new IllegalArgumentException("Kesehatan Sim mencapai 0, Sim mati karena sakit");
        }
    }
}