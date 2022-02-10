// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.GlobalVariables;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticsSubsystem extends SubsystemBase {

  /** Creates a new Subsys_Pneumatics. */

  private DoubleSolenoid dblSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.Pneumatics.dblSolenoidfwd, Constants.Pneumatics.dblSolenoidrev);
  //private Solenoid solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.Pneumatics.Solenoid);
  private AnalogInput pneumaticPressureSensor = new AnalogInput(Constants.Pneumatics.pneumaticPressureSensor);
  //private Compressor compressor = new Compressor(null);
  
  public PneumaticsSubsystem() {
    //compressor.enableDigital();
  }

  public void dblSolenoid(DoubleSolenoid.Value direction) {
    //dblSolenoid.set(direction);
  }

  public void solenoid_on(){
    solenoid.set(true);
  }

  public void solenoid_off(){
    solenoid.set(false);
  }
  

  public String dblSolenoid_status(){
    return dblSolenoid.get().toString();
  }*/

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    GlobalVariables.pneumaticPressureSensor = (250.0 * (pneumaticPressureSensor.getVoltage()/5.0)-25.0);
    //SmartDashboard.putString("Double Solenoid Status", dblSolenoid.get().toString());
  }
}
