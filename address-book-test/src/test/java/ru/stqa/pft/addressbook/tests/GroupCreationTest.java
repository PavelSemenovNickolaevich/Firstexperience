package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        applicationManager.goToGroupPage();
        applicationManager.initGroupCreation();
        applicationManager.fillGroupForm(new GroupData("Qa", "QAheader", "QAfooter"));
        applicationManager.submitGroupCreation();
        applicationManager.returnToGroupPage();
        applicationManager.logoutGroup();
    }
}