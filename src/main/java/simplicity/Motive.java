package simplicity;

public class Motive {
    private int mood;
    private int hunger;
    private int health;

    public Motive(){
        mood = 100;
        hunger = 100;
        health = 100;
    }
    
    public void changeMood(int points) throws IllegalArgumentException {
        try {
            mood += points;
            if (mood > 100){
                mood = 100;
            } else if (mood <= 0){
                throw new IllegalArgumentException("Mood Sim mencapai 0, Sim mati karena depresi");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void changeHunger(int points) throws IllegalArgumentException {
        try {
            hunger += points;
            if (hunger > 100){
                hunger = 100;
            } else if (hunger <= 0){
                throw new IllegalArgumentException("Kekenyangan Sim mencapai 0, Sim mati karena kelaparan");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void changeHealth(int points){
        try {
            health += points;
            if (health > 100){
                health = 100;
            } else if (health <= 0){
                throw new IllegalArgumentException("Kesehatan Sim mencapai 0, Sim mati karena sakit");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
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

    public void setMood(int mood) throws IllegalArgumentException {
        try {
            if (mood > 100){
                mood = 100;
            }

            this.mood = mood;
            if (mood <= 0){
                throw new IllegalArgumentException("Mood Sim mencapai 0, Sim mati karena depresi");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void setHunger(int hunger) throws IllegalArgumentException {
        try {
            if (hunger > 100){
                hunger = 100;
            }

            this.hunger = hunger;
            if (hunger <= 0){
                throw new IllegalArgumentException("Kekenyangan Sim mencapai 0, Sim mati karena kelaparan");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void setHealth(int health) throws IllegalArgumentException {
        try {
            if (health > 100){
                health = 100;
            }
            
            this.health = health;
            if (health <= 0){
                throw new IllegalArgumentException("Kesehatan Sim mencapai 0, Sim mati karena sakit");
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
