package simplicity;

import java.util.*;

public class Occupation {
    private static final Map<String, Integer> listJob = new HashMap<>();
    private static List<String> keys;
    private String jobName;
    private int dailySalary;

    public Occupation() {
        fillListJob();
        keys = new ArrayList<>(listJob.keySet());
        Random random = new Random();
        jobName = keys.get(random.nextInt(keys.size()));
        dailySalary = listJob.get(jobName);
    }

    public static List<String> getKeys() {
        return keys;
    }

    public static void setKeys(List<String> keys) {
        Occupation.keys = keys;
    }

    private void fillListJob() {
        listJob.put("Badut Sulap", 15);
        listJob.put("Koki", 30);
        listJob.put("Polisi", 35);
        listJob.put("Programmer", 45);
        listJob.put("Dokter", 50);
        listJob.put("Ojek Payung", 1);
        listJob.put("Pawang Hujan", 5);
        listJob.put("Pelukis", 20);
        listJob.put("Tukang Parkir", 2);
        listJob.put("Pengusaha", 60);
    }

    public void changeJob() {
        System.out.println("Daftar pekerjaan yang tersedia: ");
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(i + 1 + ". " + keys.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        String oldJobName = jobName;
        while (!done) {
            System.out.print("Masukkan nama pekerjaan baru: ");
            jobName = scanner.nextLine();
            if (!jobName.equals(oldJobName)) {
                done = true;
            } else {
                System.out.println("Pekerjaan baru sama dengan pekerjaan yang lama");
            }
        }

        dailySalary = listJob.get(jobName);
        scanner.close();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(int dailySalary) {
        this.dailySalary = dailySalary;
    }
}
