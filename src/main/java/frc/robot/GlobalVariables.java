package frc.robot;

public class GlobalVariables {
  
  private static GlobalVariables globVar;
  
  public static double pneumaticPressureSensor;
  public static double climberLeftEncoder;
  public static double climberRightEncoder;
  public static String climbtasknumber;
  public static boolean Limitswitch;

  public static double UpperVelocitySetXSpot = 21150.0;//currently testing the balls for this 17150.0
  public static double LowerVelocitySetXSpot = 7050.0;//this is the starting position for tomorow  9650.0

  public static double UpperVelocitySetWallHub = 3900.0;//these two numbers are done 
  public static double LowerVelocitySetWallHub = 8100.0;//these will be tuned lator

  public static double UpperVelocitySetWallLow = 4000.0;//completly untested
  public static double LowerVelocitySetWallLow = 4000.0;//not done at all

  public static double UpperShooterVelocity;
  public static double LowerShooterVelocity;
}
