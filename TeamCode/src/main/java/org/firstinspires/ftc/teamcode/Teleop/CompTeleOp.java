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
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommandOut;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmSetPower;
import org.firstinspires.ftc.teamcode.Commands.ExtenderkeepPos;
import org.firstinspires.ftc.teamcode.Commands.ResetElbowEncoder;
import org.firstinspires.ftc.teamcode.Commands.ResetExtnderEncoder;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectFromSub;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.AfterCollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PreaperForScoreSpecimen;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepaereForScoreSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSample;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectSpecimen;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

import java.util.function.DoubleSupplier;

@TeleOp
public class CompTeleOp extends CommandOpMode {
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

        //on start normal positions for the servos
        schedule(
                new InstantCommand(),
                new InstantCommand(()-> claw.SetPose(Claw.OPEN)),
                new InstantCommand(()-> clawUpDown.setPos(ClawUpDown.COLLECT)),
                new InstantCommand(()-> clawRollRotat.setPose(ClawRollRotate.DEFAULT))
        );
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
        gamepadEx1.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        //elbow
        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(
                new ResetElbowEncoder(elbowArm)
        );

        extenderReset = new Trigger(() -> extenderArm.isPressed());
        extenderReset.whenActive(
                new ResetExtnderEncoder(extenderArm)

        );
        //collect from submersible
        rightTriggerSupplier = () -> gamepadEx2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        gamepad2rightTrigger = new Trigger(() -> rightTriggerSupplier.getAsDouble() > 0.1);
        gamepad2rightTrigger.whileActiveContinuous(
                new CollectFromSub(elbowArm, extenderArm, rightTriggerSupplier)
        );

        gamepad2rightTrigger.whileActiveOnce(
                new ExtenderkeepPos(extenderArm)
        ).whenInactive(
                new SequentialCommandGroup(
                        new WaitCommand(500),
                        new InstantCommand(() -> extenderArm.setPower(0), extenderArm)
                )
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
                    elbowArm.getPidController().setSetPoint(elbowArm.getAngle().getAsDouble() + (ctr += 0.7));
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

        //Claw roll rotation
        gamepadEx2.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateToggleCommand(clawRollRotat, ClawRollRotate.SPECIAL)
        );
        //claw open close
        gamepadEx2.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new ConditionalCommand(
                        new InstantCommand(() -> claw.SetPose(Claw.CLOSE), claw),
                        new InstantCommand(() -> claw.SetPose(Claw.OPEN), claw),
                        () -> claw.getPos() == Claw.OPEN
                )
        );


        //Preaper for collect sample
        gamepadEx2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new PrepareForCollectSample(elbowArm, extenderArm, claw, clawUpDown, clawRollRotat)
        );
        //preaper for collect sample from sub
        gamepadEx2.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new ElbowArmCommand(elbowArm, 10)
        );
        //after collect sample
        gamepadEx2.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new AfterCollectSample(elbowArm, extenderArm, claw, clawUpDown, clawRollRotat)
        );
        //Preaper for score sample
        gamepadEx2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new PrepaereForScoreSample(elbowArm, extenderArm, clawUpDown, clawRollRotat)
        );

        //preaper for collect specimen press B to collect specimen
        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new PrepareForCollectSpecimen(extenderArm, elbowArm, clawRollRotat, clawUpDown, claw)
        );
        //preaper for score specimen
        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new PreaperForScoreSpecimen(elbowArm, extenderArm, claw, clawRollRotat, clawUpDown)
        );
        //score specimen
        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORE_SPECIMEN), clawUpDown)
        );
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("is Pressed", extenderArm.isPressed());
        telemetry.addData("extender", extenderArm.getLength());
        telemetry.addData("elbow", elbowArm.getDeg());
        telemetry.addData("ctr", ctr);
        telemetry.addData("kg", ElbowArm.getkG()*Math.signum(elbowArm.getPidController().getPositionError()));
        telemetry.addData("erech",Math.toDegrees(Math.acos((25.0/(38+(rightTriggerSupplier.getAsDouble()*30)))))-53);
//        telemetry.addData("toggle reader", toggleButtonReader.getState());
        telemetry.update();
    }
}