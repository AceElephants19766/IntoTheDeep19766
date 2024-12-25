package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class HangArmCommand extends CommandBase {
    public DcMotor motor;

    public HangArmCommand (){

    }
    @Override
    public void initialize() {
        motor.setPower(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        motor.setPower(0);

    }
}
