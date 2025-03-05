package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommandOut;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmSetPower;
import org.firstinspires.ftc.teamcode.Commands.ExtenderkeepPos;
import org.firstinspires.ftc.teamcode.Commands.ResetExtnderEncoder;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectFromSub;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.Hang;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSpecimen2;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepaereForScoreSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen2;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen3;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

import java.util.function.DoubleSupplier;

@TeleOp
public class CompTeleOpOneDriver extends CommandOpMode {

    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    //Subsystem
    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawUpDown clawUpDown;
    public HangArm hangArm;
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;

    public DriveTrainMecanum driveTrainMecanum;

    public Trigger joystickRightYUpCondition;
    public Trigger joystickRightYDownCondition;
    public Trigger extenderReset;
    public Trigger joystickLeftYUpCondition;
    public Trigger joystickLeftYDownCondition;

    public Trigger gamepad2rightTrigger;

    public DoubleSupplier rightTriggerSupplier;


    double ctr = 0;
    double jump = 1;

    @Override
    public void initialize() {

        //Subsystems
        claw = new Claw(hardwareMap);
        clawRollRotat = new ClawRollRotate(hardwareMap);
        clawUpDown = new ClawUpDown(hardwareMap);
        extenderArm = new ExtenderArm(hardwareMap);
        elbowArm = new ElbowArm(hardwareMap);
        hangArm = new HangArm(hardwareMap);

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx1
                )
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileActiveContinuous(
                new InstantCommand(() -> {
                    driveTrainMecanum.fieldOrientedDrive(
                            (Math.pow((gamepadEx1.getLeftX() * 1.1), 5)) * 0.3,
                            (Math.pow(gamepadEx1.getLeftY(), 5)) * 0.3,
                            Math.pow(gamepadEx1.getRightX(), 5) * 0.25
                    );
                }, driveTrainMecanum)
        );

        //IMU Reset
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        //elbow
        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm, extenderArm)
        );

        //extender
        extenderReset = new Trigger(() -> extenderArm.isPressed());

        extenderReset.whenActive(
                new ResetExtnderEncoder(extenderArm)
        );

        //Claw roll rotation - ok
        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateToggleCommand(clawRollRotat, ClawRollRotate.SPECIAL)
        );

        //claw open close - ok
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new ConditionalCommand(
                        new InstantCommand(() -> claw.SetPose(Claw.CLOSE), claw),
                        new InstantCommand(() -> claw.SetPose(Claw.OPEN), claw),
                        () -> claw.getPos() == Claw.OPEN
                )
        );

        //collect from submersible - need to check
        rightTriggerSupplier = () -> gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);

        gamepad2rightTrigger = new Trigger(() -> rightTriggerSupplier.getAsDouble() > 0.1);

        gamepad2rightTrigger.whileActiveContinuous(
                new CollectFromSub(elbowArm, extenderArm, clawUpDown,claw, rightTriggerSupplier)
        );

        gamepad2rightTrigger.whileActiveOnce(
                new ExtenderkeepPos(extenderArm)
        );

        gamepad2rightTrigger.whenInactive(
                new SequentialCommandGroup(
                        new WaitCommand(500),
                        new InstantCommand(() -> extenderArm.setPower(0))
                )
        );

        gamepad2rightTrigger.whenInactive(
                new CollectSample(elbowArm,extenderArm,claw,clawUpDown)
        );

        //Preaper for score sample
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenActive(
                new PrepaereForScoreSample(elbowArm, extenderArm, clawUpDown, clawRollRotat)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenReleased(
                new PrepareForCollectSpecimen2(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new PreaperForScoreSpecimen2(elbowArm,extenderArm,claw,clawRollRotat,clawUpDown)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new InstantCommand(()-> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN))
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whenInactive(
                new PrepareForCollectSpecimen3(extenderArm,elbowArm,clawRollRotat,clawUpDown,claw)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new Hang(elbowArm,extenderArm,clawUpDown,clawRollRotat)
        );


        //extender open by hand
        joystickRightYUpCondition = new Trigger(() -> -gamepadEx2.getRightY() > 0.1);
        joystickRightYUpCondition.whileActiveOnce(
                new ExtenderArmCommandOut(extenderArm, elbowArm, 0.6)
        );

        //extender close by hand
        joystickRightYDownCondition = new Trigger(() -> -gamepadEx2.getRightY() < -0.1);
        joystickRightYDownCondition.whileActiveOnce(
                new ExtenderArmSetPower(extenderArm, elbowArm, -0.6)
        );

        //elbow up by hand
        joystickLeftYUpCondition = new Trigger(() -> gamepadEx2.getLeftY() > 0.1);
        joystickLeftYUpCondition.whileActiveContinuous(
                new InstantCommand(() -> {
                    elbowArm.getPidController().setSetPoint(elbowArm.getAngle().getAsDouble() + (ctr += 1));
                })
        );
        joystickLeftYUpCondition.whenInactive(() -> ctr = 0);

        //elbow down by hand
        joystickLeftYDownCondition = new Trigger(() -> gamepadEx2.getLeftY() < -0.1);
        joystickLeftYDownCondition.whileActiveContinuous(
                new InstantCommand(() -> {
                    if (elbowArm.getAngle().getAsDouble() < 20) {
                        jump = 0.1;
                    } else {
                        jump = 1;
                    }
                    elbowArm.getPidController().setSetPoint(elbowArm.getAngle().getAsDouble() - (ctr += jump));
                })
        );
        joystickLeftYUpCondition.whenInactive(() -> ctr = 0);



        //on start normal positions for the servos
        schedule(
                new InstantCommand(),
                new InstantCommand(() -> claw.SetPose(Claw.OPEN)),
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.P_F_COLLECT_SPECIMEN)),
                new InstantCommand(() -> clawRollRotat.setPose(ClawRollRotate.DEFAULT))
        );

    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("ff",ElbowArm.getFeedForward(extenderArm.getLength(),elbowArm.getDeg()));
        telemetry.addLine("");
//        telemetry.addData("Kp",elbowArm.getPidController().getP());
//        telemetry.addData("Kp",elbowArm.getPidController().getI());
        telemetry.addData("is Pressed", extenderArm.isPressed());
        telemetry.addData("extender", extenderArm.getLength());
        telemetry.addData("elbow", elbowArm.getDeg());
//        telemetry.addData("ctr", ctr);
//        telemetry.addData("erech",Math.toDegrees(Math.acos((25.0/(38+(rightTriggerSupplier.getAsDouble()*30)))))-53);
        telemetry.update();
    }
}
