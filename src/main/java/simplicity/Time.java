package simplicity;

public class Time {
    private int day;
    private int minute;
    private int second;

    public Time() {
        day = 1;
        minute = 12;
        second = 0;
    }

    public Time(int day, int minute, int second) {
        setTime(day, minute, second);
    }

    private boolean isValid(int day, int minute, int second) {
        if (day < 1) {
            return false;
        }

        if (minute < 0 || minute > 12) {
            return false;
        } else if (minute == 12) {
            if (second != 0) {
                return false;
            }
        }

        if (second < 0 || second > 59) {
            return false;
        }

        return true;
    }

    public void countdown() {
        second--;

        if (second == -1) {
            second = 59;
            minute--;
        }

        if (minute == -1) {
            minute = 11;
            day++;
        }
    }

    public void runTime() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); // 1 detik
                    } catch (InterruptedException e) {

                    }
                    countdown();
                }
            }
        });
        thread.start();
    }

    // getter
    public int getDay() {
        return day;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String getTime() {
        return "day-" + day + " - " + minute + ":" + second;
    }

    // setter
    public void setDay(int day) throws IllegalArgumentException {
        if (day < 1) {
            throw new IllegalArgumentException("Hari yang dimasukkan tidak valid");
        }

        this.day = day;
    }

    public void setMinute(int minute) throws IllegalArgumentException {
        if (minute < 0 || minute > 12) {
            throw new IllegalArgumentException("Menit yang dimasukkan tidak valid");
        } else if (minute == 12) {
            if (second != 0) {
                throw new IllegalArgumentException("Menit yang dimasukkan tidak valid");
            }
        }

        this.minute = minute;
    }

    public void setSecond(int second) throws IllegalArgumentException {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Detik yang dimasukkan tidak valid");
        }

        this.second = second;
    }

    public void setTime(int day, int minute, int second) throws IllegalArgumentException {
        if (!isValid(day, minute, second)) {
            throw new IllegalArgumentException("Tanggal yang dimasukkan tidak valid");
        }

        this.day = day;
        this.minute = minute;
        this.second = second;
    }
}
