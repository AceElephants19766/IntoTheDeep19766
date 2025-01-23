package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ResetExtnderEncoder extends InstantCommand {
    public ResetExtnderEncoder(ExtenderArm extenderArm){
        super(
                ()-> extenderArm.resetEncoder(),
                extenderArm
        );
    }
}
