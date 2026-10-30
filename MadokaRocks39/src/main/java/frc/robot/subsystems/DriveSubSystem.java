package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IdConstants;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DriveSubSystem extends SubsystemBase{
    SparkMax frontleftSparkMax =new SparkMax(IdConstants.flid, MotorType.kBrushed);
SparkMax frontrightSparkMax =new SparkMax(IdConstants. frid, MotorType.kBrushed);
SparkMax backleftSparkMax =new SparkMax(IdConstants. blid, MotorType.kBrushed);
SparkMax backrightSparkMax =new SparkMax(IdConstants. brid, MotorType.kBrushed);
 
    public void drive(int leftdrive, int rightdrive){
        frontleftSparkMax.set(leftdrive);
        backleftSparkMax.set(leftdrive);
        frontrightSparkMax.set(-rightdrive);
        backrightSparkMax.set(-rightdrive);
     
    }
}