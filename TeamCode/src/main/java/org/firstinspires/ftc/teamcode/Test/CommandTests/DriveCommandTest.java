package org.firstinspires.ftc.teamcode.Test.CommandTests;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

public class DriveCommandTest extends RunCommand {
    public DriveCommandTest(DriveTrainMecanum driveTrainMecanum, GamepadEx gamepadEx1) {
        super(
                () -> driveTrainMecanum.arcadeDrive(
                        gamepadEx1.getLeftX() * 1.1,
                        gamepadEx1.getLeftY(),
                        gamepadEx1.getRightX()
                ),
                driveTrainMecanum
        );
    }
}
