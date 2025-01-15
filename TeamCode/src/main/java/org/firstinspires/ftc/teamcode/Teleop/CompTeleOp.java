package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ClawUpDownCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmJoystickCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepaereForScore;
import org.firstinspires.ftc.teamcode.MultiSystem.PrepareForCollectCommand;
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

    @Override
    public void  initialize() {
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

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );

        joystickRightYUpCondition = new Trigger(() -> -gamepadEx2.getRightY() > 0.1);

        joystickRightYDownCondition = new Trigger(() -> -gamepadEx2.getRightY() < -0.1);

        joystickRightYUpCondition.whileActiveOnce(
                new ExtenderArmJoystickCommand(extenderArm,0.5)
        );

        joystickRightYDownCondition.whileActiveOnce(
                new ExtenderArmJoystickCommand(extenderArm,-0.5)
        );

        //IMU Reset
        gamepadEx1.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(
                new ResetImu(driveTrainMecanum)
        );
        //Collecting
        gamepadEx2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new PrepareForCollectCommand(
                        elbowArm,
                        extenderArm,
                        clawUpDown,
                        clawRollRotat
                )
        );
        //Scoring
        gamepadEx2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new PrepaereForScore(
                        elbowArm,
                        extenderArm,
                        clawUpDown,
                        clawRollRotat
                )
        );
        //claw open&close
        gamepadEx2.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new ClawCommand(claw,Claw.OPEN)
        );
        //Claw roll rotation
        gamepadEx2.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateCommand(clawRollRotat,ClawRollRotate.DEFAULT)
        );
        //Claw up & down
        gamepadEx2.getGamepadButton(GamepadKeys.Button.DPAD_UP).toggleWhenPressed(
                new ClawUpDownCommand(clawUpDown, ClawUpDown.COLLECT)
        );

        gamepadEx2.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new ElbowArmCommand(elbowArm,ElbowArm.DEFAULT)
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.A).whenPressed(

                new SequentialCommandGroup(
                        new ElbowArmCommand(elbowArm, ElbowArm.COLLECT),
                        new WaitCommand(500),
                        new ClawSetPose(claw,Claw.CLOSE),
                        new WaitCommand(500),
                        new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT)
                )
        );
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("Trigger up true", joystickRightYUpCondition.get());
        telemetry.addData("Trigger up values", gamepadEx2.getRightY());
        telemetry.addData("Trigger down values", joystickRightYDownCondition.get());
        telemetry.addData("Trigger down true",gamepadEx2.getRightY());
        telemetry.update();
    }
}