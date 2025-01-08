package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

public class HangArmCommand extends CommandBase {
    private HangArm hangArm;
    private double power;

    public HangArmCommand (HangArm hangArm , double power){
        this.hangArm = hangArm;
        this.power = power;
        addRequirements(hangArm);
    }
    @Override
    public void initialize() {
        hangArm.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return hangArm.isPressed();
    }

    @Override
    public void end(boolean interrupted) {
        hangArm.setPower(0);
    }
}
