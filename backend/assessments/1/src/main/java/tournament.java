import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.*;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.*;

class OurCustomComparator1 implements Comparator<player>{
    public int compare(player s1, player s2){
        //   We are returning the object in descending order of their age.
        if (s1.getRuns() < s2.getRuns())
            return 1;
        else if (s1.getRuns() > s2.getRuns())
            return -1;
        return 0;
    }
}
class OurCustomComparator2 implements Comparator<player>{
    public int compare(player s1, player s2){
        //   We are returning the object in descending order of their age.
        if (s1.getWickets() < s2.getWickets())
            return 1;
        else if (s1.getWickets() > s2.getWickets())
            return -1;
        return 0;
    }
}
public class tournament {


    public static ArrayList<String[]> parseCSV(String file, boolean skipHeader) throws IOException {
        ArrayList<String[]> data = new java.util.ArrayList<String[]>();
        CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withSkipLines(skipHeader ? 1 : 0).build();
        String[] line;
        while ((line = reader.readNext()) != null) {
            data.add(line);
        }
        return data;
    }
    public static void main(String[] args) throws IOException {
         ArrayList<String[]> data;
        HashMap<String,ArrayList<player>> teamlist=new HashMap<String, ArrayList<player>>();
        File file=new File("src/main/resources/IP_2021-data.csv");
        data =parseCSV(file.getAbsolutePath().toString(),true);
        Scanner scn=new Scanner(System.in);
        for(String[] str:data)
        {
            String teamName=str[1];
            String playerName=str[0];
            String role=str[2];
            int matches=Integer.parseInt(str[3]);
            int runs=Integer.parseInt(str[4]);
            double average=Double.parseDouble(str[5]);
            double sr=Double.parseDouble(str[6]);
            int wickets=Integer.parseInt(str[7]);
            ArrayList<player> temp=teamlist.get(teamName);
            if(temp==null)
            {
                temp=new ArrayList<player>();
            }
            temp.add(new player(playerName,role,matches,runs,average,sr,wickets));
            teamlist.put("teamName",temp);
        }

        System.out.println("Enter 1 for returning bowlers with less than 40 wickets");
        System.out.println("Enter 2 for displaying the highest wicket taker and highest run scorer");
        System.out.println("Enter 3 for fethcing   the top 3 run-scorer and top 3 wicket-takers of the season.");
        int choice= scn.nextInt();
        switch(choice)
        {
            case 1:
                //finding the bowler wiht less than 40 wickets
                System.out.println("Enter the name of the team");
                String sname=scn.nextLine();
                ArrayList<player> task1=teamlist.get(sname);
                ArrayList<player> task1ans=new ArrayList<>();
                for(player PLayer :task1 )
                {
                    if(PLayer.getWickets()<40)
                    {
                        task1ans.add(PLayer);
                    }
                }
                break;
            case 2:
                System.out.println("Enter the name of the team");
                String sname1=scn.nextLine();
                ArrayList<player> task2=teamlist.get(sname1);
                String maxRunPLayer="";
                String maxWicketsPLayer="";
                int maxWickets=-1;int maxruns=-1;
                for(player Player :task2)
                {
                    if(Player.getRuns()>maxruns)
                    {
                         maxruns= Player.getRuns();
                         maxRunPLayer=Player.getName();
                    }
                    if(Player.getWickets()>maxWickets)
                    {
                        maxWickets= Player.getWickets();
                        maxWicketsPLayer= Player.getName();
                    }
                }
                //displaying the gihest wicket taker and highest run scorer
                break;
            case 3:
                PriorityQueue<player> topRuns=new PriorityQueue<>(new OurCustomComparator1());
                PriorityQueue<player> topWickets=new PriorityQueue<>(new OurCustomComparator1());
                for(ArrayList<player> arr : teamlist.values())
                {
                    for(player pl: arr)
                    {
                        topRuns.add(pl);
                    }
                }
                for(ArrayList<player> arr : teamlist.values())
                {
                    for(player pl: arr)
                    {
                        topWickets.add(pl);
                    }
                }
                ArrayList<player> top3runs=new ArrayList<>();
                ArrayList<player> top3wickets=new ArrayList<>();
                for(int i=0;i<3;i++)
                {
                    top3runs.add(topRuns.peek());
                    topRuns.poll();
                }
                for(int i=0;i<3;i++)
                {
                    top3wickets.add(topRuns.peek());
                    topWickets.poll();
                }


//top 3 run scorers and top3 wicket takers of the season
                break;
            default:
                System.out.println("Wrong input");
        }


    }

}
