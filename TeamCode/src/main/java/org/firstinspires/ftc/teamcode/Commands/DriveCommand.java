package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

public class DriveCommand extends CommandBase {

    private Gamepad gamepad1;

    double x = gamepad1.left_stick_x*1.1;
    double y = -gamepad1.left_stick_y;
    double rx = gamepad1.right_stick_x;

    public DriveCommand (double x,double y, double rx){
        this.x = x;
        this.y = y;
        this.rx = rx;
    }

    @Override
    public void execute() {
        super.execute();//empty
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();

    }
}
