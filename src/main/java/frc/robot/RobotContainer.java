// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.commands.climber.AngleClimbCommand;
import frc.robot.commands.climber.ClimberDownManualCommand;
import frc.robot.commands.climber.ClimberUpManualCommand;
import frc.robot.commands.shooter.ShooterXSpotCommand;
import frc.robot.commands.shooter.ShooterStopCommand;
import frc.robot.commands.shooter.ShooterWallHubCommand;
import frc.robot.commands.conveyor.ConveyorBackwardCommand;
import frc.robot.commands.conveyor.ConveyorForwardCommand;
import frc.robot.commands.harvestor.HarvestorInCommand;
import frc.robot.commands.harvestor.HarvestorOutCommand;
import frc.robot.commands.harvestor.HarvestorReverseCommand;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /* Controllers */
  private final Joystick driver = new Joystick(0);
  private final Joystick operator = new Joystick(1);


  private final SendableChooser<Command> autoChooser = new SendableChooser<Command>();


  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  /* Driver Buttons */
  private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kStart.value);

  /* Subsystems */
  private final SwerveSubsystem swerve = new SwerveSubsystem();
  private final ClimberSubsystem motors = new ClimberSubsystem();
  private final PneumaticsSubsystem pneumatics = new PneumaticsSubsystem();
  private final HarvestorSubsystem harvestor = new HarvestorSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final ConveyorSubsystem conveyor = new ConveyorSubsystem();



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    boolean fieldRelative = true;
    boolean openLoop = true;
    swerve.setDefaultCommand(new TeleopSwerve(swerve, driver, translationAxis, strafeAxis, rotationAxis, fieldRelative, openLoop));

    //makes the smartdashboard f0r auto commands
    autoChooser.addOption("S Then Backwards", new exampleAuto(swerve));
    autoChooser.addOption("DriveForwardOnly", new DriveForawrdAuto(swerve));
    autoChooser.addOption("TwoBallRightForward", new TwoBallRightForwardAuto(swerve));
    autoChooser.addOption("ThreeBallRight", new ThreeBallRightAuto(swerve));
    autoChooser.addOption("BackupAndShoot", new BackupAndShootAuto(swerve));
    autoChooser.addOption("TwoBallLeft", new TwoBallLeftAuto(swerve));
    SmartDashboard.putData("Auto Selector", autoChooser);
    
    
    // Configure the button bindings
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    //-----------------------Driver Buttons----------------------------------------------/
    zeroGyro.whenPressed(new InstantCommand(() -> swerve.zeroGyro()));
    new JoystickButton(driver, XboxController.Button.kA.value).whileHeld(new VisionAlignMovingCommand(swerve, driver, translationAxis, strafeAxis, rotationAxis, true, true));
    //new JoystickButton(driver, XboxController.Button.kB.value).whileHeld(new VisionAlignStopCommand(swerve, true, true));



    //-----------------------Shooter Buttons----------------------------------------------/
    new AxisTrigger(driver, 2).whenPressed(new ShooterXSpotCommand(shooter));
    new AxisTrigger(driver, 3).whenPressed(new ShooterWallHubCommand(shooter));
    new JoystickButton(driver, Button.kA.value).whenPressed(new ShooterStopCommand(shooter));



    //-----------------------Climbing Buttons----------------------------------------------/
    //more specific button combinations for complex programs
    //new JoystickButton(operator, Button.kY.value).whenPressed(new AutoClimbCommand(pneumatics, motors));
    //new JoystickButton(operator, Button.kB.value).whenPressed(new ClimberMotorUpCommand(motors));
    //new JoystickButton(operator, Button.kA.value).whenPressed(new ClimberMotorDownCommand(motors));
    //new JoystickButton(operator, Button.kX.value).whenPressed(new ClimberMotorStopCommand(motors));

    //basic up and down movement that is manual buttons
    //new JoystickButton(operator, Button.kLeftStick.value).whileHeld(new ClimberDownManualCommand(motors));
    //new JoystickButton(operator, Button.kRightStick.value).whileHeld(new ClimberUpManualCommand(motors));
    //new JoystickButton(operator, Button.kStart.value).whenPressed(new AngleClimbCommand(pneumatics));



    //-----------------------Harvestor Buttons----------------------------------------------/
    new AxisTrigger(operator, 2).whenPressed(new HarvestorOutCommand(harvestor, pneumatics));
    new AxisTrigger(operator, 3).whenPressed(new HarvestorInCommand(harvestor, pneumatics));
    new JoystickButton(operator, Button.kRightBumper.value).whileHeld(new HarvestorReverseCommand(harvestor));
    


    //-----------------------Conveyor Buttons----------------------------------------------/
    new JoystickButton(operator, Button.kA.value).whileHeld(new ConveyorForwardCommand(conveyor));
    new JoystickButton(operator, Button.kB.value).whileHeld(new ConveyorBackwardCommand(conveyor));

    //in case or driver wanting to shoot
    new JoystickButton(driver, Button.kY.value).whileHeld(new ConveyorForwardCommand(conveyor));  
  }


  public Command getAutonomousCommand() {
    //goes to the auto chooser selction
    return autoChooser.getSelected();
  }
}