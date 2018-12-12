package com.example.gursehajharika.dronomatic;

public class Userinfoer {
    private String Readings;
   private String Time;


   public Userinfoer(){


   }
   public Userinfoer(String Readings,String Time){
       this.Readings = Readings;
       this.Time = Time;

   }



    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getReadings() {
        return Readings;
    }

    public void setReadings(String readings) {
        Readings = readings;
    }
}
