// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Flash_Purple extends CommandBase {
  public static int t = 0;

  /** Creates a new Flash_Purple. */
  public Flash_Purple() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (t == 0) {
      for (var i = 0; i < Robot.m_ledBuffer.getLength(); i++) {
        Robot.m_ledBuffer.setRGB(i, 255, 233, 0);
        t = 1;
    }
  }

    if (t == 1) {
      for (var i = 0; i < Robot.m_ledBuffer.getLength(); i++) {
        Robot.m_ledBuffer.setRGB(i,0,0,0);
        t = 0;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    for (var i = 0; i < Robot.m_ledBuffer.getLength(); i++) {
      Robot.m_ledBuffer.setRGB(i,0,0,0);
  }
}
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
