package Pojos;

public class PostPojo {
    private String name;
    private String job;

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public PostPojo(String name, String job){
        this.name = name;
        this.job = job;
    }
}
