package simplicity;

import java.util.*;

public class Occupation {
    private String jobName;
    private int dailySalary;
    private Map<String, Integer> listJob = new HashMap<>();
    private List<String> keys;

    public Occupation(){
        fillListJob();
        keys = new ArrayList<>(listJob.keySet());
        Random random = new Random();
        jobName = keys.get(random.nextInt(keys.size()));
        dailySalary = listJob.get(jobName); 
    }

    private void fillListJob(){
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

    public void changeJob(){
        Random random = new Random();
        boolean done = false;
        String oldJobName = jobName;
        while (!done){
            jobName = keys.get(random.nextInt(keys.size()));
            if(!jobName.equals(oldJobName)){
                done = true;
            }
        }

        dailySalary = listJob.get(jobName); 
    }
    
    public String getJobName(){
        return jobName;
    }

    public int getDailySalary(){
        return dailySalary;
    }
}
