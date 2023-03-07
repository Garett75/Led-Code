// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Test_Motors;

public class Motors_With_Color_Sensor extends CommandBase {
  private Test_Motors test_Motors;
  //private Robot robot;
  protected final static int COMMAND_REGISTER_BIT = 0x80;
 
  /** Creates a new Motors_With_Color_Sensor. */
  
  public Motors_With_Color_Sensor(Test_Motors test_Motors) {
    this.test_Motors = test_Motors;
    //this.robot = robot;
    addRequirements(test_Motors);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     if (Robot.state == 1 && Robot.confidence < .93) {
      test_Motors.Run_Big_Motor(Constants.TEST_RUN_SPEED);
      test_Motors.Run_Little_Motor(0);
     }

     else if (Robot.state == 2 && Robot.confidence < .93) {
      test_Motors.Run_Big_Motor(0);
      test_Motors.Run_Little_Motor(Constants.TEST_RUN_SPEED);
     }

     else if (Robot.state == 1 && Robot.confidence > .93) {
      test_Motors.Run_Big_Motor(0);
      test_Motors.Run_Little_Motor(0);
     }

     else if (Robot.state == 2 && Robot.confidence > .93) {
      test_Motors.Run_Big_Motor(0);
      test_Motors.Run_Little_Motor(0);
     }
   
    }
  
      

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    test_Motors.Run_Big_Motor(0);
    test_Motors.Run_Little_Motor(0);
    Robot.state = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
