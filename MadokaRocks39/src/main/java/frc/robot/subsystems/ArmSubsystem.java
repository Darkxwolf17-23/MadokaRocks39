package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IdConstants;

public class ArmSubsystem extends SubsystemBase{
    TalonFXConfiguration leftconfig;
    TalonFXConfiguration rightconfig;
    TalonFX m_leftarm = new TalonFX(IdConstants.twl);
    TalonFX m_rightarm = new TalonFX(IdConstants.twr);
    Follower right_follower;
      public ArmSubsystem() {
        // right_follower = new Follower(IdConstants.twl, false);
        // m_rightarm.setControl(right_follower);
        // right_follower = new follower()
            

// Arm motion logic
       leftconfig = new TalonFXConfiguration();
        leftconfig.Slot0.kP = .1;
        // "Nobody uses I" apparently, so dont set it.
        leftconfig.Slot0.kD = 0.087045;
        leftconfig.Slot0.kS = 0.284662;
        leftconfig.Slot0.kV = 1.30919;
        leftconfig.Slot0.kA = 0.15569;
        
        leftconfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; 
       
        rightconfig = new TalonFXConfiguration();
        rightconfig.Slot0.kP = .1;
        // "Nobody uses I" apparently, so dont set it.
        rightconfig.Slot0.kD = 0.087045;
        rightconfig.Slot0.kS = 0.284662;
        rightconfig.Slot0.kV = 1.30919;
        rightconfig.Slot0.kA = 0.15569;

        rightconfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive; 

        StatusCode status = StatusCode.StatusCodeNotInitialized;
        for (int i = 0; i < 5; ++i) {
            status = m_leftarm.getConfigurator().apply(leftconfig);
            status = m_rightarm.getConfigurator().apply(rightconfig);
            if (status.isOK())
                System.out.println("configuered motor");
                break;
        }
        if (!status.isOK()) {
            System.out.println("Could not configure device. Error: " + status.toString());
        }
    
      }
    
    public void setarm(Double pos ) {
        System.out.println("arm moves");
    final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
    m_leftarm.setControl(m_request.withPosition(pos));
    m_rightarm.setControl(m_request.withPosition(pos));
    }

    public void setarmL2() {
    final MotionMagicVoltage m_request = new MotionMagicVoltage(40);
    m_leftarm.setControl(m_request.withPosition(40));
    m_rightarm.setControl(m_request.withPosition(40));
    }
    public void setarmL3() {
    final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
    m_leftarm.setControl(m_request.withPosition(0));
    m_rightarm.setControl(m_request.withPosition(0));
    }
     public void setspeed( double speed){
        m_leftarm.set(speed);
        m_rightarm.set(speed);
     }
}

/*


public class armSubsystem extends SubsystemBase {

    TalonFX m_leftarm = new TalonFX(RobotConstants.LeftarmCanId);
    TalonFX m_rightarm = new TalonFX(RobotConstants.RightarmCanId);
    Follower right_follower;
    TalonFXConfiguration config;

    public armSubsystem() {
        right_follower = new Follower(m_leftarm.getDeviceID(), false);
        m_rightarm.setControl(right_follower);

        // Arm motion logic
        config = new TalonFXConfiguration();
        config.Slot0.kP = 0.1;
        // "Nobody uses I" apparently, so dont set it.
        config.Slot0.kD = 0.0087045;
        config.Slot0.kS = 0.034662;
        config.Slot0.kV = 2.00919;
        config.Slot0.kA = 0.0015569;

        // Zero means no limit to cruise velocity
        config.MotionMagic.MotionMagicCruiseVelocity = 1000; // changed from 250 (12 inches / second) * (1 sprocket
                                                             // rotation / 1.8 inches
                                                             // ) * (90 motor rotations / sprocket rotation) = ~191
                                                             // motor
                                                             // roations / second.8 inches /
        config.MotionMagic.MotionMagicAcceleration = config.MotionMagic.MotionMagicCruiseVelocity * 2 * 100; //
        // .5
        // seconds
        // to
        // reach
        // full
        // speed
        config.MotionMagic.MotionMagicJerk = config.MotionMagic.MotionMagicAcceleration * 10 * 50; // spread jerk over
                                                                                                   // .1
                                                                                                   // second

        // Limit Switch
        config.HardwareLimitSwitch.ReverseLimitEnable = true;
        config.HardwareLimitSwitch.ReverseLimitAutosetPositionEnable = true;
        config.HardwareLimitSwitch.ReverseLimitSource = ReverseLimitSourceValue.LimitSwitchPin;
        config.HardwareLimitSwitch.ReverseLimitType = ReverseLimitTypeValue.NormallyClosed;
        // config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        // config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0.0;

        config.HardwareLimitSwitch.ForwardLimitEnable = true;
        config.HardwareLimitSwitch.ForwardLimitSource = ForwardLimitSourceValue.LimitSwitchPin;
        config.HardwareLimitSwitch.ForwardLimitType = ForwardLimitTypeValue.NormallyClosed;
        // config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        // config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 82.0;

        // config.Slot0.GravityType = GravityTypeValue.arm_Static;
        // config.Slot0.kG = 0.15387;

        StatusCode status = StatusCode.StatusCodeNotInitialized;
        for (int i = 0; i < 5; ++i) {
            status = m_leftarm.getConfigurator().apply(config);
            if (status.isOK())
                break;
        }
        if (!status.isOK()) {
            System.out.println("Could not configure device. Error: " + status.toString());
        }
    }

    private void setControl(ControlRequest control) {
        m_leftarm.setControl(control);
        m_rightarm.setControl(control);
        one has to be positive one has to be negative 
    }

    // basic arm commands

    // public void setarmL1() {
    // final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
    // m_leftarm.setControl(m_request.withPosition(80));
    // }
    public void setarmL0() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_leftarm.setControl(m_request.withPosition(0));
    }

    public void setarmL2() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_leftarm.setControl(m_request.withPosition(140));
    }

    public void setarmL3() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_leftarm.setControl(m_request.withPosition(260));

    }

    public void setarmL4() {
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_leftarm.setControl(m_request.withPosition(455));
    }

    public void armr.setControl(new VelocityDutyCycle(.2 *
        // DriveConstants.ARM_LIMITER));
        m_leftarm.set(.2 * DriveConstants.ARM_LIMITER);
    }

    public void armDown() {
        // m_leftarm.setControl(new VelocityDutyCycle(-.2 *
        // DriveConstants.ARM_LIMITER));
        m_leftarm.set(-.2 * DriveConstants.ARM_LIMITER);
    }

    public void armStop() {
        // m_leftarm.setControl(new VelocityDutyCycle(0));
        m_leftarm.set(0);
    }

    public Command armUpStop() {
        return Commands.startEnd(() -> {
            this.armUp();
        }, () -> {
            this.armStop();
        }, this);
    }

    public Command armDownStop() {
        return Commands.startEnd(() -> {
            this.armDown();
        }, () -> {
            this.armStop();
        }, this);
    }

    public double getarmSpeed() {
        return m_leftarmk.get();
    }

    private final VoltageOut m_sysidControl = new VoltageOut(0);
    private SysIdRoutine m_SysIdRoutine = new SysIdRoutine(
            new SysIdRoutine.Config(
                    null, // Default ramp rate is acceptable
                    Volts.of(4), // Reduce dynamic voltage to 4 to prevent motor brownout
                    null, // Default timeout is acceptable
                          // Log state with Phoenix SignalLogger class
                    (state) -> SignalLogger.writeString("state", state.toString())),
            new SysIdRoutine.Mechanism(
                    drive -> m_leftelevator.setControl(m_sysidControl.withOutput(drive.in(Volts))),
                    log -> {
                    },
                    this, "elev"));

    /**
     * Returns a command that will execute a quasistatic test in the given
     * direction.
     *
     * @param direction The direction (forward or reverse) to run the test in
     
    public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
        // SignalLogger.start();

        return m_SysIdRoutine.quasistatic(direction);
        // SignalLogger.stop();
    }

    
     * Returns a command that will execute a dynamic test in the given direction.
     *
     * @param direction The direction (forward or reverse) to run the test in
     
    public Command sysIdDynamic(SysIdRoutine.Direction direction) {
        // SignalLogger.start();

        return m_SysIdRoutine.dynamic(direction);
        // SignalLogger.stop();
    }

}*/