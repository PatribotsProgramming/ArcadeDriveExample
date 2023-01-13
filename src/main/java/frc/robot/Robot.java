package frc.robot;


/**
 * Quick note: MC means motor controller
 */


//-- Import Libraries --
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
<<<<<<< HEAD
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.ctre.phoenix.motorcontrol.ControlMode;
=======

>>>>>>> f8f407ee7632bc05316973c6e347f53f6c079a9f
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  /**
   * WPI_TalonFX(deviceNumber)
   * @param deviceNumber is the CAN-ID of the Falcon 500 motor **Set up in Phoenix Tuner**
   */
  // Right side drive train
  WPI_TalonFX _rightLeader = new WPI_TalonFX(0);
  WPI_TalonFX _rightFollow = new WPI_TalonFX(1);

  // Left side drive train
  WPI_TalonFX _leftFollow = new WPI_TalonFX(3);
  WPI_TalonFX _leftLeader = new WPI_TalonFX(4);  

  // Shooter Wheels 
  WPI_TalonFX _talon7 = new WPI_TalonFX(7);  
  WPI_TalonFX _talon8 = new WPI_TalonFX(8);

  // Intake SparkMAX
  CANSparkMax _trigger = new CANSparkMax(6, MotorType.kBrushless);

  CANSparkMax _intake = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax _turret = new CANSparkMax(2, MotorType.kBrushless);

  // Climb motors
  //  WPI_TalonFX _talon9 = new WPI_TalonFX(9);  
  //  WPI_TalonFX _talon10 = new WPI_TalonFX(10);  

  /**
   * XboxController(port)
   * @param port is the index on the Driver Station that the controller is plugged into.
   */
  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

  /**
   * DifferentialDrive(leftMotor, rightMotor)
   * @param leftMotor is the left motor controller
   * @param rightMotor is the right motor controller
   * In this case talon0 and talon4 are the leader motors for each side.
   */
  DifferentialDrive driveTrain = new DifferentialDrive(_leftLeader, _rightLeader);

  @Override
  public void robotInit() 
  {

    // Rest Motors to Factory Defults **This Can be done in Pheonix Tuner too** to create a standard for Motor Controller (MC) configs
    _leftLeader.configFactoryDefault();
    _leftFollow.configFactoryDefault();
    _rightLeader.configFactoryDefault();
    _rightFollow.configFactoryDefault();

    _talon7.configFactoryDefault();
    _talon8.configFactoryDefault();

    _turret.restoreFactoryDefaults();
    _intake.restoreFactoryDefaults();
    _trigger.restoreFactoryDefaults();

    // _talon9.configFactoryDefault();
    // _talon10.configFactoryDefault();

    /** 
     * Drive Train Follow "Group" set one motor to mimic the actions of another
     * follow-MC is the follow MC in the "follow group"
     * follow-MC.follow(leader-MC)
     * @param leader-MC is the leader MC in the "follow group"
     */
    _leftFollow.follow(_leftLeader);
    _rightFollow.follow(_rightLeader);
    
    _talon8.follow(_talon7);

    /**
     * setInverted(InvertType)
     * @param InvertType is the type of inversion applied to the Motor Controller (MC) 
     * setInverted(InvertType.FollowMaster) sets the InvertType regardless of the masters inversion
     */
    _leftFollow.setInverted(InvertType.FollowMaster);
    _rightFollow.setInverted(InvertType.FollowMaster);
    
    // Set Master Motor to Clockwise direction **Same as setInverted(true)**
    _rightLeader.setInverted(TalonFXInvertType.Clockwise);   
    
    // _talon10.follow(_talon9);
    // _talon9.setInverted(TalonFXInvertType.CounterClockwise);
    // _talon10.setInverted(InvertType.FollowMaster);
  }

  @Override
  public void robotPeriodic() 
  {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() 
  {
    /**
     * arcadeDrive(forward, turning)
     * @param forward is the LeftY of the XBOX controller
     * @param turning is the RightX of the XBOX controller
     */
    driveTrain.arcadeDrive(driver.getLeftY(), -driver.getRightX());
    
    // double liftUp = 0.4*-driver.getLeftTriggerAxis();
    // double liftDown = 0.4*driver.getRightTriggerAxis();
    
    _intake.set(-driver.getLeftTriggerAxis());
    _trigger.set(driver.getRightTriggerAxis());

    
    System.out.println(_intake.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    _turret.set(operator.getLeftX()*0.1);

    // // Creates a safety for the shooter
    // if (operator.getAButton()){
    //   _
    // }

    
    // if (liftUp != 0)
    // {
    //   _talon7.set(ControlMode.PercentOutput, liftUp);
    // } else if (liftDown != 0)
    // {
    //   _talon7.set(ControlMode.PercentOutput, liftDown);
    // }
    // else if (liftDown + liftUp == 0)
    // {
    //   _talon7.set(ControlMode.PercentOutput, 0);
    // }
    

    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
