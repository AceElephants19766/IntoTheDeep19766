package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmPID;

import java.util.Calendar;

public class CollectingArmCommand extends CommandBase {

    public CollectingArmPID collectingArmPID;
    public int target;
    public long startTime;

    public CollectingArmCommand(CollectingArmPID collectingArmPID, int target) {
        this.collectingArmPID = collectingArmPID;
        this.target = target;
        addRequirements(collectingArmPID);
    }

    @Override
    public void initialize() {
        collectingArmPID.getPidController().setSetPoint(target);
    }

    @Override
    public void execute() {
        collectingArmPID.setPower(
                collectingArmPID.getPidController().calculate(
                        collectingArmPID.getRevs()
                )
        );
    }

    @Override
    public boolean isFinished() {
        if (!collectingArmPID.getPidController().atSetPoint()) {
            startTime = Calendar.getInstance().getTimeInMillis();
        }
        return Calendar.getInstance().getTimeInMillis() - startTime > 500;
    }

    @Override
    public void end(boolean interrupted) {
        //if the motor cannot finish, we will put a small amount of power at the "end"
        collectingArmPID.setPower(0.1);
    }
}


