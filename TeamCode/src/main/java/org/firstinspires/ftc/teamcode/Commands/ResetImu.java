package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

public class ResetImu  extends InstantCommand {
    public ResetImu (DriveTrainMecanum driveTrainMecanum){
        super (
                () -> driveTrainMecanum.reset(),
                      driveTrainMecanum
        );
    }
}
