package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class Robot extends TimedRobot {

    // Motores individuais
    private final PWMVictorSPX rightMotor1 = new PWMVictorSPX(0);
    private final PWMVictorSPX rightMotor2 = new PWMVictorSPX(1);
    private final PWMVictorSPX leftMotor1 = new PWMVictorSPX(2);
    private final PWMVictorSPX leftMotor2 = new PWMVictorSPX(3);

    // Motor do intake
    private final PWMVictorSPX intakeMotor = new PWMVictorSPX(4); // Configurar conforme necessário

    // Grupos de motores
    private final MotorControllerGroup rightSpeedGroup = new MotorControllerGroup(rightMotor1, rightMotor2);
    private final MotorControllerGroup leftSpeedGroup = new MotorControllerGroup(leftMotor1, leftMotor2);

    // Drivetrain
    private final DifferentialDrive drivetrain = new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);

    // Joystick
    private final Joystick stick = new Joystick(0);

    @Override
    public void robotInit() {}

    @Override
    public void robotPeriodic() {}

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
        // Controle de movimentação: analógico esquerdo (eixo Y) para frente e trás
        // Analógico direito (eixo X) para rotação
        double rotation = -stick.getRawAxis(1); // Eixo Y do analógico esquerdo
        double forward = stick.getRawAxis(4); // Eixo X do analógico direito

        drivetrain.arcadeDrive(forward, rotation);

        // Controle do intake
        if (stick.getRawButtonPressed(0)) {
            turnIntakeOn();
        }
        if (stick.getRawButtonReleased(0)) {
            turnIntakeOff();
        }
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

    // Métodos para controlar o intake
    private void turnIntakeOn() {
        intakeMotor.set(1.0); // Liga o intake na potência máxima
    }

    private void turnIntakeOff() {
        intakeMotor.set(0.0); // Desliga o intake
    }
}
