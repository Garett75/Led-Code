// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Test_Motors;

public class Motors_With_Limit_Switch extends CommandBase {
    private Test_Motors test_Motors;

  /** Creates a new Motors_With_Limit_Switch. */
  public Motors_With_Limit_Switch(Test_Motors test_Motors) {
    this.test_Motors = test_Motors;
   addRequirements(test_Motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    try(DigitalInput limit_switch = new DigitalInput(Constants.LIMIT_SWITCH_ID);) {
      if(!limit_switch.get()) {
        test_Motors.Run_Big_Motor(0);
        }
      else if(limit_switch.get()){
        test_Motors.Run_Big_Motor(Constants.TEST_RUN_SPEED);
      }
      }
    }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    test_Motors.Run_Big_Motor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
