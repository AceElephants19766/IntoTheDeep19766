package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.BooleanSupplier;

public class SamplePrScoreAndPrCollect extends ConditionalCommand {
    public SamplePrScoreAndPrCollect(
            ElbowArm elbowArm,
            ExtenderArm extenderArm,
            Claw claw,
            ClawUpDown clawUpDown,
            ClawRollRotate clawRollRotate,
            ToggleButtonReader toggleButtonReader
    )
    {
        super(
                new PrepaereForScoreSample(elbowArm, extenderArm, clawUpDown, clawRollRotate),
                new PrepareForCollectSample(elbowArm, extenderArm, claw, clawUpDown, clawRollRotate),
                ()->toggleButtonReader.getState()
        );
        addRequirements(
                extenderArm,
                claw,
                clawUpDown,
                clawRollRotate
        );
    }

}
