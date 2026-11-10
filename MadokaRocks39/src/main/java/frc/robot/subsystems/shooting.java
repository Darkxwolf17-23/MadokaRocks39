package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IdConstants;

public class shooting extends SubsystemBase{
    SparkMax m_ShootMotorl =new SparkMax(IdConstants.intr, MotorType.kBrushed);
    SparkMax m_ShootMotorc =new SparkMax(IdConstants.intl, MotorType.kBrushed);
    SparkMax m_ShootMotorv =new SparkMax(IdConstants.pull, MotorType.kBrushed);
    SparkMax m_ShootMotorb =new SparkMax(IdConstants.shl, MotorType.kBrushed);
    SparkMax m_ShootMotorn =new SparkMax(IdConstants.shr, MotorType.kBrushed);
}
