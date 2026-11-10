package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IdConstants;

public class IntakeSubSystem extends SubsystemBase{
    SparkMax  intakerightSparkMax =new SparkMax(IdConstants.intr, MotorType.kBrushless);
    SparkMax  intakeleftSparkMax =new SparkMax(IdConstants.intl,MotorType.kBrushless);

    
    public void spin( double speed){
     intakerightSparkMax.set(speed); 
      intakeleftSparkMax.set(speed); 
     } 

    
}
