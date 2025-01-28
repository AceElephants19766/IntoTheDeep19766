package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ClawUpDownToggleCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmJoystickCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetElbowEncoder;
import org.firstinspires.ftc.teamcode.Commands.ResetExtnderEncoder;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.MultiSystem.CollectSample;
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
        //IMU Reset
        gamepadEx1.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(
                new ResetImu(driveTrainMecanum)
        );
        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetElbowEncoder(elbowArm)
        );

        joystickRightYUpCondition = new Trigger(() -> -gamepadEx2.getRightY() > 0.1);
        joystickRightYUpCondition.whileActiveOnce(
                new ExtenderArmJoystickCommand(extenderArm, 0.6)
        );

        joystickRightYDownCondition = new Trigger(() -> -gamepadEx2.getRightY() < -0.1);
        joystickRightYDownCondition.whileActiveOnce(
                new ExtenderArmJoystickCommand(extenderArm, -0.6)
        );

        joystickLeftYUpCondition = new Trigger(() -> gamepadEx2.getLeftY() > 0.1);
        joystickLeftYUpCondition.whileActiveContinuous(
                new InstantCommand(() -> {
                    elbowArm.getPidController().setSetPoint(elbowArm.getAngle().getAsDouble() + (ctr += 0.7));
                })
        );
        joystickLeftYUpCondition.whenInactive(() -> ctr = 0);

        joystickLeftYDownCondition = new Trigger(() -> gamepadEx2.getLeftY() < -0.1);
        joystickLeftYDownCondition.whileActiveContinuous(
                new InstantCommand(() -> {
                    if (elbowArm.getAngle().getAsDouble() < 20) {
                        jump = 0.1 ;
                    }else {
                        jump =1;
                    }
                    elbowArm.getPidController().setSetPoint(elbowArm.getAngle().getAsDouble() - (ctr += jump));
                })
        );
        joystickLeftYUpCondition.whenInactive(() -> ctr = 0);


        extenderReset = new Trigger(() -> extenderArm.isPressed());
        extenderReset.whenActive(
                new ResetExtnderEncoder(extenderArm)

        );

        //claw open&close
        gamepadEx2.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new ClawToggleCommand(claw, Claw.OPEN)
        );
        //Claw roll rotation
        gamepadEx2.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateToggleCommand(clawRollRotat, ClawRollRotate.SPECIAL)
        );
        //Claw up & down
        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_UP).toggleWhenPressed(
                new ClawUpDownToggleCommand(clawUpDown, ClawUpDown.COLLECT)
        );

        //Collecting
        gamepadEx2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new PrepareForCollectSample(
                        elbowArm,
                        extenderArm,
                        clawUpDown,
                        clawRollRotat
                )
        );

        //Scoring a sample
        gamepadEx2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new PrepaereForScoreSample(
                        elbowArm,
                        extenderArm,
                        clawUpDown,
                        clawRollRotat
                )
        );

        //collecting a sample
        gamepadEx2.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new CollectSample(
                        elbowArm,
                        extenderArm,
                        claw,
                        clawRollRotat
                )
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SPECIMEN)
        );

        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new PrepareForCollectSpecimen(
                        extenderArm,
                        elbowArm,
                        clawRollRotat,
                        clawUpDown,
                        claw
                )
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new SequentialCommandGroup(
                        new ElbowArmCommand(elbowArm, 30),
                        new WaitCommand(300),
                        new ExtenderArmCommand(extenderArm, 2)
                )
        );
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("is Pressed", extenderArm.isPressed());
        telemetry.addData("extender", extenderArm.getLength());
        telemetry.addData("elbow", elbowArm.getDeg());
        telemetry.addData("ctr", ctr);
        telemetry.update();
    }
}