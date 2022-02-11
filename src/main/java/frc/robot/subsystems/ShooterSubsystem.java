// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.GlobalVariables;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_TalonFX falconShooterUpper = new WPI_TalonFX(Constants.Motors.CANshooterUpper);
  private WPI_TalonFX falconShooterLower = new WPI_TalonFX(Constants.Motors.CANshooterLower);

  private double velocitysetUpper = 0.0;
  private double velocitysetLower = 0.0;

  /** Creates a new Subsys_Motors. */
  public ShooterSubsystem() {
    falconShooterUpper.configFactoryDefault();
    falconShooterLower.configFactoryDefault();
    falconShooterUpper.set(ControlMode.PercentOutput, 0.0);
    falconShooterLower.set(ControlMode.PercentOutput, 0.0);
    falconShooterLower.setInverted(true);
    falconShooterUpper.setNeutralMode(NeutralMode.Coast);
    falconShooterLower.setNeutralMode(NeutralMode.Coast);
   
      falconShooterUpper.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
      falconShooterLower.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
    
  
    //Name: Brennan 
    //About: configure the flywheels and set the PID loops(create the horizontal asympote for the velocity to get to) for the most optimal velocity control  
  
      falconShooterUpper.configClosedloopRamp(0.5, 30);
      falconShooterLower.configClosedloopRamp(0.5, 30);
      falconShooterUpper.configAllowableClosedloopError(0, 10);
      falconShooterLower.configAllowableClosedloopError(0, 10);

      falconShooterUpper.selectProfileSlot(0, 0);
      falconShooterUpper.config_kF(0, 0.049, 30);
      falconShooterUpper.config_kP(0, 0.055, 30);
      falconShooterUpper.config_kI(0, 0.0005, 30);
      falconShooterUpper.config_kD(0, 20.0, 30);
      falconShooterLower.selectProfileSlot(0, 0);
      falconShooterLower.config_kF(0, 0.049, 30);
      falconShooterLower.config_kP(0, 0.055, 30);
      falconShooterLower.config_kI(0, 0.0005, 30);
      falconShooterLower.config_kD(0, 20.0, 30);

      falconShooterUpper.configNominalOutputForward(0.0, 30);
      falconShooterUpper.configNominalOutputReverse(0.0, 30);
    
      falconShooterLower.configNominalOutputForward(0.0, 30);
      falconShooterLower.configNominalOutputReverse(0.0, 30);
    
  
  }

  public void shooter_set_power() {
    velocitysetUpper = GlobalVariables.UpperVelocitySet;
    velocitysetLower = GlobalVariables.LowerVelocitySet;
  }

  public void shooter_go() {
    falconShooterUpper.set(ControlMode.Velocity, velocitysetUpper);
    falconShooterLower.set(ControlMode.Velocity, velocitysetLower);
  }

  public void shooter_stop() {
    falconShooterUpper.set(ControlMode.PercentOutput, 0.0);
    falconShooterLower.set(ControlMode.PercentOutput, 0.0);
  }

  public void initialize_enconders() {
    falconShooterUpper.setSelectedSensorPosition(0, 0, 30);
    falconShooterLower.setSelectedSensorPosition(0, 0, 30);
  }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
    //Global_Variables.UpperShooterPower = falconShooterUpper.getMotorOutputPercent();
    //Global_Variables.LowerShooterPower = falconShooterLower.getMotorOutputPercent();
    GlobalVariables.UpperShooterVelocity = falconShooterUpper.getSelectedSensorVelocity();
    GlobalVariables.LowerShooterVelocity = falconShooterLower.getSelectedSensorVelocity();
  }
}
