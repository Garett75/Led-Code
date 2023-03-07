// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  public static int state = 0;
  public static int test1 = 0;
  public static int test2 = 0;
  public static double confidence = 0;
  private AddressableLED m_led;
  public static AddressableLEDBuffer m_ledBuffer;
  //private int m_rainbowFirstPixelHue;
  //public static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(Constants.LED_LENGTH);
  

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kYellowTarget = new Color(0.335, 0.545, 0.100);
  private final Color kPurpleTarget = new Color(0.220, 0.350, 0.420);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    CameraServer.startAutomaticCapture();

    m_robotContainer = new RobotContainer();

    m_led = new AddressableLED(0);
    m_ledBuffer = new AddressableLEDBuffer(Constants.LED_LENGTH);
    m_led.setLength(m_ledBuffer.getLength());
    m_led.setData(m_ledBuffer);
    m_led.start();
    

    
    }
  

  
      

    
  


  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    final PS4Controller ps4Controller = new PS4Controller(Constants.PS4CONTROLLER_ID);
    final JoystickButton optionButton = new JoystickButton(ps4Controller, 10);
    final JoystickButton shareButton = new JoystickButton(ps4Controller, 9);
    boolean flash_yellow = optionButton.getAsBoolean();
    boolean flash_purple = shareButton. getAsBoolean();
    
   
    m_led.setData(m_ledBuffer);
    //The colors are red, blue, green. Not red, green, blue.
    if (Robot.confidence < .93) {
      for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        m_ledBuffer.setRGB(i, 0, 0, 0 );
      }
    }

    if (Robot.state == 1 && Robot.confidence > .93) {
      for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        m_ledBuffer.setRGB(i, 195, 0, 67);
      }
    }

    if (Robot.state == 2 && Robot.confidence > .93) {
      for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        m_ledBuffer.setRGB(i, 255, 233, 0);
    }
  }

    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.addColorMatch(kPurpleTarget);

    CommandScheduler.getInstance().run();

    Color detectedColor = m_colorSensor.getColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kYellowTarget){
      colorString = "Yellow";
      state = 1;
    } else if (match.color == kPurpleTarget) {
      colorString = "Purple";
      state = 2;
    } else {
      colorString = "Unknown";
      state = 0;
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    confidence = match.confidence;

    if (flash_yellow == true && test1 == 1) {
      for (var i = 0; i < Robot.m_ledBuffer.getLength(); i++) {
      Robot.m_ledBuffer.setRGB(i, 195, 0, 67);
      test1 = 0;
      Timer.delay(.25);
      } 
    }

      else {
        for (var i = 1; i < Robot.m_ledBuffer.getLength(); i++) {
          Robot.m_ledBuffer.setRGB(i, 0, 0, 0);
          test1 = 1;
          Timer.delay(.25);
      }
    }

    if (flash_purple == true && test2 == 1) {
      for (var c = 0; c < Robot.m_ledBuffer.getLength(); c++) {
      Robot.m_ledBuffer.setRGB(c, 255, 233, 0);
      test2 = 0;
      } 
    }

      else {
        for (var i = 1; i < Robot.m_ledBuffer.getLength(); i++) {
          Robot.m_ledBuffer.setRGB(i, 0, 0, 0);
          test2 = 1;
         }
    }
    

  }
 









  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

}
