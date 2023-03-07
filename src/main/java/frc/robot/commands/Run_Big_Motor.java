// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants;
import frc.robot.subsystems.Test_Motors;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Run_Big_Motor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Test_Motors test_motors;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Run_Big_Motor(Test_Motors test_motors) {
    this.test_motors = test_motors;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(test_motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    test_motors.Run_Big_Motor(Constants.TEST_RUN_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    test_motors.Run_Big_Motor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
