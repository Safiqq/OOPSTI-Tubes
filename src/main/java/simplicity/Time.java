package simplicity;

public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Time() {
        day = 1;
        month = 1;
        year = 1;
        hour = 0;
        minute = 0;
        second = 0;
    }

    public Time(int day, int month, int year, int hour, int minute, int second) {
        setTime(day, month, year, hour, minute, second);
    }

    private boolean isValid(int day, int month, int year, int hour, int minute, int second) {
        if (day < 1 || day > getMaxDay(month, year)) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        if (year < 1) {
            return false;
        }

        if (hour < 0 || hour > 23) {
            return false;
        }

        if (minute < 0 || minute > 59) {
            return false;
        }

        if (second < 0 || second > 59) {
            return false;
        }

        return true;
    }

    private int getMaxDay(int month, int year) {
        switch (month) {
            case 2:
                if (isKabisat(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
                return 30;
            case 6:
                return 30;
            case 9:
                return 30;
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    private boolean isKabisat(int year) {
        if (year % 400 == 0) {
            return true;
        } else {
            if (year % 100 == 0) {
                return false;
            } else {
                if (year % 4 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public void addSecond(){
        second++;

        if (second == 60){
            second = 0;
            minute++;
        }

        if (minute == 60){
            minute = 0;
            hour++;
        }

        if (hour == 24){
            hour = 0;
            day++;
        }

        if (day == getMaxDay(month, year)+1){
            day = 1;
            month++;
        }

        if (month == 13){
            month = 1;
            year++;
        }
    }

    public void runTime(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000); // 1 detik
                    } catch (InterruptedException e){
                        
                    }
                    addSecond();
                }
            }
        });
        thread.start();
    }

    // getter
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String getTime() {
        return day + "/" + month + "/" + year + " - " + hour + ":" + minute + ":" + second;
    }

    // setter
    public void setYear(int year) throws IllegalArgumentException {
        // try {
        if (year < 1) {
            throw new IllegalArgumentException("Tahun yang dimasukkan tidak valid");
        }

        this.year = year;

        // } catch (IllegalArgumentException e){
        // System.out.println(e.getMessage());
        // }

    }

    public void setMonth(int month) throws IllegalArgumentException {
        // try {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Bulan yang dimasukkan tidak valid");
        }

        this.month = month;

        // } catch (IllegalArgumentException e) {
        // System.out.println(e.getMessage());
        // }
    }

    public void setDay(int day) throws IllegalArgumentException {
        // try {
        if (day < 1 || day > getMaxDay(month, year)) {
            throw new IllegalArgumentException("Hari yang dimasukkan tidak valid");
        }

        this.day = day;

        // } catch (IllegalArgumentException e) {
        // System.out.println(e.getMessage());
        // }
    }

    public void setHour(int hour) throws IllegalArgumentException {
        // try {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Jam yang dimasukkan tidak valid");
        }

        this.hour = hour;

        // } catch (IllegalArgumentException e) {
        // System.out.println(e.getMessage());
        // }
    }

    public void setMinute(int minute) throws IllegalArgumentException {
        // try {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Menit yang dimasukkan tidak valid");
        }

        this.minute = minute;

        // } catch (IllegalArgumentException e) {
        // System.out.println(e.getMessage());
        // }
    }

    public void setSecond(int second) throws IllegalArgumentException {
        // try {
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("Detik yang dimasukkan tidak valid");
        }

        this.second = second;

        // } catch (IllegalArgumentException e) {
        // System.out.println(e.getMessage());
        // }
    }

    public void setTime(int day, int month, int year, int hour, int minute, int second) throws IllegalArgumentException {
        // try {
        if (!isValid(day, month, year, hour, minute, second)) {
            throw new IllegalArgumentException("Tanggal yang dimasukkan tidak valid");
        }

        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        // } catch (IllegalArgumentException e) {
        // System.out.println(e.getMessage());
        // }
    }
}
