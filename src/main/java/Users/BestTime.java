package Users;


import Activities.Discipline;


import java.time.LocalDate;

public class BestTime {

   private double bestTime;
   private LocalDate date;


   public BestTime(double bestTime, LocalDate date){
       this.bestTime = bestTime;
       this.date = date;

   }

    public void SetBestTime(Discipline discipline){

    }

    public double getBestTime(Discipline discipline){

        return bestTime;
    }
    //Todo ligenu med den her logik så kan man kun indtaste dato på selve dagen. og på det aktuelle tidspunkt skal evt ændres senere.
    public LocalDate getDate(){

        return date;
    }

}
