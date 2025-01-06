package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawPitchRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.CollectingElbowServoCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Commands.HangArmForSec;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawPitchRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmServo;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@TeleOp
public class MegaPlayer extends CommandOpMode {

    //Gampad
    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    //Subsystem
    public Claw claw;
    public ClawRollRotate clawRollRotat;
    public ClawPitchRotate clawPitchRotate;
    public HangArm hangArm;
    public CollectingArmServo collectingArmServo;
    public DriveTrainMecanum driveTrainMecanum;

    @Override
    public void initialize() {
        //Subsystems
        claw = new Claw(hardwareMap);
        clawRollRotat = new ClawRollRotate(hardwareMap);
        clawPitchRotate = new ClawPitchRotate(hardwareMap);

        hangArm = new HangArm(hardwareMap);

        collectingArmServo = new CollectingArmServo(hardwareMap);

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx2
                )
        );
        //IMU Reset
        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        //claw open&close
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new ClawCommand(claw,0.5)
        );
        //Claw roll rotation
        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateCommand(clawRollRotat,0.38)
        );
        //Claw pitch rotation
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).toggleWhenPressed(
                new ClawPitchRotateCommand(clawPitchRotate, 0.38)
        );

        //when we catch the sample, when pressed closing the elevator and lifting the elbow
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new ParallelCommandGroup(
                        new CollectingElbowServoCommand(collectingArmServo, 0.1),
                        new HangArmForSec(hangArm, 0.5, 500)
                )
        );
        //opening and closing the elevator
        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileActiveOnce(
                new HangArmCommand(hangArm, -0.5)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whileActiveOnce(
                new HangArmCommand(hangArm, 0.5)
        );
        // for collecting
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).toggleWhenPressed(
                new CollectingElbowServoCommand(collectingArmServo, 0.25)
        );
        //for putting samples in the basket
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).toggleWhenPressed(
                new CollectingElbowServoCommand(collectingArmServo, 1)
        );
    }
}
