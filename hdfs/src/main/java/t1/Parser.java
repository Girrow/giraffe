package t1;

import org.apache.hadoop.io.Text;

public class Parser {

  private int year;
  private int month;

  private int arriveDelayTime = 0;
  private int departureDelayTime = 0;
  private int distance = 0;

  private boolean arriveDelayAvailable = true;
  private boolean departureDelayAvailable = true;
  private boolean distanceAvailable = true;

  private String uniqueCarrier;

  
/*  Year				0
 * ,Month				1
 * ,DayofMonth			2
 * ,DayOfWeek			3
 * ,DepTime				4
 * ,CRSDepTime			5
 * ,ArrTime				6
 * ,CRSArrTime			7
 * ,UniqueCarrier		8
 * ,FlightNum			9
 * ,TailNum				10
 * ,ActualElapsedTime	11
 * ,CRSElapsedTime		12
 * ,AirTime				13
 * ,ArrDelay			14
 * ,DepDelay			15
 * ,Origin				16
 * ,Dest				17
 * ,Distance			18
 * ,TaxiIn				19
 * ,TaxiOut				20
 * ,Cancelled			21
 * ,CancellationCode	22
 * ,Diverted			23
 * ,CarrierDelay		24
 * ,WeatherDelay		25
 * ,NASDelay			26
 * ,SecurityDelay		27
 * ,LateAircraftDelay	28
 * 
 * */
  public Parser(Text text) {
    try {
      String[] colums = text.toString().split(",");

      // 운항 연도 설정
      year = Integer.parseInt(colums[0]);

      // 운항 월 설정
      month = Integer.parseInt(colums[1]);

      // 항공사 코드 설정
      uniqueCarrier = colums[8];
      
      // 항공기 출발 지연 시간 설정
      if (!colums[15].equals("NA")) {
        departureDelayTime = Integer.parseInt(colums[15]);
      } else {
        departureDelayAvailable = false;
      }

      // 항공기 도착 지연 시간 설정
      if (!colums[14].equals("NA")) {
        arriveDelayTime = Integer.parseInt(colums[14]);
      } else {
        arriveDelayAvailable = false;
      }

      // 운항 거리 설정
      if (!colums[18].equals("NA")) {
        distance = Integer.parseInt(colums[18]);
      } else {
        distanceAvailable = false;
      }
    } catch (Exception e) {
      System.out.println("Error parsing a record :" + e.getMessage());
    }
  }

  public int getYear() { return year; }

  public int getMonth() { return month; }

  public int getArriveDelayTime() { return arriveDelayTime; }

  public int getDepartureDelayTime() { return departureDelayTime; }

  public boolean isArriveDelayAvailable() { return arriveDelayAvailable; }

  public boolean isDepartureDelayAvailable() { return departureDelayAvailable;  }

  public String getUniqueCarrier() { return uniqueCarrier; }

  public int getDistance() { return distance; }

  public boolean isDistanceAvailable() { return distanceAvailable;  }
}
