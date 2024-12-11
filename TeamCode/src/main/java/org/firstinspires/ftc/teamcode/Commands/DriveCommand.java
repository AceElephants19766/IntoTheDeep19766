package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

public class DriveCommand extends CommandBase {

    private Gamepad gamepad1;
    private DriveTrainMecanum driveTrainMecanum;

    double x;
    double y;
    double rx;

    public DriveCommand (DriveTrainMecanum driveTrainMecanum, double x,double y, double rx){
        this.driveTrainMecanum = driveTrainMecanum;
        this.x = x;
        this.y = y;
        this.rx = rx;
    }

    @Override
    public void execute() {
        driveTrainMecanum.arcadeDrive(x,y,rx);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();

    }
}
