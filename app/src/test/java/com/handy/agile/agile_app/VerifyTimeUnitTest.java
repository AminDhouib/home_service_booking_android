package com.handy.agile.agile_app;

import com.handy.agile.agile_app.DialogBoxes.EditAvailabilityDialog;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerifyTimeUnitTest {

    public VerifyTimeUnitTest() {

    }

    //If we enter a valid time it should return true
    @Test
    public void time_isCorrect() {
        EditAvailabilityDialog myDialog = new EditAvailabilityDialog();
        String startTime = "09:00AM";
        String endTime = "10:00AM";
        Boolean verify = myDialog.verifyTime(startTime,endTime);
        assertEquals(true,verify);
    }

    //If we enter an invalid time it should return false
    @Test
    public void time_isNotCorrect() {
        EditAvailabilityDialog myDialog = new EditAvailabilityDialog();
        String startTime = "08:00AM";
        String endTime = "07:00AM";
        Boolean verify = myDialog.verifyTime(startTime,endTime);
        assertEquals(false,verify);
    }

}
