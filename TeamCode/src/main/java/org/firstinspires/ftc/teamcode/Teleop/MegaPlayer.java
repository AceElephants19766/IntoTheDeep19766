package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawUpDownCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRollRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ElbowKeepPos;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderGetToZero;
import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@TeleOp
public class MegaPlayer extends CommandOpMode {

    //Gampad
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
                        gamepadEx2
                )
        );
        //IMU Reset
        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        elbowArm.setDefaultCommand(
                new ElbowKeepPos(elbowArm)
        );
        //extender arm PID
        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new ExtenderArmCommand(extenderArm,30)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new ExtenderGetToZero(extenderArm)
        );

        //elbow arm up & down
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new ElbowArmCommand(elbowArm,90)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new ElbowArmCommand(elbowArm,0)
        );

        //opening and closing the hangArm
        gamepadEx2.getGamepadButton(GamepadKeys.Button.Y).whileActiveOnce(
                new HangArmCommand(hangArm, 1)
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.A).whileActiveOnce(
                new HangArmCommand(hangArm, -1)
        );

        //claw open&close
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new ClawCommand(claw,Claw.OPEN)
        );

        //Claw roll rotation
        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRollRotateCommand(clawRollRotat,ClawRollRotate.COLLECTING)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new ClawRollRotateCommand(clawRollRotat,ClawRollRotate.SCORING)
        );
        //Claw up & down
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).toggleWhenPressed(
                new ClawUpDownCommand(clawUpDown, ClawUpDown.COLLECT)
        );



    }
}
