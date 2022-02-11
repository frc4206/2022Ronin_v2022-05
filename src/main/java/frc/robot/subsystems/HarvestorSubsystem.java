// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HarvestorSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  public HarvestorSubsystem() {}
  TalonFX IntakeMotor = new TalonFX(Constants.MotorsIDs.groundmotor);
  //DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.Pneumatics.SolenoidForward, Constants.Pneumatics.SolenoidReverse);

  public void feederIn(){
    IntakeMotor.set(TalonFXControlMode.PercentOutput, Constants.MotorValues.FeederIn);
  }
  public void feederOut(){
    IntakeMotor.set(TalonFXControlMode.PercentOutput, Constants.MotorValues.FeederOut);
  }
  public void feederStop(){
    IntakeMotor.set(TalonFXControlMode.PercentOutput, Constants.MotorValues.FeederStop);
  }
  
  
  public void GroundFeederShifter(){/*
    switch (solenoid.get()){
      case kOff:
        solenoid.set(DoubleSolenoid.Value.kForward);
       break;
      case kForward:
        solenoid.set(DoubleSolenoid.Value.kReverse);
       break;
      case kReverse:
        solenoid.set(DoubleSolenoid.Value.kForward);
        break;
    }
*/
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
    
  }
  
}
