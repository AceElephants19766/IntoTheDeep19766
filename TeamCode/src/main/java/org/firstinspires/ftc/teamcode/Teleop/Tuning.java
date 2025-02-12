package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
@TeleOp
public class Tuning extends CommandOpMode {
    public Claw claw;
    public ClawUpDown clawUpDown;
    public ClawRollRotate clawRollRotate;

    @Override
    public void initialize() {
        claw = new Claw(hardwareMap);
        clawUpDown = new ClawUpDown(hardwareMap);
        clawRollRotate = new ClawRollRotate(hardwareMap);

    }

    @Override
    public void run() {
        super.run();
        if (gamepad2.b)
        {
            claw.SetPose(Claw.OPEN);
        } else if (gamepad2.x){
            claw.SetPose(Claw.CLOSE);
        }
        clawUpDown.setPos(gamepad2.right_trigger);
        clawRollRotate.setPose(gamepad2.left_trigger);
        telemetry.addData(" Up down Pos",gamepad2.right_trigger);
        telemetry.addData("Roll Pos",gamepad2.left_trigger);
        telemetry.update();
    }
}