





package org.firstinspires.ftc.teamcode.test;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class TestCode extends LinearOpMode
{


    // Add comment
    DcMotor FL;
    DcMotor FR;

    DcMotor BL;

    DcMotor BR;
    //ColorSensor color;
    //DistanceSensor distance;










    @Override
    public void runOpMode() {


        // Create two DcMotor OBJECTS connected to the motors on
        // the robot named motorLeft and motorRight
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        //color = hardwareMap.get(ColorSensor.class, "color1");
        //distance = hardwareMap.get(DistanceSensor.class, "distance1");


        // Set motor directions
        FL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);


        // Set motors
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Robot actions
        waitForStart();




        //Codes for testing


        drive(0.5, 1000);
        rotate(0.5, 90);



        //___




        //Sensing and telemetry
        //Double d = distance.getDistance(DistanceUnit.CM);
        //String r = color.red();
        //String b = color.blue();
        //telemetry.addData("red:", r);
        //telemetry.addData("blue:", b);
        //telemetry.addData("distance:", d);
        //telemetry.update();
        //___




        //Figuring path
        //String path = "";
        //if (d > 100 && b > 210) {
        //    path = "1";
        //    telemetry.addData("Path", path);
        //    telemetry.update();
        //} else if (d > 100 && r > 210) {
        //    path = "2";
        //    telemetry.addData("Path", path);
        //    telemetry.update();
        //} else if (d < 100 && r > 210) {
        //    path = "3";
        //    telemetry.addData("Path", path);
        //    telemetry.update();
        //} else {
        //    path = "4";
        //    telemetry.addData("Path", path);
        //    telemetry.update();
        //}
        //___




        //Path directions
        //if (path == "1") {
        //    drive(0.5, 5100);
        //} else if (path == "2") {
        //    drive(0.5, 5100);
        //} else if (path == "3") {
        //    rotate(0.5, 90);
        //    drive(0.5, 1600);
        //    rotate(0.5, -90);
        //    drive(0.5, 1600);
        //    rotate(0.5, -90);
        //    drive(0.5, 1300);
        //    rotate(0.5, 90);
        //    drive(0.5, 5100);
        //} else {
        //    rotate(0.5, -90);
        //    drive(0.5, 1800);
        //    rotate(0.5, 90);
        //    drive(0.5, 1650);
        //    rotate(0.5, 90);
        //    drive(0.5, 1500);
        //    rotate(0.5, -90);
        //    drive(0.5, 5100);
        //}
        //___




        //for(int n=0; n<4; n++){
        //    drive(0.5, 900);
        //    rotate(0.5, -45);
        //}


        //int n = 0;
        //while(n<4){
        //    drive(0.5, 900);
        //    rotate(0.5, -45);
        //    n++;
        //}
    }
    public void drive(double speed, int ticks){


        // Add comment
        if (opModeIsActive()) {


            // Add comment
            FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            // Add comment
            FL.setTargetPosition(ticks);
            FR.setTargetPosition(ticks);
            BL.setTargetPosition(ticks);
            BR.setTargetPosition(ticks);


            // Add comment
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);




            // Add comment
            FL.setPower(speed);
            FR.setPower(speed);
            BL.setPower(speed);
            BR.setPower(speed);


            // Add comment
            while (opModeIsActive() &&
                    (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy() )) {


                // Add comment
                telemetry.addData("Running to", "Left: " + ticks + " | Right: " + ticks);
                telemetry.addData("Current pos", "F_Left: " + FL.getCurrentPosition() + " | F_Right: " + FR.getCurrentPosition() + " | B_Left" + BL.getCurrentPosition() + " | B_Right" + BR.getCurrentPosition());
                telemetry.update();
            }


            // Add comment
            telemetry.addData("Running to", "Left: " + ticks + " | Right: " + ticks);
            telemetry.addData("Current pos", "F_Left: " + FL.getCurrentPosition() + " | F_Right: " + FR.getCurrentPosition() + " | B_Left" + BL.getCurrentPosition() + " | B_Right" + BR.getCurrentPosition());
            telemetry.update();


            // Add comment
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);
        }
    }


    public void rotate(double speed, double degrees){


        // Add comment
        if (opModeIsActive()) {


            // Add comment
            FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            //int the decimal number because ticks needs integer
            int ticks = (int) (degrees * 15.68);


            // Add comment
            FL.setTargetPosition(-ticks);
            FR.setTargetPosition(ticks);
            BL.setTargetPosition(-ticks);
            BR.setTargetPosition(ticks);


            // Add comment
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);






            // Add comment
            FL.setPower(speed);
            FR.setPower(speed);
            BL.setPower(speed);
            BR.setPower(speed);


            // Add comment
            while (opModeIsActive() &&
                    (FL.isBusy() && FR.isBusy() && BL.isBusy() && BR.isBusy())) {


                // Add comment
                telemetry.addData("Running to", "Left: " + ticks + " | Right: " + ticks);
                telemetry.addData("Current pos", "F_Left: " + FL.getCurrentPosition() + " | F_Right: " + FR.getCurrentPosition() + "B_Left: " + BL.getCurrentPosition() + " | B_Right: " + BR.getCurrentPosition());
                telemetry.update();
            }


            // Add comment
            telemetry.addData("Running to", "Left: " + ticks + " | Right: " + ticks);
            telemetry.addData("Current pos", "F_Left: " + FL.getCurrentPosition() + " | F_Right: " + FR.getCurrentPosition() + "Current pos", "B_Left: " + BL.getCurrentPosition() + " | B_Right: " + BR.getCurrentPosition());
            telemetry.update();


            // Add comment
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);
        }
    }




}
