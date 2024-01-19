public class player {
    private String name;
    private String role;
    private int match;

    private  int runs;

    private double average;
    private double sr;
    private int wickets;

    public String getName() {
        return name;
    }

    public player(String name, String role,int match,int runs, double average, double sr, int wickets) {
        this.name = name;
        this.role = role;
        this.match=match;
        this.runs = runs;
        this.average = average;
        this.sr = sr;
        this.wickets = wickets;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSr() {
        return sr;
    }

    public void setSr(double sr) {
        this.sr = sr;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setName(String name) {
        this.name = name;
    }
}
