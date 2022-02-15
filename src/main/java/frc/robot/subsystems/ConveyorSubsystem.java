// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {
  /** Creates a new ConveyorSubsystem. */
  public ConveyorSubsystem() {}

  //making the motor
  TalonFX conveyor = new TalonFX(Constants.MotorsIDs.conveyormotor);


  //functions for running
  public void conveyorForward(){
    conveyor.set(TalonFXControlMode.PercentOutput, Constants.MotorValues.conveyorForward);
  }
  public void conveyorBackward(){
    conveyor.set(TalonFXControlMode.PercentOutput, Constants.MotorValues.conveyorBackward);
  }
  public void conveyorrStop(){
    conveyor.set(TalonFXControlMode.PercentOutput, Constants.MotorValues.conveyorStop);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
