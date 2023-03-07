// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Flash_Purple;
import frc.robot.commands.Motors_With_Color_Sensor;
import frc.robot.commands.Motors_With_Limit_Switch;
import frc.robot.commands.Run_Big_Motor;
import frc.robot.commands.Run_Little_Motor;
import frc.robot.subsystems.Test_Motors;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  //Initialize controllers
  private final PS4Controller ps4Controller = new PS4Controller(Constants.PS4CONTROLLER_ID);

  // Initialize Subsystems
  private final Test_Motors test_motors = new Test_Motors();

  //Initialize Commands
  private final Run_Big_Motor run_big_motor = new Run_Big_Motor(test_motors);
  private final Run_Little_Motor run_little_motor = new Run_Little_Motor(test_motors);
  private final Motors_With_Limit_Switch motors_with_limit_switch = new Motors_With_Limit_Switch(test_motors);
  private final Motors_With_Color_Sensor motors_with_color_sensor = new Motors_With_Color_Sensor(test_motors);
  private final Flash_Purple flash_purple = new Flash_Purple();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    final JoystickButton trianglebutton = new JoystickButton(ps4Controller, 4);
    trianglebutton.whileTrue(run_big_motor);

    final JoystickButton xbutton = new JoystickButton(ps4Controller,2);
    xbutton.whileTrue(run_little_motor);

    final JoystickButton squarebutton = new JoystickButton(ps4Controller, 1);
    squarebutton.whileTrue(motors_with_limit_switch);

    final JoystickButton left_one = new JoystickButton(ps4Controller, 5);
    left_one.whileTrue(motors_with_color_sensor);

    final JoystickButton shareButton = new JoystickButton(ps4Controller, 9);
    shareButton.whileTrue(flash_purple);

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
    return null;
  }
}
