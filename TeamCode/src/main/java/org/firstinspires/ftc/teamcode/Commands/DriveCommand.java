package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

public class DriveCommand extends CommandBase {

    public GamepadEx gamepadEx1;
    private DriveTrainMecanum driveTrainMecanum;

    public DriveCommand (DriveTrainMecanum driveTrainMecanum, GamepadEx gamepadEx1){
        this.driveTrainMecanum = driveTrainMecanum;
        this.gamepadEx1 = gamepadEx1;
        addRequirements(driveTrainMecanum);
    }

    @Override
    public void execute() {
        double x = gamepadEx1.getLeftX()*1.1;
        double y = gamepadEx1.getLeftY();
        double rx = gamepadEx1.getRightX();

        driveTrainMecanum.fieldOrientedDrive(x,y,rx);
    }
}
