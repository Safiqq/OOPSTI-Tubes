package simplicity;

public class Time {
    private int day;
    private int minute;
    private int second;

    public Time() {
        this(1, 12, 0);
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
        return second >= 0 && second <= 59;
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

    public void effectCountdown(Sim sim) {
        for (String status : sim.getMapStatus().keySet()) {
            int duration = sim.getMapStatus().get(status);
            if (duration > 0) {
                sim.getMapStatus().put(status, duration - 1);
            }
            if (duration == 0) {
                sim.applyEffect(status);
                sim.deleteStatus(status);
            }
        }
    }

    public void sleepMain(Sim sim, int seconds) {
        // aksi yang butuh waktu dan tidak bisa ditinggal/aksi aktif
        try {
            for (int i = 0; i < seconds; i++) {
                countdown();
                effectCountdown(sim);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public int getDay() {
        return day;
    }

    // setter
    public void setDay(int day) throws IllegalArgumentException {
        if (day < 1) {
            throw new IllegalArgumentException("Hari yang dimasukkan tidak valid");
        }
        this.day = day;
    }

    public int getMinute() {
        return minute;
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

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) throws IllegalArgumentException {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Detik yang dimasukkan tidak valid");
        }
        this.second = second;
    }

    public String getTime() {
        return "day-" + day + " - " + minute + ":" + second;
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
