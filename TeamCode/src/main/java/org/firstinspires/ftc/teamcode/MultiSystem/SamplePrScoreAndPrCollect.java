package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class SamplePrScoreAndPrCollect extends CommandBase {
    private ElbowArm elbowArm;
    private ExtenderArm extenderArm;
    private Claw claw;
    private ClawRollRotate clawRollRotate;
    private ClawUpDown clawUpDown;

    public SamplePrScoreAndPrCollect(ElbowArm elbowArm,ExtenderArm extenderArm,Claw claw,ClawRollRotate clawRollRotate,ClawUpDown clawUpDown){
        this.elbowArm = elbowArm;
        this.extenderArm = extenderArm;
        this.claw = claw;
        this.clawRollRotate = clawRollRotate;
        this.clawUpDown = clawUpDown;
        addRequirements(
                extenderArm,
                claw,
                clawRollRotate,
                clawUpDown
        );
    }

    @Override
    public void initialize() {
        new PrepaereForScoreSample(elbowArm,extenderArm,clawUpDown,clawRollRotate);
    }

    @Override
    public void end(boolean interrupted) {
        new PrepareForCollectSample(elbowArm,extenderArm,claw,clawUpDown,clawRollRotate);
    }
}
